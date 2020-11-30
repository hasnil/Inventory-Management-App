package serverController;

import java.io.IOException;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import message.Message;
import commonModel.*;

/**
 * 
 * This class will act as the server for clients accessing
 * the customer or inventory management system.
 * 
 * ENSF 607 Project - November 26, 2020
 * @author Completed by Hunter Kimmett and Lotfi Hasni
 *
 */
public class ModelController implements Runnable{

	private Shop theShop;
	private ServerController serverController;
	private Serializer serializer;
	private Deserializer deserializer;
	private DatabaseController databaseController;
	Message theMessage;
	private Socket theSocket;
	private HashMap<String,String> usernamesAndPasswords;
	
	public ModelController(Socket accept) {
		this.theSocket = accept;
	}
	
	public ModelController(ServerController serverController, Serializer serializer, 
			Deserializer deserializer, DatabaseController databaseController, Message theMessage) {
		this.serverController = serverController;
		this.serializer = serializer;
		this.deserializer = deserializer;
		this.databaseController = databaseController;
		this.theMessage = theMessage;
	}
	
	public ServerController getServerController() {
		return serverController;
	}

	public void setServerController(ServerController serverController) {
		this.serverController = serverController;
	}

	public Serializer getSerializer() {
		return serializer;
	}

	public void setSerializer(Serializer serializer) {
		this.serializer = serializer;
	}

	public Deserializer getDeserializer() {
		return deserializer;
	}

	public void setDeserializer(Deserializer deserializer) {
		this.deserializer = deserializer;
	}

	public DatabaseController getDatabaseController() {
		return databaseController;
	}

	public void setDatabaseController(DatabaseController databaseController) {
		this.databaseController = databaseController;
	}

	public boolean addTool(Tool t) {
		
		for(Supplier s : theShop.getSupplierList().getSupplierArrayList()) {
			if(t.getSupplierID() == s.getId()) {
				databaseController.addTool(t);
				theShop.addTool(t);
				return true;
			}
		}
		return false;
	}
	
	public void deleteTool(int toolId) {
		databaseController.deleteTool(toolId);
		theShop.getTheInventory().deleteTool(toolId);
	}
	
	public void decreaseToolQuantity(int toolId, int amount) {
		
		int amountToOrder;
		int currentAmount = theShop.getTheInventory().getToolQuantity(toolId);
		if(currentAmount - amount < 0) {
			return;
		}
		// When quantity drops below 40, must update database to be same as local model
		else if(currentAmount - amount < 40) {
			amountToOrder = 50 - (currentAmount - amount);
			theShop.getTheInventory().searchForTool(toolId).decreaseToolQuantity(amount);
			// update model
			Message result = theShop.getTheInventory().placeOrder(theShop.getTheInventory().searchForTool(toolId), amountToOrder);
			if(result.getSelection() == 1) {
				databaseController.decreaseToolQuantity(toolId, amount);
				// update orderline
				databaseController.updateOrderLine(result.getTheOrder().getId(), amountToOrder);
			}
			else if(result.getSelection() == 2) {
				databaseController.decreaseToolQuantity(toolId, amount);
				// add new orderline
				databaseController.generateOrderLine(result.getTheOrder().getId(), toolId, result.getTheOrderLine().getSupplierId(), amountToOrder);
			}
			else if(result.getSelection() == 3) {
				databaseController.decreaseToolQuantity(toolId, amount);
				//add new order and orderline
				databaseController.addOrder(result.getTheOrder().getId(), result.getTheOrder().getDate());
				databaseController.generateOrderLine(result.getTheOrderLine().getOrderId(), toolId, result.getTheOrderLine().getSupplierId(), amountToOrder);
			}
		}
		else {
			theShop.getTheInventory().searchForTool(toolId).decreaseToolQuantity(amount);
			databaseController.decreaseToolQuantity(toolId, amount);
		}
	}
	
	
	public void decreaseToolQuantity(String name, int amount) {
		Tool tool = theShop.getTool(name);
		decreaseToolQuantity(tool.getID(), amount);
	}
	
	public void addCustomer(Customer c) {
		databaseController.addCustomer(c);
		theShop.addCustomer(c);
	}
	
	public void deleteCustomer(int id) {
		databaseController.deleteCustomer(id);
		theShop.deleteCustomer(id);
	}
				
	
	
	/**
	 * This method will create the model, creating and linking all objects as necessary
	 */
	public void loadModel() {
		
		//load suppliers
		ArrayList<Supplier> allSuppliers = loadAllSuppliers();

		
		//load items 
		ArrayList<Tool> allTools = loadAllTools();
		
		//connect tools and suppliers
		for(Tool t : allTools) {
			for(Supplier s : allSuppliers) {
				if(t.getSupplierID() == s.getId()) {
					s.addTool(t);
					t.setSupplier(s);
				}}}
		
		//load orderlines (orderline's order id is set within this method)
		ArrayList<OrderLine> allOrderLines = loadAllOrderLines(allTools);
		
		//load orders (order id and date set within this method)
		ArrayList<Order> allOrders = loadAllOrders(allOrderLines);
		
		//create inventory
		Inventory theInventory = new Inventory(allTools, allOrders);
		
		//load customers
		ArrayList<Customer> allCustomers = loadAllCustomers();
		
		//create shop
		theShop = new Shop(theInventory, new SupplierList(allSuppliers), new CustomerList(allCustomers));
		
		//load user verification info
		usernamesAndPasswords = databaseController.loadUsernamesAndPasswords();
	}
	
	
	private ArrayList<Tool> loadAllTools() {
		ResultSet tool = databaseController.loadAllTools();
		ArrayList<Tool> listOfTools = new ArrayList<Tool> ();
		try {
			while(tool.next())
			{	if(tool.getString("Type").equals("Electrical")) {
					listOfTools.add(
							new ElectricalTool(tool.getInt("ToolID"),
							tool.getString("Name"), 
							tool.getInt("Quantity"), 
							tool.getDouble("Price"), 
							tool.getInt("SupplierID"),
							tool.getString("PowerType")));	
				}
				else if(tool.getString("Type").equals("Non-Electrical")) {
						listOfTools.add(
								new NonElectricalTool(tool.getInt("ToolID"),
								tool.getString("Name"), 
								tool.getInt("Quantity"), 
								tool.getDouble("Price"), 
								tool.getInt("SupplierID")));	
				}			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfTools;
	}
	
	private ArrayList<Supplier> loadAllSuppliers(){
		ResultSet supplier = databaseController.loadAllSuppliers();
		ArrayList<Supplier> listOfSuppliers = new ArrayList<Supplier> ();
		try {
			while(supplier.next())
			{
				if(supplier.getString("Type").equals("Local")) {
						listOfSuppliers.add(
								new LocalSupplier(supplier.getInt("SupplierId"),
									supplier.getString("Name"), 
									supplier.getString("Address"), 
									supplier.getString("CName"),
									supplier.getString("Phone")));		
				}
				else if(supplier.getString("Type").equals("International")) {
						listOfSuppliers.add(
								new InternationalSupplier(supplier.getInt("SupplierId"),
									supplier.getString("Name"), 
									supplier.getString("Address"), 
									supplier.getString("CName"),
									supplier.getString("Phone"),
									supplier.getDouble("ImportTax")));				
				}		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfSuppliers;
	}
	
	
	private ArrayList<OrderLine> loadAllOrderLines(ArrayList<Tool> allTools){
		ResultSet orderLine = databaseController.loadAllOrderLines();
		ArrayList<OrderLine> orderLinesList = new ArrayList<OrderLine>();
		try {
			while(orderLine.next()) {
				int toolId = orderLine.getInt("ToolID");
				for(Tool t: allTools) {
					if(t.getID() == toolId) {
						OrderLine temp = new OrderLine(t, orderLine.getInt("Quantity"));
						temp.setOrderId(orderLine.getInt("OrderID"));
						orderLinesList.add(temp);
					}
				}
			}
			//orderLine.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderLinesList;
	}
	
	private ArrayList<Order> loadAllOrders(ArrayList<OrderLine> allOrderLines){
		ResultSet order = databaseController.loadAllOrders();
		ArrayList<Order> ordersList = new ArrayList<Order> ();
		try {
			while(order.next())
			{//(order id and date set within this method)
				//public Order(ArrayList<OrderLine> orderLines) 
				int orderId = order.getInt("OrderID");
				ArrayList<OrderLine> temp = new ArrayList<OrderLine>();
				for(OrderLine ol : allOrderLines) {
					if(ol.getOrderId() == orderId) {
						temp.add(ol);
					}
				}
				Order tempOrder = new Order(temp);
				tempOrder.setId(orderId);
				tempOrder.setDate(order.getString("Date"));
				ordersList.add(tempOrder);
			}
			//order.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ordersList;
	}
	
	
	private ArrayList<Customer> loadAllCustomers(){
		ResultSet customer = databaseController.loadAllCustomers();
		ArrayList<Customer> customersList = new ArrayList<Customer> ();
		try {
			while(customer.next())
			{
				if(customer.getString("Type").equals("R")) {
					customersList.add(
						new ResidentialCustomer(customer.getInt("ClientId"),
							customer.getString("FName"), 
							customer.getString("LName"), 
							customer.getString("Address"),
							customer.getString("PostalCode"),
							customer.getString("PhoneNum"),
							customer.getString("Type").charAt(0)));	
				}
				else if(customer.getString("Type").equals("C")) {
					customersList.add(
						new CommercialCustomer(customer.getInt("ClientId"),
							customer.getString("FName"), 
							customer.getString("LName"), 
							customer.getString("Address"),
							customer.getString("PostalCode"),
							customer.getString("PhoneNum"),
							customer.getString("Type").charAt(0)));	
				}
			}
			//customer.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customersList;
		
	}
	
	/**
	 * Receives client side messages and sends responses.
	 */
	public void run() {

		Serializer serializer = new Serializer(theSocket);
		Deserializer deserializer = new Deserializer(theSocket);
		DatabaseController myDatabaseController = new DatabaseController();
		
		setSerializer(serializer);
		setDeserializer(deserializer);
		setDatabaseController(myDatabaseController);
		setServerController(serverController);
		
		loadModel();
	
	
		while(true) {
			
			Message messageFromClient = (Message)deserializer.receiveResponse();
			
			// 1. Search tool by name
			if(messageFromClient.getSelection() == 1) { 
				Message responseMessage = new Message();
				ArrayList<Tool> toolsList = new ArrayList<Tool>();
				
				for(Tool t : theShop.getTheInventory().getToolList()) {
					if(t.getName().toLowerCase().contains(messageFromClient.getInstructions().toLowerCase())) {
						toolsList.add(t);
					}
				}
				responseMessage.setToolsList(toolsList);
				serializer.sendObject(responseMessage);
			}
			// 2. List all tools
			else if(messageFromClient.getSelection() == 2) { 
				Message responseMessage = new Message();
				responseMessage.setToolsList(theShop.getTheInventory().getToolList());
				serializer.sendObject(responseMessage);
			}
			// 3. Search tool by id
			else if(messageFromClient.getSelection() == 3) {
				Message responseMessage = new Message();
				ArrayList<Tool> toolsList = new ArrayList<Tool>();
				
				for(Tool t : theShop.getTheInventory().getToolList()) {
					if(String.valueOf(t.getID()).contains(String.valueOf(messageFromClient.getId()))) {
						toolsList.add(t);
					}
				}
				responseMessage.setToolsList(toolsList);
				serializer.sendObject(responseMessage);
			}
			// 6. Add tool
			else if(messageFromClient.getSelection() == 6) {
				boolean result = addTool(messageFromClient.getTheTool());
				Message responseMessage = new Message();
				responseMessage.setResult(result);
				serializer.sendObject(responseMessage);
			}
			// 7. Delete item
			else if(messageFromClient.getSelection() == 7) {
				deleteTool(messageFromClient.getId());
			}
			// 8. Add customer
			else if(messageFromClient.getSelection() == 8) {
				addCustomer(messageFromClient.getTheCustomer());
			}
			// 9. Delete customer
			else if(messageFromClient.getSelection() == 9) {
				deleteCustomer(messageFromClient.getId());
			}
			// 11. Get customers by id
			else if(messageFromClient.getSelection() == 11) {
				Message responseMessage = new Message();
				int customerId = messageFromClient.getId();
				ArrayList<Customer> filteredCustomers = new ArrayList<Customer>();
				
				for(Customer c: theShop.getCustomerList().getCustomerArrayList()) {
					if(String.valueOf(c.getId()).contains(String.valueOf(customerId))) {
						filteredCustomers.add(c);
					}
				}
				responseMessage.setCustomersList(filteredCustomers);
				serializer.sendObject(responseMessage);
			}
			// 12. Get customers by last name
			else if(messageFromClient.getSelection() == 12) {
				Message responseMessage = new Message();
				String customerLastName = messageFromClient.getInstructions();
				ArrayList<Customer> filteredCustomers = new ArrayList<Customer>();
				
				for(Customer c: theShop.getCustomerList().getCustomerArrayList()) {
					if(c.getLastName().toLowerCase().contains(customerLastName.toLowerCase())) {
						filteredCustomers.add(c);
					}
				}
				responseMessage.setCustomersList(filteredCustomers);
				serializer.sendObject(responseMessage);
			}
			// 13. Get customers by type
			else if(messageFromClient.getSelection() == 13) {
				Message responseMessage = new Message();
				char customertype = messageFromClient.getType();
				ArrayList<Customer> filteredCustomers = new ArrayList<Customer>();
				
				for(Customer c: theShop.getCustomerList().getCustomerArrayList()) {
					if(c.getType() == customertype) {
						filteredCustomers.add(c);
					}
				}
				responseMessage.setCustomersList(filteredCustomers);
				serializer.sendObject(responseMessage);
			}
			// 14. Decrease tool quantity by id
			else if(messageFromClient.getSelection() == 14) {
				decreaseToolQuantity(messageFromClient.getId(), messageFromClient.getAmount());
			}
			// 15. Decrease tool quantity by name
			else if(messageFromClient.getSelection() == 15) {
				decreaseToolQuantity(messageFromClient.getInstructions(), messageFromClient.getAmount());
			}
			// 16. Write order to file
			else if(messageFromClient.getSelection() == 16) {
				Message responseMessage = new Message();
				ArrayList<Order> orders = theShop.getTheInventory().getOrders();
				responseMessage.setTheOrder(orders.get(orders.size() - 1));
				serializer.sendObject(responseMessage);
			}
			
			// 17. Search tool by id and name
			else if(messageFromClient.getSelection() == 17) {
				Message responseMessage = new Message();
				int id = messageFromClient.getId();
				String name = messageFromClient.getInstructions().trim();
				for(Tool stored : theShop.getTheInventory().getToolList()) {
					if(stored.getID() == id && stored.getName().equals(name)) {
						responseMessage.setTheTool(stored);
					}
				}
				serializer.sendObject(responseMessage);
			}
			// 18. Check username and password
			else if(messageFromClient.getSelection() == 18) {
				Message responseMessage = new Message();
				String username = messageFromClient.getInstructions();
				String password = messageFromClient.getMoreInstructions();
				
				if(usernamesAndPasswords.containsKey(username) == true 
						&& usernamesAndPasswords.get(username).equals(password)) {
					responseMessage.setResult(true);
					serializer.sendObject(responseMessage);
				}
				else {
					responseMessage.setResult(false);
					serializer.sendObject(responseMessage);
				}
				
				
			}
		}
	}
	

	
	public static void main(String [] args) {
		// Creates and runs server
		System.out.println("Server running...");
		ServerController serverController = new ServerController();
		ExecutorService pool = Executors.newFixedThreadPool(20);
		
		while(true) {
			try {
				pool.execute(new ModelController(serverController.serverSocket.accept()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

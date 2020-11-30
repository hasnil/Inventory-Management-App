package commonModel;


/**
 *  The Shop class represents a retail store that offers tools for sale. It holds an 
 *  inventory of all the tools in stock and a list of all suppliers. This class is used
 *  by the front end to allow the user to access the retail store program's information.
 *  Adapted from lecture UML diagram and tutorial video to ensure design is highly cohesive
 *  and adheres to single responsibility principle.
 *  
 *
 * ENSF 607 Project - November 26, 2020
 * @author Completed by Hunter Kimmett and Lotfi Hasni
 *
 */
public class Shop{

	/**
	 * The inventory of Tools in the shop
	 */
	private Inventory theInventory;
	
	/**
	 * List of supplier's for shop's Tools
	 */
	private SupplierList theSuppliers;
	
	/**
	 * List of shop's customers
	 */
	private CustomerList theCustomers;
	
	/**
	 * Constructor method for creating Shop with
	 * appropriate Tools and suppliers.
	 * @param ToolsFileName the name of the Tools text file
	 * @param suppliersFileName the name of the suppliers text file
	 */
	public Shop(Inventory i, SupplierList s, CustomerList c) {
		//FileManager fileManager = new FileManager();
		theSuppliers = s;
		theInventory = i;
		theCustomers = c;
	}
	
	public CustomerList getTheCustomers() {
		return theCustomers;
	}

	public void setTheCustomers(CustomerList theCustomers) {
		this.theCustomers = theCustomers;
	}

	/**
	 * Gets the shop's inventory.
	 * @return the inventory
	 */
	public Inventory getTheInventory() {
		return theInventory;
	}
	
	/**
	 * Used to set the shop's inventory.
	 * @param inventory the new inventory
	 */
	public void setTheInventory(Inventory inventory) {
		theInventory = inventory;
	}
	
	
	/**
	 * Used to add an Tool to the inventory.
	 * @param Tool the Tool to add to inventory
	 */
	public void addTool(Tool tool) {
		
		for(Supplier s: theSuppliers.getSupplierArrayList()) {
			if(s.getId() == tool.getSupplierID()) {
				s.addTool(tool);
				tool.setSupplier(s);
			}
		}
		theInventory.addTool(tool);
		return;
	}
	
	
	/**
	 * Displays all Tools in the shop's inventory.
	 */
	public void listAllTools() {
		System.out.println(theInventory);
	}
	
	/**
	 * This method is used to reduce the quantity of
	 * an Tool that is in the shop.
	 * @param name the name of the Tool
	 * @param amount the amount by which to decrease
	 * @return a String indicating the success or failure of this operation
	 */
	public String decreaseTool(String name, int amount) {
		if(theInventory.manageTool(name, amount) == null)
			return "Couldn't decrease Tool quantity.\n";
		else
			return "Tool quantity was decreased.\n";
	}
	
	/**
	 * This method is used to reduce the quantity of
	 * an Tool that is in the shop. Overloaded method.
	 * @param id the id of the Tool
	 * @param amount the amount by which to decrease
	 * @return a String indicating the success or failure of this operation
	 * @see Shop#decreaseTool(String, int)
	 */
	public String decreaseTool(int id, int amount) {
		if(theInventory.manageTool(id, amount) == null)
			return "Couldn't decrease Tool quantity.\n";
		else
			return "Tool quantity was decreased.\n";
	}
	
	/**
	 * Displays all of the shop's suppliers.
	 */
	public void listAllSuppliers() {
		theSuppliers.listAllSuppliers();
	}
	
	/**
	 * Gets the SupplierList for the shop.
	 * @return the SupplierList
	 */
	public SupplierList getSupplierList() {
		return theSuppliers;
	}
	
	/**
	 * Used to set the store's supplier list.
	 * @param theSuppliers the new supplier list
	 */
	public void setSupplierList(SupplierList theSuppliers) {
		this.theSuppliers = theSuppliers;
	}
	
	/**
	 * Used to get specified Tool.
	 * @param name the Tool's name
	 * @return the Tool
	 */
	public Tool getTool(String name) {
		Tool theTool = theInventory.searchForTool(name);
		return theTool;
	}
	
	/**
	 * Used to get specified Tool. Overloaded method.
	 * @param id the id number of the desired Tool
	 * @return the Tool
	 * @see Shop#getTool(String)
	 */
	public Tool getTool(int id) {
		Tool theTool = theInventory.searchForTool(id);
		return theTool;
	}
	
	/**
	 * Returns a String representation of an Tool's information.
	 * @param theTool the Tool whose information is sought
	 * @return String of the Tool's information
	 */
	public String outputTool(Tool theTool) {
		return "The Tool information is as follows: \n" + theTool;
	}
	
	/**
	 * Returns a String of the quantity of an Tool in stock.
	 * @param name the name of the Tool
	 * @return the String quantity if the Tool can be found
	 */
	public String getToolQuantity(String name) {
		int quantity = theInventory.getToolQuantity(name);
		if(quantity < 0)
			return "Tool " + name + " could not be found.";
		else
			return "The quantity of Tool " + name + " is: " + quantity + "\n";
	}
	

	/**
	 * Returns a String of the quantity of an Tool in stock.
	 * Overloaded method.
	 * @param id the id number of the Tool
	 * @return the String quantity if the Tool can be found
	 * @see Shop#getToolQuantity(String)
	 */
	public String getToolQuantity(int id) {
		int quantity = theInventory.getToolQuantity(id);
		if(quantity < 0)
			return "Tool " + id + " could not be found.";
		else
			return "The quantity of Tool " + id + " is: " + quantity + "\n";
	}
	
	/**
	 * Returns String representation of the inventory's current order.
	 * @return the order String
	 */
	//public String printOrder() {
	//	return theInventory.printOrder();
	//}
	
	public CustomerList getCustomerList() {
		return theCustomers;
	}
	
	public void setCustomerList(CustomerList c) {
		theCustomers = c;
	}

	/**
	 * Adds customer to shop
	 * @param c the customer
	 */
	public void addCustomer(Customer c) {
		theCustomers.getCustomerArrayList().add(c);
	}

	/**
	 * Deletes customer from shop system
	 * @param id the customer's unique id
	 */
	public void deleteCustomer(int id) {
		for(Customer c: theCustomers.getCustomerArrayList()) {
			if(c.getId() == id) {
				theCustomers.removeCustomer(id);
				return;
			}
		}
	}
	
	
	
}

package commonModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import message.Message;


/**
 *  The Inventory class represents the complete list of tools 
 *  currently in stock and being offered in the retail store program.
 *  It stores a list of the tools and the daily order. Adapted from
 *  lecture and tutorial notes.
 *  
*
 * ENSF 607 Project - November 26, 2020
 * @author Completed by Hunter Kimmett and Lotfi Hasni
 *
 */
public class Inventory {
	
	/**
	 * The complete list of the store's Tools
	 */
	private ArrayList<Tool> tools;
	
	/**
	 * The store's orders
	 */
	private ArrayList<Order> orders;
	
	
	/**
	 * Constructor for Inventory object.
	 * @param ToolsInStore the list of Tools
	 */
	public Inventory(ArrayList<Tool> ToolsInStore, ArrayList<Order> orders) {
		tools = ToolsInStore;
		this.orders = orders;
		//ensureMinimumLevels();
	}

	/**
	 * This method is used to ensure that all Tools in the inventory
	 * meet the minimum quantity level. If an Tool doesn't, a new order line
	 * will be added to the order for that Tool.
	 */
	public void ensureMinimumLevels() {
		if(tools != null) {
			for(Tool i : tools) {
				placeOrder(i,0);
			}
		}
	}

	/**
	 * Sets the inventory's Tool list.
	 * @param ToolList the list of Tools
	 */
	public void setToolList(ArrayList<Tool> ToolList) {
		tools = ToolList;
	}
	
	/**
	 * Gets the inventory's list of Tools.
	 * @return the ArrayList of Tools.
	 */
	public ArrayList<Tool> getToolList(){
		return tools;
	}
	
	/**
	 * This method is used to manage an Tool's quantity by 
	 * calling the decreaseTool() method and, if the Tool 
	 * exists, the placeOrder() method.
	 * @param name the name of the Tool to be decreased
	 * @param amount the amount by which to decrease its quantity
	 * @return the Tool in question
	 */
	public Tool manageTool (String name, int amount) {
		Tool theTool = decreaseTool(name, amount);
		
		if(theTool != null) {
			placeOrder(theTool, amount);
		}
		return theTool;
	}
	
	/**
	 * Decreases the quantity of the specified Tool.
	 * @param name the name of the Tool to be decreased
	 * @param amount the amount by which to decrease the Tool
	 * @return the Tool in question
	 */
	private Tool decreaseTool(String name, int amount) {
		Tool theTool = searchForTool(name);
		
		if(theTool == null) {
			return null;
		}
		
		if(theTool.decreaseToolQuantity(amount) == true) {
			return theTool;
		}
		return null;
	}
	
	
	/**
	 * This method is used to manage an Tool's quantity by 
	 * calling the decreaseTool() method and, if the Tool 
	 * exists, the placeOrder() method. Overloaded method.
	 * @param id the id number of the Tool
	 * @param amount the amount by which to decrease the Tool's quantity
	 * @return the Tool in question
	 * @see Inventory#manageTool(String, int)
	 */
	public Tool manageTool (int id, int amount) {
		Tool theTool = decreaseTool(id, amount);
		
		if(theTool != null) {
			placeOrder(theTool, amount);
		}
		return theTool;
	}
	
	/**
	 * Decreases the quantity of the specified Tool. Overloaded method.
	 * @param id the id number of the Tool to be decreased
	 * @param amount the amount by which to decrease the Tool
	 * @return the Tool in question
	 * @see Inventory#decreaseTool(String, int)
	 */
	private Tool decreaseTool(int id, int amount) {
		Tool theTool = searchForTool(id);
		
		if(theTool == null) {
			return null;
		}
		
		if(theTool.decreaseToolQuantity(amount) == true) {
			return theTool;
		}
		return null;
	}
	

	/**
	 * Searches for and returns the specified Tool
	 * in the inventory.
	 * @param name the name of the Tool being searched for
	 * @return the Tool, if found
	 */
	public Tool searchForTool(String name) {
		for(Tool i: tools) {
			if(i.getToolName().equals(name))
				return i;
		}
		return null;
	}
	
	/**
	 * Searches for and returns the specified Tool
	 * in the inventory. Overloaded method.
	 * @param id the ID number of the Tool being searched for
	 * @return the Tool, if found
	 * @see Inventory#searchForTool(String)
	 */
	public Tool searchForTool(int id) {
		for(Tool i: tools) {
			if(i.getToolId() == id)
				return i;
		}
		
		return null;
	}

	
	/**
	 * Used to retrieve current day's date.
	 * @return the date
	 */
	public String retrieveDate() {
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = date.format(new Date());
		return formattedDate;
	}
	
	
	/**
	 * Creates order line with given tool and places it in order.
	 * If an order line already exists, updates it.
	 * @param theTool the Tool to be ordered
	 * @param amount the quantity to order
	 */
	public Message placeOrder(Tool theTool, int amount) {
		
		String currentDate = retrieveDate();
		
		// 1. Check if current day order exists
		for(Order o : orders) {
			if(o.getDate().equals(currentDate)) {
				ArrayList<OrderLine> currentOrderOrderLines = o.getOrderLines();
				// 2 Check if there is already an orderline for that item
				for(OrderLine orderLine : currentOrderOrderLines) {
					if(orderLine.getToolId() == theTool.getID()) {
						orderLine.setQuantity(orderLine.getQuantity() + amount);
						Message orderType = new Message();
						orderType.setTheOrderLine(orderLine);
						orderType.setTheOrder(o);
						orderType.setSelection(1);
						return orderType;
				}
				}
				
				OrderLine ol = theTool.generateOrderLine();
				if(ol != null) {
					o.addOrderLine(ol);
					ol.setOrderId(o.getId());
					Message orderType = new Message();
					orderType.setTheOrderLine(ol);
					orderType.setTheOrder(o);
					orderType.setSelection(2);
					return orderType;
				}
			}
		}
		// 3. Otherwise create new order and add orderline
		ArrayList<OrderLine> newOrderLinesList = new ArrayList<OrderLine>();
		OrderLine newOrderLine = theTool.generateOrderLine();
		newOrderLinesList.add(newOrderLine);
		Order newOrder = new Order(newOrderLinesList);
		newOrderLine.setOrderId(newOrder.getId());
		orders.add(newOrder);
		Message orderType = new Message();
		orderType.setTheOrder(newOrder);
		orderType.setTheOrderLine(newOrderLine);
		orderType.setSelection(3);
		return orderType;
		
	}
	
	
	/**
	 * Used to clear the inventory of all Tools.
	 */
	public void clearTools() {
		tools.clear();
	}

	/**
	 * Used to add an Tool to the inventory.
	 * @param Tool the Tool to add to inventory
	 */
	public void addTool(Tool tool) {
		tools.add(tool);
	}
	
	/**
	 * Used to delete an Tool from the inventory.
	 * @param id the id number of the Tool
	 * @return the Tool that was removed
	 */
	public Tool deleteTool(int id) {
		Tool tool = searchForTool(id);
		tool.getSupplier().getToolList().remove(tool);
		tools.remove(tool);
		return tool;
	}
	
	/**
	 * Used to delete an Tool from the inventory.
	 * Overloaded method.
	 * @param name the name of the Tool
	 * @return the Tool that was removed
	 * @see Inventory#deleteTool(int)
	 */
	public Tool deleteTool(String name) {
		Tool tool = searchForTool(name);
		tool.getSupplier().getToolList().remove(tool);
		tools.remove(tool);
		return tool;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	/**
	 * Gets the quantity of the specified Tool.
	 * @param name the name of the Tool
	 * @return the Tool's quantity
	 */
	public int getToolQuantity(String name) {
		Tool tool = searchForTool(name);
		if(tool == null) {
			return -1;
		}
		else {
			return tool.getToolQuantity();
		}
		
	}

	/**
	 * Gets the quantity of the specified Tool.
	 * Overloaded method.
	 * @param id the id of the Tool 
	 * @return the Tool's quantity
	 * @see Inventory#getToolQuantity(String)
	 */
	public int getToolQuantity(int id) {
		Tool tool = searchForTool(id);
		if(tool == null) {
			return -1;
		}
		else {
			return tool.getToolQuantity();
		}
	}
	
	/**
	 * Produces a String representation of the complete 
	 * list of Tools in the inventory.
	 * @return String of the Inventory's Tools
	 */
	@Override
	public String toString() {
		StringBuilder buildString = new StringBuilder();
		for(Tool i : tools) {
			buildString.append("\n" + i.toString());
		}
		buildString.append("\n");
		
		return buildString.toString();
	}
}


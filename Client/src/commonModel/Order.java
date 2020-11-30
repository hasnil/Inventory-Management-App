package commonModel;


import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.concurrent.ThreadLocalRandom;

/**
 *  The Order class represents the daily order of tools to replenish
 *  stock for the retail store program. It is responsible for holding
 *  a list of OrderLines.
 *  
 *
 * ENSF 607 Project - November 26, 2020
 * @author Completed by Hunter Kimmett and Lotfi Hasni
 * 
 */
public class Order implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The order lines making up the order
	 */
	private ArrayList<OrderLine> orderLines;
	
	/**
	 * The id number of the order
	 */
	private int orderId;
	
	/**
	 * The date of the order
	 */
	private String date;
	
	/**
	 * Constructor method for Order.
	 */
	public Order(ArrayList<OrderLine> orderLines) {
		this.orderLines = orderLines;
		date = retrieveDate();
		orderId = generateRandomId();
	}
	
	/**
	 * This method is used to get today's date.
	 * @return the date as a String
	 */
	public String retrieveDate() {
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = date.format(new Date());
		return formattedDate;
	}
	
	public ArrayList<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(ArrayList<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	/**
	 * Used to set the order's date.
	 * @param date the new date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * Gets the order's date.
	 * @return the date as a String
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Used to clear order of all order lines.
	 */
	public void clearOrder() {
		orderLines.clear();
	}

	/**
	 * Creates a random ID for the order.
	 * @return the new id number
	 */
	private int generateRandomId() {
		int randNum = ThreadLocalRandom.current().nextInt(10000, 100000);
		return randNum;
	}
	
	/**
	 * Used to get the order's ID number.
	 * @return the ID
	 */
	public int getId() {
		return orderId;
	}
	
	public void setId(int id) {
		orderId = id;
	}
	
	/**
	 * Adds order line to the order.
	 * @param line the OrderLine
	 */
	public void addOrderLine(OrderLine line) {
		orderLines.add(line);
	}
	
	/**
	 * Removes specified order line from the order.
	 * @param name the name of the Tool in the order line
	 */
	public void removeFromOrder(String name) {
		OrderLine line = searchByToolName(name);
		orderLines.remove(line);
	}

	/**
	 * Used to search for specific order line within the order.
	 * @param name the name of the Tool in the order line
	 * @return the chosen OrderLine
	 */
	public OrderLine searchByToolName(String name) {
		for(OrderLine ol : orderLines ) {
			if(ol.getToolDescription() != null && ol.getToolDescription().contains(name)){
				return ol;
			}
		}
		return null;
	}
	
	/**
	 * Used to update the amount being ordered for an existing order line.
	 * @param name the name of the Tool in the order line
	 * @param amount the amount by which to increase the order line's quantity
	 */
	public void updateOrderLine(String name, int amount) {
		OrderLine line = searchByToolName(name);
		line.setQuantity(amount);
	}
	
	/**
	 * Creates and returns a String representation of the entire order.
	 * @return the order as a String
	 */
	@Override
	public String toString() {
		StringBuilder buildString = new StringBuilder("********************************************************\n");
		buildString.append("ORDER ID:\t\t" + orderId + "\nDate Ordered:\t\t" + date + "\n");
		for(OrderLine ol : orderLines) {
			buildString.append("\n" + ol.toString());
		}
		buildString.append("\n********************************************************\n");
		
		return buildString.toString();
	}

}


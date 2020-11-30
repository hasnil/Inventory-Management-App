package commonModel;

import java.io.Serializable;


/**
 *  The OrderLine class represents one line of the retail store
 *  program's daily order. This line contains information for the
 *  tool being ordered, amount ordered, and supplier used.
 *  
 *
 * ENSF 607 Project - November 26, 2020
 * @author Completed by Hunter Kimmett and Lotfi Hasni
 * 
 */
public class OrderLine implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The Tool being ordered
	 */
	private Tool tool;
	
	/**
	 * The quantity being ordered
	 */
	private int quantity;
	
	/**
	 * The supplier who will be filling the order
	 */
	private String supplierName;
	
	/**
	 * The order's unique id
	 */
	private int orderId;
	
	/**
	 * The tool's id
	 */
	private int toolId;
	
	/**
	 * The supplier's id
	 */
	private int supplierId;
	
	
	/**
	 * Constructor method for new OrderLine object.
	 * @param ToolToOrder the Tool being ordered
	 * @param amount the amount
	 */
	public OrderLine(Tool toolToOrder, int amount) {
		tool = toolToOrder;
		quantity = amount;
		supplierName = tool.getSupplier().getCompanyName();
		toolId = tool.getID();
		supplierId = tool.getSupplier().getId();	
	}	

	/**
	 * Gets the amount of the Tool in the order line.
	 * @return the quantity ordered
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * Used to set the amount ordered.
	 * @param quantity the new quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * Gets the supplier's name.
	 * @return the supplier's name
	 */
	public String getSupplierName() {
		return supplierName;
	}
	
	/**
	 * Used to set the supplier's name.
	 * @param supplierName the new supplier name
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	/**
	 * Returns the name/description of the Tool in the order line.
	 * @return the Tool's name
	 */
	public String getToolDescription() {
		return tool.getToolName();
	}
	
	public int getSupplierId() {
		return supplierId;
	}

	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public int getToolId() {
		return toolId;
	}
	
	/**
	 * Creates and returns the order line as a String.
	 * @return line the order line String
	 */
	@Override
	public String toString() {
		String line = "Tool description:\t" + tool.getToolName() + "\nAmount ordered:\t\t" + quantity + 
						"\nSupplier:\t\t" + supplierName + "\n";
		return line;
	}

}
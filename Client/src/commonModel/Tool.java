package commonModel;

import java.io.Serializable;


/**
 *  The Tool class represents a tool, with its attendant traits,
 *  within the retail store program. It also stores the tool's
 *  supplier and is responsible for generating an OrderLine if
 *  the tool's quantity falls below the prescribed minimum. Adapted
 *  from lecture and tutorial notes.
 *  
 *
 * ENSF 607 Project - November 26, 2020
 * @author Completed by Hunter Kimmett and Lotfi Hasni
 *
 */
public abstract class Tool implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The Tool's ID number
	 */
	protected int id;
	
	/**
	 * The name/description of the Tool
	 */
	protected String name;
	
	/**
	 * The quantity of the Tool available
	 */
	protected int quantity;
	
	/**
	 * The purchase price of the Tool
	 */
	protected double price;
	
	/**
	 * Flag variable used to indicate whether this
	 * Tool has already been ordered or not
	 */
	//private boolean alreadyOrdered;
	
	/**
	 * The supplier who provides this Tool
	 */
	protected Supplier theSupplier;
	
	/**
	 * The tool supplier's unique id
	 */
	protected int supplierId;
	
	/**
	 * The tool's type
	 */
	protected String type;
	
	/**
	 * The default number of each Tool that should
	 * be present after an order has been fulfilled
	 */
	private static final int DEFAULTQUANTITY = 50;
	
	/**
	 * The minimum quantity of an Tool that can be in
	 * store before an OrderLine is automatically generated
	 */
	private static final int MINIMUMNUMBER = 40;

	/**
	 * Constructor method for Tool object.
	 * @param id the Tool's id number
	 * @param name the name/description of the Tool
	 * @param quantity the quantity of the Tool in stock
	 * @param price the price of the Tool
	 * @param supplierId the supplier's id
	 */
	public Tool(int id, String name, int quantity, double price, int supplierId) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.supplierId = supplierId;
		//alreadyOrdered = false;
	}
	
	/**
	 * Decreases the tool amount by specified
	 * amount of tools sold.
	 * @param amount the amount to decrease quantity by
	 * @return true if it was possible to reduce the quantity. Otherwise, returns false
	 */
	public boolean decreaseToolQuantity(int amount) {
		if(amount < 0) {
			return false;
		}
		
		if(quantity > 0 && (quantity - amount) >= 0) {
			quantity -= amount;
			return true;
		}
		else
			return false;
	}
	
	/**
	 * This method automatically generates and returns an
	 * OrderLine for the Tool if it falls below the minimum 
	 * quantity and is not yet part of another order.
	 * @return the new OrderLine
	 */
	public OrderLine generateOrderLine() {
		OrderLine ol;
		//if(getToolQuantity() < MINIMUMNUMBER && alreadyOrdered == false) {
		if(getToolQuantity() < MINIMUMNUMBER) {
			ol = new OrderLine(this, DEFAULTQUANTITY - getToolQuantity());
			//alreadyOrdered = true;
			return ol;
		}
		return null;
	}
	
	/**
	 * Gets Tool id number.
	 * @return the id
	 */
	public int getToolId() {
		return id;
	}

	/**
	 * Sets Tool id number.
	 * @param id the id
	 */
	public void setToolId(int id) {
		this.id = id;
	}
	
	/**
	 * Checks whether Tool has already been ordered or not.
	 * @return alreadyOrdered variable indicating order status
	 */
	//public boolean getAlreadyOrdered() {
	//	return alreadyOrdered;
	//}

	/**
	 * Gets the Tool's name/description.
	 * @return Tool name
	 */
	public String getToolName() {
		return name;
	}
	
	/**
	 * Used to set Tool's name.
	 * @param name the new name
	 */
	public void setToolName(String name) {
		this.name = name;
	}

	/**
	 * Gets current quantity of Tool in store.
	 * @return the quantity
	 */
	public int getToolQuantity() {
		return quantity;
	}

	/**
	 * Used to set the quantity of the Tool in store.
	 * @param quantity the new quantity
	 */
	public void setToolQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets the Tool's price.
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Used to set the price of the Tool.
	 * @param price the new price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Gets the Tool's supplier.
	 * @return the supplier
	 */
	public Supplier getSupplier() {
		return theSupplier;
	}

	/**
	 * Used to set the Tool's supplier.
	 * @param theSupplier the new supplier
	 */
	public void setSupplier(Supplier theSupplier) {
		this.theSupplier = theSupplier;
	}
	
	/**
	 * Creates and returns a String representation of the Tool.
	 * @return the Tool as a String
	 */
	@Override
	public String toString(){
		return "" + id + " " + name + " " + quantity + " " + price + " " + supplierId;
	}
	
	/**
	 * Used to increase the Tool quantity by the specified amount.
	 * @param amount the amount to increase by
	 */
	public void increaseToolQuantity(int amount) {
		quantity = quantity + amount;
	}

	public int getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getSupplierID() {
		return supplierId;
	}
	
	public String getType() {
		return type;
	}

	public abstract String getPowerType();
}

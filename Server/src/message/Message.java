package message;


import java.io.Serializable;
import java.util.ArrayList;

import commonModel.Customer;
import commonModel.Order;
import commonModel.OrderLine;
import commonModel.Tool;


/**
 * The Message class is used for communicating information
 * and objects between client and server sides, as well as
 * for other smaller uses.
 * 
 * ENSF 607 Project - November 26, 2020
 * @author Completed by Hunter Kimmett and Lotfi Hasni
 *
 */
public class Message implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String theInstructions;
	private String moreInstructions;
	private Tool theTool;
	private Customer theCustomer;
	private Order theOrder;
	private OrderLine theOrderLine;
	private ArrayList<Tool> toolsList;
	private int selection;
	private int id;
	private ArrayList<Customer> customersList;
	private char type;
	private int amount;
	private boolean result;
	
	public ArrayList<Customer> getCustomersList() {
		return customersList;
	}

	public void setCustomersList(ArrayList<Customer> customersList) {
		this.customersList = customersList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Message() {}
	
	public Message(String theInstructions, Tool theTool, Customer theCustomer, Order theOrder) {
		this.theInstructions = theInstructions;
		this.theTool = theTool;
		this.theCustomer = theCustomer;
		this.theOrder = theOrder;
	}
	
	public void clear() {
		theInstructions = null;
		theTool = null;
		theCustomer = null;
		theOrder = null;
	}
	
	public int getSelection() {
		return selection;
	}
	
	public void setSelection(int n) {
		selection = n;
	}
	public String getInstructions() {
		return theInstructions;
	}
	public void setInstructions(String instructions) {
		this.theInstructions = instructions;
	}
	public Tool getTheTool() {
		return theTool;
	}
	public void setTheTool(Tool theTool) {
		this.theTool = theTool;
	}
	public Customer getTheCustomer() {
		return theCustomer;
	}
	public void setTheCustomer(Customer theCustomer) {
		this.theCustomer = theCustomer;
	}
	public Order getTheOrder() {
		return theOrder;
	}
	public void setTheOrder(Order theOrder) {
		this.theOrder = theOrder;
	}
	
	public ArrayList<Tool> getToolsList(){
		return toolsList;
	}
	
	public void setToolsList(ArrayList<Tool> t) {
		toolsList = t;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public OrderLine getTheOrderLine() {
		return theOrderLine;
	}

	public void setTheOrderLine(OrderLine theOrderLine) {
		this.theOrderLine = theOrderLine;
	}

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMoreInstructions() {
		return moreInstructions;
	}

	public void setMoreInstructions(String moreInstructions) {
		this.moreInstructions = moreInstructions;
	}
	

}

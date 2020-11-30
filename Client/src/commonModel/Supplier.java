package commonModel;

import java.io.Serializable;
import java.util.ArrayList;


/**
 *  The Supplier class represents a supplier of certain
 *  specific tools within the retail store program. It is
 *  responsible for holding a list of these tools.
 *  
 *
 * ENSF 607 Project - November 26, 2020
 * @author Completed by Hunter Kimmett and Lotfi Hasni
 *
 */
public abstract class Supplier implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * List of the Tools that this supplier provides
	 */
	protected ArrayList<Tool> toolList;

	/**
	 * The supplier's ID number
	 */
	protected int id;
	
	/**
	 * The supplier's company name
	 */
	protected String companyName;
	
	/**
	 * The supplier's business address
	 */
	protected String address;
	
	/**
	 * The name of the sales contact at the supplier
	 */
	protected String salesContact;
	
	/**
	 * The supplier's type
	 */
	protected String type;
	
	/**
	 * The supplier's phone number
	 */
	protected String phoneNumber;
	
	
	/**
	 * Constructs a Supplier object with the 
	 * specified values from the given parameters.
	 * @param id the supplier's ID number
	 * @param name the supplier's name
	 * @param address the supplier's address
	 * @param contact the name of the supplier's contact person
	 */
	public Supplier(int id, String name, String address, String contact, String phoneNumber) {
		toolList = new ArrayList<Tool> ();
		this.id = id;
		companyName = name;
		this.address = address;
		salesContact = contact;
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * Used to get the list of Tools provided by supplier.
	 * @return full list of Tools
	 */
	public ArrayList<Tool> getToolList(){
		return toolList;
	}
	
	/**
	 * Used to set the list of Tools provided by supplier. May be used
	 * to update Supplier object if the list of Tools they provide changes.
	 * @param ToolList the new list of Tools provided by this supplier
	 */
	public void setToolList(ArrayList<Tool> ToolList) {
		this.toolList = ToolList;
	}

	/**
	 * Gets supplier ID.
	 * @return the supplier's ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets supplier's ID.
	 * @param id the new ID number for the supplier
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the supplier's company name.
	 * @return the company name
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * Sets the supplier's company name.
	 * @param companyName the new company name
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * Gets the supplier's address.
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the supplier's address.
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the name of the supplier's sales contact.
	 * @return the sales contact's name
	 */
	public String getSalesContact() {
		return salesContact;
	}
	
	/**
	 * Sets the name of the supplier's sales contact.
	 * @param salesContact the name of the new sales contact
	 */
	public void setSalesContact(String salesContact) {
		this.salesContact = salesContact;
	}
	
	/**
	 * Used to add tool to supplier's collection
	 * @param tool the tool
	 */
	public void addTool(Tool tool) {
		toolList.add(tool);
	}
	

	/**
	 * Returns a String representation of the supplier's information.
	 * @return String of the supplier's information
	 */
	@Override
	public String toString() {
		return "ID: " + id + "; Company Name: " + companyName + "; Address: " + address + "; Sales Contact: " + salesContact;
	}
}

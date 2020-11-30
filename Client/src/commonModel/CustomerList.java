package commonModel;

import java.util.ArrayList;

/**
 * ENSF 607 Project - November 26, 2020
 * @author Completed by Hunter Kimmett and Lotfi Hasni
 *
 */
public class CustomerList {
	
	private ArrayList<Customer> customerList;
	
	public CustomerList(ArrayList<Customer> customersForStore) {
		customerList = customersForStore;
	}
	
	public ArrayList<Customer> getCustomerArrayList(){
		return customerList;
	}
	
	public void addCustomer(int id, String firstName, String lastName, String address, String postalCode, String phoneNumber, char type) {
		
		if(type == 'R') {
			ResidentialCustomer r = new ResidentialCustomer(id, firstName, lastName, address, postalCode, phoneNumber, type);
			customerList.add(r);
			return;
		}
		else if(type == 'C') {
			CommercialCustomer c = new CommercialCustomer(id, firstName, lastName, address, postalCode, phoneNumber, type);
			customerList.add(c);
			return;
		}
	}
	
	public ArrayList<Customer> searchForCustomer(int id) {
		ArrayList<Customer> arr = new ArrayList<Customer>();
		for(Customer c : customerList) {
			if(c.getId() == id) {
				arr.add(c);
			}
		}
		return arr;
	}
	
	public ArrayList<Customer> searchForCustomer(String lastName) {
		ArrayList<Customer> arr = new ArrayList<Customer>();
		for(Customer c : customerList) {
			if(c.getLastName().equals(lastName)) {
				arr.add(c);
			}
		}
		return arr;
	}

	public ArrayList<Customer> searchForCustomer(char type) {
		ArrayList<Customer> arr = new ArrayList<Customer>();
		for(Customer c : customerList) {
			if(c.getType() == type) {
				arr.add(c);
			}
		}
		return arr;
	}
	
	public void removeCustomer(int id) {
		
		for(Customer c : customerList) {
			if(c.getId() == id) {
				customerList.remove(c);
				return;
			}
		}
		
		return;
	}
	
	public void modifyCustomerint (int id, String firstName, String lastName, String address, String postalCode, String phoneNumber, char type) {
		removeCustomer(id);
		addCustomer(id, firstName, lastName, address, postalCode, phoneNumber, type);
	}


}

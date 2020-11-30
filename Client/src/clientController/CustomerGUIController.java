package clientController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import clientView.CustomerGUI;
import commonModel.Customer;


/**
 * ENSF 607 Project - November 26, 2020
 * @author Completed by Hunter Kimmett and Lotfi Hasni
 *
 */
public class CustomerGUIController {

	private CustomerGUI theView;
	private ModelController theModel;
	
	public CustomerGUIController(CustomerGUI theView, ModelController theModel) {
		this.theView = theView;
		this.theModel = theModel;
		theView.addSearchListener(new SearchListener());
		theView.addClearSearchListener(new ClearSearchListener());
		theView.addSaveListener(new SaveListener());
		theView.addDeleteListener(new DeleteListener());
		theView.addClearFieldsListener(new ClearFieldsListener());
		theView.addListSelectionListener(new ListListener());	
	}
	
	class SearchListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<Customer> custList;
			DefaultListModel<String> listModel = new DefaultListModel<String>();
			theView.setList(listModel);
			try {
				
				if (theView.getSearchType() == "Client ID") {
					custList = theModel.searchCustomersById(Integer.parseInt(theView.getSearchText()));
				} else if (theView.getSearchType() == "Last Name") {
					custList = theModel.searchCustomersByName(theView.getSearchText());
				} else if (theView.getSearchType() == "Client Type") {
					custList = theModel.searchCustomersByType(theView.getSearchText().charAt(0));
				} else {
					return;
				}
				
				for (Customer i:custList) {
					listModel.addElement(i.getId() + ", " + i.getFirstName() + " " + i.getLastName() + ", " + i.getType());
				}
				
				theView.setList(listModel);
			}catch (Exception ex) {
				theView.displayErrorMessage("Ensure input is correct. ID must be an integer, Type must be C or R. ");
			}
			
		}
		
	}
	
	class ClearSearchListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			DefaultListModel<String> listModel = new DefaultListModel<String>();
			
			try {
				theView.setList(listModel);
			}catch (Exception ex) {
				theView.displayErrorMessage("Something went wrong!");
			}
		}
	}
	
	class ListListener implements ListSelectionListener{


		@Override
		public void valueChanged(ListSelectionEvent e) {
			try {
				String val = (String)theView.getListSelection();
				
				if(val != null) {
					int id = Integer.parseInt(val.split(",")[0]);
					
					Customer cust = theModel.searchCustomersById(id).get(0);
					
					theView.setIDText(""+cust.getId());
					theView.setFNameText(cust.getFirstName());
					theView.setLNameText(cust.getLastName());
					theView.setAddressText(cust.getAddress());
					theView.setPostalText(cust.getPostalCode());
					theView.setPhoneText(cust.getPhoneNumber());
				}
			}catch (Exception ex) {
				theView.displayErrorMessage("Something went wrong!");
			}
			
		}
		
	}
	
	class SaveListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				
				int custID = theView.getIDText();
				String fName = theView.getFNameText();
				String lName = theView.getLNameText();
				char custType = theView.getTypeSpinType().charAt(0);
				String address = theView.getAddressText();
				String postal = theView.getPostalText();
				String phone = theView.getPhoneText();
			
				ArrayList<Customer> custList = theModel.searchCustomersById(custID);
				
				if(custList.size() != 0) {
					theModel.updateCustomer(custID, lName, fName, custType, phone, address, postal);
					theView.displayErrorMessage("Customer updated.");
				}
				else {
					theModel.addCustomer(custID, lName, fName, custType, phone, address, postal);
					theView.displayErrorMessage("Customer added.");
				}
			
			}catch (Exception ex) {
				theView.displayErrorMessage("Ensure all data is in the correct format!");
			}
		}
	}
	
	class DeleteListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String val = (String)theView.getListSelection();
				int id = Integer.parseInt(val.split(",")[0]);
				theModel.deleteCustomer(id);
				theView.displayErrorMessage("Customer deleted.");
			}catch (Exception ex) {
				theView.displayErrorMessage("Ensure you have a customer selected.");
			}
			
		}
		
	}
	
	
	class ClearFieldsListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				
				theView.setIDText("");
				theView.setFNameText("");
				theView.setLNameText("");
				theView.setAddressText("");
				theView.setPostalText("");
				theView.setPhoneText("");
				
			}catch (Exception ex) {
				theView.displayErrorMessage("Something went wrong.");
			}
		}
	}
}

package clientController;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import commonModel.*;
import message.Message;

/**
 * ENSF 607 Project - November 26, 2020
 * @author Completed by Hunter Kimmett and Lotfi Hasni
 *
 */
public class ModelController {
	
	private MainClientController clientController;
	private Serializer serializer;
	private Deserializer deserializer;
	private int selection = 0;
	private Message theMessage;
	
	public ModelController(MainClientController clientController, Serializer serializer, Deserializer deserializer){
		this.clientController = clientController;
		this.serializer = serializer;
		this.deserializer = deserializer;
	}
	
	public ModelController(){
		this.clientController = new MainClientController();
		this.serializer = new Serializer(clientController.getServerCommunicator());
		this.deserializer = new Deserializer(clientController.getServerCommunicator());
	}
	
	public void setMessage(Message m) {
		theMessage = m;
	}
	
	public Message getMessage() {
		return theMessage;
	}
	
	public int getSelection() {
		return selection;
	}
	
	public void setSelection(int selection) {
		this.selection = selection;
	}
	
	
	// Communication methods ------------------------------------
	
	public ArrayList<Tool> searchForToolByName(String name) {
		Message message = new Message();
		message.setSelection(1);
		message.setInstructions(name);
		serializer.sendObject(message);
		Message response = deserializer.receiveResponse();
		return response.getToolsList();
	}
	
	public ArrayList<Tool> listAllTools() {
		Message message = new Message();
		message.setSelection(2);
		serializer.sendObject(message);
		Message response = deserializer.receiveResponse();
		return response.getToolsList();
	}
	
	public ArrayList<Tool> searchForToolById(int id) {
		Message message = new Message();
		message.setSelection(3);
		message.setId(id);
		serializer.sendObject(message);
		Message response = deserializer.receiveResponse();
		return response.getToolsList();
	}

	public int checkItemQuantity(int id, String name) {
		return searchForToolIdAndName(id, name).getToolQuantity();
	}
	
	public boolean addTool(int toolId, String name, String type, int quantity, double price, int supplierId, String powerType) {
		Message message = new Message();
		message.setSelection(6);
		if(type.equals("Electrical")) {
			ElectricalTool newTool = new ElectricalTool(toolId, name, quantity, price, supplierId, powerType);
			message.setSelection(6);
			message.setTheTool(newTool);
			serializer.sendObject(message);
			Message response = deserializer.receiveResponse();
			boolean result = response.getResult();
			return result;
		}
		else if(type.equals("Non-Electrical")) {
			NonElectricalTool newTool = new NonElectricalTool(toolId, name, quantity, price, supplierId);
			message.setSelection(6);
			message.setTheTool(newTool);
			serializer.sendObject(message);
			Message response = deserializer.receiveResponse();
			boolean result = response.getResult();
			return result;
		}
		return false;
	}
	
	public void deleteTool(int id) {
		Message message = new Message();
		message.setSelection(7);
		message.setId(id);
		serializer.sendObject(message);
	}
	
	public void addCustomer(int clientId, String lName, String fName, char type, String phoneNum, String address, String postalCode) {
		Message message = new Message();
		message.setSelection(8);
		if(type == 'C') {
			CommercialCustomer newCustomer = new CommercialCustomer(clientId, fName, lName, address, postalCode, phoneNum, type);
			message.setTheCustomer(newCustomer);
			serializer.sendObject(message);
		}
		else if(type == 'R') {
			ResidentialCustomer newCustomer = new ResidentialCustomer(clientId, fName, lName, address, postalCode, phoneNum, type);
			message.setTheCustomer(newCustomer);
			serializer.sendObject(message);
		}
	}
		
	public void deleteCustomer(int customerId) {
		Message message = new Message();
		message.setSelection(9);
		message.setId(customerId);
		serializer.sendObject(message);
	}
	
	public void updateCustomer(int clientId, String lName, String fName, char type, String phoneNum, String address, String postalCode) {
		deleteCustomer(clientId);
		addCustomer(clientId, lName, fName, type, phoneNum, address, postalCode);
	}
	
	public ArrayList<Customer> searchCustomersById(int id) {
		Message message = new Message();
		message.setSelection(11);
		message.setId(id);
		serializer.sendObject(message);
		Message response = deserializer.receiveResponse();
		return response.getCustomersList();
		
	}
	
	public ArrayList<Customer> searchCustomersByName(String lastName) {
		Message message = new Message();
		message.setSelection(12);
		message.setInstructions(lastName);
		serializer.sendObject(message);
		Message response = deserializer.receiveResponse();
		return response.getCustomersList();
	}
	
	public ArrayList<Customer> searchCustomersByType(char c) {
		Message message = new Message();
		message.setSelection(13);
		message.setType(c);
		serializer.sendObject(message);
		Message response = deserializer.receiveResponse();
		return response.getCustomersList();
	}
	
	public void decreaseToolQuantity(int id, int amount) {
		Message message = new Message();
		message.setSelection(14);
		message.setId(id);
		message.setAmount(amount);
		serializer.sendObject(message);		
	}

	public void decreaseToolQuantity(String name, int amount) {
		Message message = new Message();
		message.setSelection(15);
		message.setInstructions(name);
		message.setAmount(amount);
		serializer.sendObject(message);		
	}
	
	public void writeToFile(Order o) {
		try{
			FileWriter write = new FileWriter("orders.txt");
            BufferedWriter buffered = new BufferedWriter(write);
            buffered.write(o.toString());
            buffered.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void writeLatestOrderToFile() {
		Message message = new Message();
		message.setSelection(16);
		serializer.sendObject(message);
		Message response = deserializer.receiveResponse();
		writeToFile(response.getTheOrder());		
	}
	
	public Tool searchForToolIdAndName(int id, String name) {
		Message message = new Message();
		message.setSelection(17);
		message.setId(id);
		message.setInstructions(name);
		serializer.sendObject(message);
		Message response = deserializer.receiveResponse();
		return response.getTheTool();
	}
	
	public boolean checkUsernameAndPassword(String username, String password) {
		Message message = new Message();
		message.setSelection(18);
		message.setInstructions(username);
		message.setMoreInstructions(password);
		serializer.sendObject(message);
		Message response = deserializer.receiveResponse();
		return response.getResult();
	}

	
}


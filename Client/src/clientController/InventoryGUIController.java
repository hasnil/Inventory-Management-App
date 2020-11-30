package clientController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import clientView.InventoryGUI;
import commonModel.Tool;

/**
 * ENSF 607 Project - November 26, 2020
 * @author Completed by Hunter Kimmett and Lotfi Hasni
 *
 */
public class InventoryGUIController {
	
	private InventoryGUI theView;
	private ModelController theModel;
	
	
	public InventoryGUIController(InventoryGUI theView, ModelController theModel) {
		this.theView = theView;
		this.theModel = theModel;
		theView.addListAllListener(new ListAllListener());
		theView.addSearchListener(new SearchListener());
		theView.addClearSearchListener(new ClearSearchListener());
		theView.addCheckQtyListener(new CheckQtyListener());
		theView.addDecreaseQtyListener(new DecreaseQtyListener());
		theView.addListSelectionListener(new ListListener());
		theView.addSaveListener(new SaveListener());
		theView.addDeleteListener(new DeleteListener());
		theView.addOrderListener(new OrderListener());
		theView.addToolTypeListener(new toolTypeListener());
		
	}
	
	public InventoryGUIController(InventoryGUI theView) {
		this.theView = theView;
		theView.addListAllListener(new ListAllListener());
		
	}
	
	class ListAllListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<Tool> toolList = theModel.listAllTools();
			DefaultListModel<String> listModel = new DefaultListModel<String>();
			
			for (Tool i:toolList) {
				listModel.addElement(i.getID() + ",\t" + i.getName());
			}
			
			try {
				theView.setList(listModel);
				theView.getSearchButton().setEnabled(false);
			}catch (Exception ex) {
				theView.displayErrorMessage("Something went wrong!");
			}
			
		}
		
	}
	
	class SearchListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<Tool> toolList;
			
			try {
				
				if (theView.getSearchType() == "Name") {
					toolList = theModel.searchForToolByName(theView.getSearchText());
				} else if (theView.getSearchType() == "ID") {
					toolList = theModel.searchForToolById(Integer.parseInt(theView.getSearchText()));
				} else {
					return;
				}
				
				DefaultListModel<String> listModel = new DefaultListModel<String>();
				
				for (Tool i:toolList) {
					listModel.addElement(i.getID() + ",\t" + i.getName());
				}
				
				theView.setList(listModel);
				theView.getSearchButton().setEnabled(false);
			}catch (Exception ex) {
				theView.displayErrorMessage("Ensure input is correct, ID must be an integer.");
			}
			
		}
		
	}
	
	class ClearSearchListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			DefaultListModel<String> listModel = new DefaultListModel<String>();
			theView.getSearchButton().setEnabled(true);
			try {
				theView.setList(listModel);
				theView.setIDText("");
				theView.setNameText("");
				theView.setQtyText("");
				theView.setPriceText("");
				theView.setSupText("");
				theView.setTypeSpinType("Non-Electrical");
				theView.setPowerTypeField("");
			}catch (Exception ex) {
				theView.displayErrorMessage("Something went wrong!");
			}
			
		}
		
	}
	
	class CheckQtyListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				String val = (String)theView.getListSelection();
				int id = Integer.parseInt(val.split(",")[0]);
				String name = val.split(",")[1];
				
				int qty  = theModel.checkItemQuantity(id, name);
				theView.setQuantityTextPane(qty);
			}catch (Exception ex) {
				theView.displayErrorMessage("Ensure you have a tool selected.");
			}
			
		}
		
	}
	
	class DecreaseQtyListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String val = (String)theView.getListSelection();
				int id = Integer.parseInt(val.split(",")[0]);
				String name = val.split(",")[1];
				int qtyBeforeDecrease  = theModel.checkItemQuantity(id, name);
				if(qtyBeforeDecrease <= 0){
					theView.displayErrorMessage("That quantity is already at zero!");
					return;
				}
				
				theModel.decreaseToolQuantity(id, 1);
				
				int qty  = theModel.checkItemQuantity(id, name);
				theView.setQuantityTextPane(qty);
				theView.displayDecreaseMessage();
			}catch (Exception ex) {
				theView.displayErrorMessage("Ensure you have a tool selected.");
			}
			
		}
		
	}
	
	class ListListener implements ListSelectionListener{


		@Override
		public void valueChanged(ListSelectionEvent e) {
			try {
				
				if(theView.getListSelection()== null) {
					return;
				}
				
				String val = (String)theView.getListSelection();
				int id = Integer.parseInt(val.split(",")[0]);
				String name = val.split(",")[1];
				
				
				Tool tool = theModel.searchForToolIdAndName(id, name);
				
				theView.setIDText(""+tool.getID());
				theView.setNameText(tool.getName());
				theView.setQtyText(""+tool.getQuantity());
				theView.setPriceText(""+tool.getPrice());
				theView.setSupText(""+tool.getSupplierID());
				theView.setTypeSpinType(tool.getType());
				
				if(tool.getType().equals("Electrical")) {
					theView.setPowerTypeField(tool.getPowerType());
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
				
				int toolID = theView.getIDText();
				String toolName = theView.getNameText();
				int toolQTY = theView.getQtyText();
				double toolPrice = theView.getPriceText();
				int toolSup = theView.getSupText();
				String toolType = theView.getTypeSpinType();
				
				ArrayList<Tool> toolList = theModel.listAllTools();
				for(Tool t : toolList) {
					if(t.getID() == toolID) {
						theModel.deleteTool(toolID); //Will update new tool with same id
					}
				}
				
				boolean result;
				if(toolType.equals("Electrical")) {
					String powerType = theView.getPowerType();
					 result = theModel.addTool(toolID, toolName, toolType, toolQTY, toolPrice, toolSup, powerType);
				}
				else {
					result = theModel.addTool(toolID, toolName, toolType, toolQTY, toolPrice, toolSup, null);
				}
				if(result == false) {
					theView.displayErrorMessage("Tool could not be added. No matching supplier ID.");
				}
				else {
					theView.displayErrorMessage("Tool added.");
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
				theModel.deleteTool(id);
				theView.displayErrorMessage("Tool deleted.");
			}catch (Exception ex) {
				theView.displayErrorMessage("Ensure you have a tool selected.");
			}
			
		}
		
	}
	
	class OrderListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				theModel.writeLatestOrderToFile();
				theView.displayErrorMessage("Order successfully written to text file: orders.txt.");
			}catch (Exception ex) {
				theView.displayErrorMessage("No latest order exists.");
			}
			
		}
		
	}
	
	class toolTypeListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) {
			try {
				String type = theView.getTypeSpinType();
				if(type.equals("Non-Electrical")) {
					theView.getPowerTypeField().setEnabled(false);
				}
				else {
					theView.getPowerTypeField().setEnabled(true);
				}
			}catch (Exception ex) {
				
			}
		}
		
	}
}

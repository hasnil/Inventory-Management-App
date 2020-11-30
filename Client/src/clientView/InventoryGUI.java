package clientView;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;

import java.awt.event.MouseAdapter;

import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;


/**
 * ENSF 607 Project - November 26, 2020
 * @author Completed by Hunter Kimmett and Lotfi Hasni
 *
 */
public class InventoryGUI {

	private JFrame frame;
	private JTextField searchTextField;	// Search
	private JTextField idTextField;		// ID
	private JTextField nameTextField;	// Name
	private JTextField qtyTextField; 	// Quantity
	private JTextField priceTextField; 	// Price
	private JTextField supTextField; 	// SupplierID
	private JSpinner searchSpinner;		// Search Type
	private JSpinner typeSpinner;		// Tool Type
	private JTextField powerTypeField;  // Power Type
	private JButton listAllButton;		// List all
	private JButton searchButton; 		// Search
	private JButton clearSearchButton; 	// Clear Search
	private JButton checkQtyButton; 	// Check QTY
	private JButton decreaseQtyButton; 	// Decrease QTY
	private JButton saveButton; 		// Save
	private JButton deleteButton; 		// Delete
	private JButton writeOrderButton;   // Print order
	private JList<String> list;			// List
	private JTextPane qtyTextPane;		// Quantity
	

	public InventoryGUI() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("InventoryGUI");
		frame.setBounds(100, 100, 850, 395);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 506, 39);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Search Inventory");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(524, 6, 320, 39);
		frame.getContentPane().add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Add/Delete Item");
		panel_1.add(lblNewLabel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 38, 506, 39);
		frame.getContentPane().add(panel_2);
		
		listAllButton = new JButton("List All");
		panel_2.add(listAllButton);
		
		JLabel lblNewLabel_2 = new JLabel("Search:");
		panel_2.add(lblNewLabel_2);
		
		searchTextField = new JTextField("ID or Name");
		panel_2.add(searchTextField);
		searchTextField.setColumns(10);
		searchTextField.addMouseListener(new MouseAdapter(){
	        @Override
	        public void mouseClicked(MouseEvent e){
	        	searchTextField.setText("");
	        }
	    });
		
		JLabel lblNewLabel_3 = new JLabel("Type:");
		panel_2.add(lblNewLabel_3);
		
		SpinnerListModel typelist = new SpinnerListModel(new String[] {"Name", "ID"});
		searchSpinner = new JSpinner(typelist);
		panel_2.add(searchSpinner);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(6, 76, 506, 39);
		frame.getContentPane().add(panel_3);
		
		searchButton = new JButton("Search");
		panel_3.add(searchButton);
		
		clearSearchButton = new JButton("Clear Search");
		panel_3.add(clearSearchButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 114, 506, 199);
		frame.getContentPane().add(scrollPane);
		
		list = new JList<String>();
		scrollPane.setViewportView(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(6, 325, 506, 42);
		frame.getContentPane().add(panel_4);
		
		checkQtyButton = new JButton("Check Qty.");
		panel_4.add(checkQtyButton);
		
		decreaseQtyButton = new JButton("Decrease Qty.");
		panel_4.add(decreaseQtyButton);
		
		JLabel lblNewLabel_4 = new JLabel("Quantity:");
		panel_4.add(lblNewLabel_4);
		
		qtyTextPane = new JTextPane();
		qtyTextPane.setText("             ");
		panel_4.add(qtyTextPane);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(524, 38, 320, 42);
		frame.getContentPane().add(panel_5);
		
		JLabel lblNewLabel_5 = new JLabel("ID");
		panel_5.add(lblNewLabel_5);
		
		idTextField = new JTextField();
		panel_5.add(idTextField);
		idTextField.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(510, 84, 320, 42);
		frame.getContentPane().add(panel_6);
		
		JLabel lblNewLabel_6 = new JLabel("Type");
		panel_6.add(lblNewLabel_6);
		
		SpinnerListModel typelist_1 = new SpinnerListModel(new String[] {"Non-Electrical", "Electrical"});
		typeSpinner = new JSpinner(typelist_1);
		((JSpinner.DefaultEditor)typeSpinner.getEditor()).getTextField().setColumns(8);
		panel_6.add(typeSpinner);
		
		
		
		JPanel panel_6_5 = new JPanel();
		panel_6_5.setBounds(500, 131, 320, 42);
		frame.getContentPane().add(panel_6_5);
		
		JLabel lblNewLabel_6_5 = new JLabel("PowerType");
		panel_6_5.add(lblNewLabel_6_5);
		
		powerTypeField = new JTextField();
		panel_6_5.add(powerTypeField);
		powerTypeField.setColumns(10);
		powerTypeField.setEnabled(false);
		
		
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(500, 163, 320, 42);
		frame.getContentPane().add(panel_7);
		
		JLabel lblNewLabel_7 = new JLabel("Name/Description");
		panel_7.add(lblNewLabel_7);
		
		nameTextField = new JTextField();
		panel_7.add(nameTextField);
		nameTextField.setColumns(10);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBounds(510, 195, 320, 42);
		frame.getContentPane().add(panel_8);
		
		JLabel lblNewLabel_8 = new JLabel("Quantity");
		panel_8.add(lblNewLabel_8);
		
		qtyTextField = new JTextField();
		panel_8.add(qtyTextField);
		qtyTextField.setColumns(10);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBounds(517, 230, 320, 42);
		frame.getContentPane().add(panel_9);
		
		JLabel lblNewLabel_9 = new JLabel("Price");
		panel_9.add(lblNewLabel_9);
		
		priceTextField = new JTextField();
		panel_9.add(priceTextField);
		priceTextField.setColumns(10);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBounds(504, 271, 320, 42);
		frame.getContentPane().add(panel_10);
		
		JLabel lblNewLabel_10 = new JLabel("Supplier ID");
		panel_10.add(lblNewLabel_10);
		
		supTextField = new JTextField();
		panel_10.add(supTextField);
		supTextField.setColumns(10);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBounds(524, 325, 320, 42);
		frame.getContentPane().add(panel_11);
		
		saveButton = new JButton("Save");
		panel_11.add(saveButton);
		
		deleteButton = new JButton("Delete");
		panel_11.add(deleteButton);
		
		writeOrderButton = new JButton("Write Current Order");
		panel_11.add(writeOrderButton);
	}
	
	public String getPowerType() {
		return powerTypeField.getText();
	}
	
	public JTextField getPowerTypeField() {
		return powerTypeField;
	}
	
	public void setPowerTypeField(String s) {
		powerTypeField.setText(s);
	}
	
	public String getSearchText() {
		return searchTextField.getText();
	}
	
	public JButton getSearchButton() {
		return searchButton;
	}
	
	public String getSearchType() {
		return (String)searchSpinner.getValue();
	}
	
	public int getIDText() {
		return Integer.parseInt(idTextField.getText());
	}
	
	public void setIDText(String s) {
		idTextField.setText(s);
	}
	
	public String getTypeSpinType() {
		return (String)typeSpinner.getValue();
	}
	
	public void setTypeSpinType(String s) {
		typeSpinner.getModel().setValue(s);
	}
	
	public String getNameText() {
		return nameTextField.getText();
	}
	
	public void setNameText(String s) {
		nameTextField.setText(s);
	}
	
	public int getQtyText() {
		return Integer.parseInt(qtyTextField.getText());
	}
	
	public void setQtyText(String s) {
		qtyTextField.setText(s);
	}
	
	public double getPriceText() {
		return Double.parseDouble(priceTextField.getText());
	}
	
	public void setPriceText(String s) {
		priceTextField.setText(s);
	}
	
	public int getSupText() {
		return Integer.parseInt(supTextField.getText());
	}
	
	public void setSupText(String s) {
		supTextField.setText(s);
	}
	
	public void setList(DefaultListModel<String> listModel) {
		list.removeAll();
		list.setModel(listModel);
	}
	
	public String getListSelection() {
		return list.getSelectedValue();
	}
	
	public void setQuantityTextPane(int qty) {
		qtyTextPane.setText(String.valueOf(qty));
	}
	
	public void addToolTypeListener(ChangeListener listenForTypeSpinner) {
		typeSpinner.addChangeListener(listenForTypeSpinner);
	}
	
	public void addListAllListener (ActionListener listenForListButton) {
		listAllButton.addActionListener(listenForListButton);
	} 
	
	public void addSearchListener (ActionListener listenForSearchButton) {
		searchButton.addActionListener(listenForSearchButton);
	}
	
	public void addClearSearchListener (ActionListener listenForClearSearchButton) {
		clearSearchButton.addActionListener(listenForClearSearchButton);
	}
	
	public void addCheckQtyListener (ActionListener listenForCheckQtyButton) {
		checkQtyButton.addActionListener(listenForCheckQtyButton);
	}
	
	public void addDecreaseQtyListener (ActionListener listenForDecreaseQtyButton) {
		decreaseQtyButton.addActionListener(listenForDecreaseQtyButton);
		
	}
	
	public void addListSelectionListener (ListSelectionListener listenForListSelection) {
		list.addListSelectionListener(listenForListSelection);
		
	}
	
	public void addSaveListener (ActionListener listenForSaveButton) {
		saveButton.addActionListener(listenForSaveButton);
	}
	
	public void addDeleteListener (ActionListener listenForDeleteButton) {
		deleteButton.addActionListener(listenForDeleteButton);
	}
	
	public void addOrderListener (ActionListener listenForOrderButton) {
		writeOrderButton.addActionListener(listenForOrderButton);
	}

	public void displayDecreaseMessage () {
		JOptionPane pane = new JOptionPane();
		String message = list.getSelectedValue() + " decreased by 1.";
		JOptionPane.showMessageDialog(pane, message);
	}
	
	public void displayErrorMessage (String errorMessage) {
		JOptionPane pane = new JOptionPane();
		JOptionPane.showMessageDialog(pane, errorMessage);
	}
}

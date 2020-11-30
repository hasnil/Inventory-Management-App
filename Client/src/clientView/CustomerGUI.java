package clientView;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;

/**
 * ENSF 607 Project - November 26, 2020
 * @author Completed by Hunter Kimmett and Lotfi Hasni
 *
 */
public class CustomerGUI {

	private JFrame frame;
	private JTextField searchTextField;	//Search
	private JTextField idTextField;		//ID
	private JTextField fNameTextField;	//fName
	private JTextField lNameTextField; //lName
	private JTextField addressTextField; //Address
	private JTextField postalTextField; //Postal Code
	private JTextField phoneTextField; //Phone
	private ButtonGroup buttonGroup;
	private JButton searchButton;
	private JButton clearSearchButton;
	private JButton saveButton;
	private JButton deleteButton;
	private JButton clearFieldsButton;
	private JList<String> list;
	private JSpinner typeSpinner;


	public CustomerGUI() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("ClientGUI");
		frame.setBounds(100, 100, 910, 454);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 460, 32);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Client Search");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 38, 189, 124);
		frame.getContentPane().add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Select search parameter type:");
		panel_1.add(lblNewLabel_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Client ID");
		rdbtnNewRadioButton.setActionCommand("Client ID");
		panel_1.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Last Name");
		rdbtnNewRadioButton_1.setActionCommand("Last Name");
		rdbtnNewRadioButton_1.setSelected(true);
		panel_1.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Client Type");
		rdbtnNewRadioButton_2.setActionCommand("Client Type");
		panel_1.add(rdbtnNewRadioButton_2);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnNewRadioButton);
		buttonGroup.add(rdbtnNewRadioButton_1);
		buttonGroup.add(rdbtnNewRadioButton_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(207, 80, 259, 82);
		frame.getContentPane().add(panel_2);
		
		JLabel lblNewLabel_2 = new JLabel("Search parameter:");
		panel_2.add(lblNewLabel_2);
		
		searchTextField = new JTextField();
		panel_2.add(searchTextField);
		searchTextField.setColumns(10);
		
		searchButton = new JButton("Search");
		panel_2.add(searchButton);
		
		clearSearchButton = new JButton("Clear Search");
		panel_2.add(clearSearchButton);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(6, 174, 460, 37);
		frame.getContentPane().add(panel_3);
		
		JLabel lblNewLabel_3 = new JLabel("Search Results");
		panel_3.add(lblNewLabel_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 219, 460, 207);
		frame.getContentPane().add(scrollPane);
		
		list = new JList<String>();
		scrollPane.setViewportView(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(478, 6, 426, 32);
		frame.getContentPane().add(panel_4);
		
		JLabel lblNewLabel_4 = new JLabel("Client Information");
		panel_4.add(lblNewLabel_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(478, 51, 426, 32);
		frame.getContentPane().add(panel_5);
		
		JLabel lblNewLabel_5 = new JLabel("Client ID");
		panel_5.add(lblNewLabel_5);
		
		idTextField = new JTextField();
		panel_5.add(idTextField);
		idTextField.setColumns(10);
		
		JPanel panel_5_1 = new JPanel();
		panel_5_1.setBounds(478, 95, 426, 32);
		frame.getContentPane().add(panel_5_1);
		
		JLabel lblNewLabel_6 = new JLabel("First Name");
		panel_5_1.add(lblNewLabel_6);
		
		fNameTextField = new JTextField();
		panel_5_1.add(fNameTextField);
		fNameTextField.setColumns(10);
		
		JPanel panel_5_2 = new JPanel();
		panel_5_2.setBounds(478, 139, 426, 32);
		frame.getContentPane().add(panel_5_2);
		
		JLabel lblNewLabel_7 = new JLabel("Last Name");
		panel_5_2.add(lblNewLabel_7);
		
		lNameTextField = new JTextField();
		panel_5_2.add(lNameTextField);
		lNameTextField.setColumns(10);
		
		JPanel panel_5_3 = new JPanel();
		panel_5_3.setBounds(478, 183, 426, 32);
		frame.getContentPane().add(panel_5_3);
		
		JLabel lblNewLabel_8 = new JLabel("Address");
		panel_5_3.add(lblNewLabel_8);
		
		addressTextField = new JTextField();
		panel_5_3.add(addressTextField);
		addressTextField.setColumns(10);
		
		JPanel panel_5_4 = new JPanel();
		panel_5_4.setBounds(478, 229, 426, 32);
		frame.getContentPane().add(panel_5_4);
		
		JLabel lblNewLabel_9 = new JLabel("Postal Code");
		panel_5_4.add(lblNewLabel_9);
		
		postalTextField = new JTextField();
		panel_5_4.add(postalTextField);
		postalTextField.setColumns(10);
		
		JPanel panel_5_5 = new JPanel();
		panel_5_5.setBounds(478, 273, 426, 32);
		frame.getContentPane().add(panel_5_5);
		
		JLabel lblNewLabel_10 = new JLabel("Phone #");
		panel_5_5.add(lblNewLabel_10);
		
		phoneTextField = new JTextField();
		panel_5_5.add(phoneTextField);
		phoneTextField.setColumns(10);
		
		JPanel panel_5_6 = new JPanel();
		panel_5_6.setBounds(478, 317, 426, 32);
		frame.getContentPane().add(panel_5_6);
		
		JLabel lblNewLabel_11 = new JLabel("Client Type");
		panel_5_6.add(lblNewLabel_11);
		
		SpinnerListModel typelist = new SpinnerListModel(new String[] {"R", "C"});
		typeSpinner = new JSpinner(typelist);
		panel_5_6.add(typeSpinner);
		
		JPanel panel_5_7 = new JPanel();
		panel_5_7.setBounds(478, 361, 426, 32);
		frame.getContentPane().add(panel_5_7);
		
		saveButton = new JButton("Save");
		panel_5_7.add(saveButton);
		
		deleteButton = new JButton("Delete");
		panel_5_7.add(deleteButton);
		
		clearFieldsButton = new JButton("Clear");
		panel_5_7.add(clearFieldsButton);
		
	}
	
	public String getSearchText() {
		return searchTextField.getText();
	}
	
	public String getSearchType() {
		return buttonGroup.getSelection().getActionCommand();
	}
	
	public int getIDText() {
		return Integer.parseInt(idTextField.getText());
	}
	
	public void setIDText(String s) {
		idTextField.setText(s);
	}

	public String getFNameText() {
		return fNameTextField.getText();
	}
	
	public void setFNameText(String s) {
		fNameTextField.setText(s);
	}
	
	public String getLNameText() {
		return lNameTextField.getText();
	}
	
	public void setLNameText(String s) {
		lNameTextField.setText(s);
	}
	
	public String getAddressText() {
		return addressTextField.getText();
	}
	
	public void setAddressText(String s) {
		addressTextField.setText(s);
	}
	
	public String getPostalText() {
		return postalTextField.getText();
	}
	
	public void setPostalText(String s) {
		postalTextField.setText(s);
	}
	
	public String getPhoneText() {
		return phoneTextField.getText();
	}
	
	public void setPhoneText(String s) {
		phoneTextField.setText(s);
	}
	
	public String getTypeSpinType() {
		return (String)typeSpinner.getValue();
	}
	
	public void setTypeSpinType(String s) {
		typeSpinner.setValue(s);
	}
	
	
	public void setList(DefaultListModel<String> listModel) {
		list.removeAll();
		list.setModel(listModel);
	}
	
	public String getListSelection() {
		return list.getSelectedValue();
	}
	
	public void addSearchListener (ActionListener listenForSearchButton) {
		searchButton.addActionListener(listenForSearchButton);
	}
	
	public void addClearSearchListener (ActionListener listenForClearSearchButton) {
		clearSearchButton.addActionListener(listenForClearSearchButton);
	}
	
	public void addSaveListener (ActionListener listenForSaveButton) {
		saveButton.addActionListener(listenForSaveButton);
	}
	
	public void addDeleteListener (ActionListener listenForDeleteButton) {
		deleteButton.addActionListener(listenForDeleteButton);
	}
	
	public void addListSelectionListener (ListSelectionListener listenForListSelection) {
		list.addListSelectionListener(listenForListSelection);
		
	}
	
	public void addClearFieldsListener (ActionListener listenForClearSearchButton) {
		clearFieldsButton.addActionListener(listenForClearSearchButton);
	}
	
	public void displayErrorMessage (String errorMessage) {
		JOptionPane pane = new JOptionPane();
		JOptionPane.showMessageDialog(pane, errorMessage);
	}
	
}

package start;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import clientController.CustomerGUIController;
import clientController.InventoryGUIController;
import clientController.ModelController;
import clientView.CustomerGUI;
import clientView.InventoryGUI;

import javax.swing.JButton;
import javax.swing.JLabel;

public class ChoiceGUI implements ActionListener {

	private JFrame frame;
	private ModelController theModel;


	public ChoiceGUI(ModelController modelController) {
		theModel = modelController;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 491, 195);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton custButton = new JButton("Open Customer App");
		custButton.setBounds(146, 66, 188, 29);
		frame.getContentPane().add(custButton);
		custButton.setActionCommand("cust");
		
		JButton invButton = new JButton("Open Inventory App");
		invButton.setBounds(146, 104, 188, 29);
		frame.getContentPane().add(invButton);
		invButton.setActionCommand("inv");
		
		JLabel lblNewLabel = new JLabel("Please select the app you would like to run:");
		lblNewLabel.setBounds(101, 38, 279, 16);
		frame.getContentPane().add(lblNewLabel);
		
		custButton.addActionListener(this);
		invButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "cust") {
			CustomerGUI theView = new CustomerGUI ();
			CustomerGUIController theController = new CustomerGUIController(theView, theModel);
		} else if (e.getActionCommand() == "inv") {
			InventoryGUI theView = new InventoryGUI ();
			InventoryGUIController theController = new InventoryGUIController(theView, theModel);
		}
		
	}
}

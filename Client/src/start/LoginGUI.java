package start;


import javax.swing.*;

import clientController.InventoryGUIController;
import clientController.ModelController;
import clientView.InventoryGUI;

import java.awt.*;  
import java.awt.event.*;  


/**
 * ENSF 607 Project - November 26, 2020
 * @author Completed by Hunter Kimmett and Lotfi Hasni
 *
 */
public class LoginGUI {  
	
    private static JDialog d;  
    private ModelController modelController;
    
    public LoginGUI() {  
    	modelController = new ModelController();
    	
        JFrame f= new JFrame();  
        d = new JDialog(f , "User Login System", true);  
        d.setLayout( new FlowLayout() );  
        JTextField username = new JTextField("Username");
        username.setColumns(10);
        
        username.addMouseListener(new MouseAdapter(){
	        @Override
	        public void mouseClicked(MouseEvent e){
	        	username.setText("");
	        }
	    });
        
        JTextField password = new JTextField("Password");
        password.setColumns(10);
        
        password.addMouseListener(new MouseAdapter(){
	        @Override
	        public void mouseClicked(MouseEvent e){
	        	password.setText("");
	        }
	    });
        
        d.add(username);
        d.add(password);
        d.setAlwaysOnTop(true);
        
        JButton b = new JButton ("Login");  
        b.addActionListener ( new ActionListener()  
        {  
            public void actionPerformed( ActionEvent e )  
            {  	d.setAlwaysOnTop(false);
            	
            	String usernameField = username.getText();
            	String passwordField = password.getText();
            	
            	boolean result = modelController.checkUsernameAndPassword(usernameField, passwordField);
            	
            	if(result == true) {
            		
            		JOptionPane pane = new JOptionPane();
            		JOptionPane.showMessageDialog(pane, "Verification successful. Thank you for using our management system.");
            	
            		LoginGUI.d.setVisible(false);
                	LoginGUI.d.dispose();
                	
                	ChoiceGUI choice = new ChoiceGUI(modelController);
            	}
            	else {
            		JOptionPane pane = new JOptionPane();
            		JOptionPane.showMessageDialog(pane, "Verification failed. Please try again.");
            	}
            	d.setAlwaysOnTop(true);
            }  
        });  
        d.add( new JLabel ("            Click Here to Log in to Online ToolShop              "));  
        d.add(b);   
        d.setSize(350,175);   
        d.setLocationRelativeTo(null);
        d.setVisible(true);  
    }  
    
    public static void main(String args[])  
    {  
		new LoginGUI();  
    }  
}  
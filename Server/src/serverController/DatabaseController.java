package serverController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.sql.Date;
import commonModel.Customer;
import commonModel.Tool;

/**
 * The database controller is responsible for retrieving
 * information from and changing the information of the 
 * database.
 * 
 * ENSF 607 Project - November 26, 2020
 * @author Completed by Hunter Kimmett and Lotfi Hasni
 *
 */
public class DatabaseController {
	
	
	public Connection jdbc_connection;
	public PreparedStatement preparedStatement;
	
	// Change to use with different database
	public String databaseName = "ENSF608Project";
	public String nonElectricalToolTableName = "Tool";
	public String electricalToolTableName = "Electrical";
	public String localSupplierTableName = "Supplier";
	public String internationalSupplierTableName = "International";
	public String orderTableName = "Orders";
	public String orderLineTableName = "Orderline";
	public String customerTableName = "Clients";
	public String purchasesTableName = "Purchases";
	
	
	// Edit the following info to suit SQL needs
	public String connectionInfo = "jdbc:mysql://localhost:3306/" + databaseName,  login = "root", password = "zgmfx09a";

	public DatabaseController()
	{
		try{
			// If this throws an error, make sure you have added the mySQL connector JAR to the project
			Class.forName("com.mysql.jdbc.Driver");
			
			// If this fails make sure your connectionInfo and login/password are correct
			jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
			System.out.println("Connected to: " + connectionInfo + "\n");
		}
		catch(SQLException e) { e.printStackTrace(); }
		catch(Exception e) { e.printStackTrace(); }
	}
	

	public void addTool(Tool tool) {
				
		String sql = "INSERT INTO " + nonElectricalToolTableName + " VALUES (?,?,?,?,?,?);";
		
		try{
			preparedStatement = jdbc_connection.prepareStatement(sql);
			preparedStatement.setInt(1, tool.getID());
			preparedStatement.setString(2, tool.getName());
			preparedStatement.setString(3, tool.getType());
			preparedStatement.setInt(4, tool.getQuantity());
			preparedStatement.setDouble(5, tool.getPrice());
			preparedStatement.setInt(6, tool.getSupplierID());
			preparedStatement.executeUpdate();
			//preparedStatement.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		if(tool.getType().equals("Electrical")) {
			String sql2 = "INSERT INTO " + electricalToolTableName + " VALUES (?,?);";
			
			try{
				preparedStatement = jdbc_connection.prepareStatement(sql2);
				preparedStatement.setInt(1, tool.getID());
				preparedStatement.setString(2, tool.getPowerType());
				preparedStatement.executeUpdate();
				//preparedStatement.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	
	public void addCustomer(Customer c) {
		String sql = "INSERT INTO " + customerTableName + " VALUES (?,?,?,?,?,?,?);";
		
		try{
			preparedStatement = jdbc_connection.prepareStatement(sql);
			preparedStatement.setInt(1, c.getId());
			preparedStatement.setString(2, c.getLastName());
			preparedStatement.setString(3, c.getFirstName());
			preparedStatement.setString(4, String.valueOf(c.getType()));
			preparedStatement.setString(5, c.getPhoneNumber());
			preparedStatement.setString(6, c.getAddress());
			preparedStatement.setString(7, c.getPostalCode());
			preparedStatement.executeUpdate();
			//preparedStatement.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	

	public void deleteCustomer(int id) {
		String sql = "DELETE FROM " + customerTableName + " WHERE ClientID = ?;";
		
		try{
			preparedStatement = jdbc_connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			//preparedStatement.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void deleteTool(int toolId) {
		
		String sql = "DELETE FROM " + nonElectricalToolTableName + " WHERE ToolID=?;";
		try{
			preparedStatement = jdbc_connection.prepareStatement(sql);
			preparedStatement.setInt(1, toolId);
			preparedStatement.executeUpdate();
			//preparedStatement.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void decreaseToolQuantity(int toolId, int amount) {
		String sql = "UPDATE " + nonElectricalToolTableName + " SET Quantity = Quantity - ? WHERE ToolID = ?;";
		try{
			preparedStatement = jdbc_connection.prepareStatement(sql);
			preparedStatement.setInt(1, amount);
			preparedStatement.setInt(2, toolId);
			preparedStatement.executeUpdate();
			//preparedStatement.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void decreaseToolQuantity(String name, int amount) {
		String sql = "UPDATE " + nonElectricalToolTableName + " SET Quantity = Quantity - ? WHERE Name = ?;";
		try{
			preparedStatement = jdbc_connection.prepareStatement(sql);
			preparedStatement.setInt(1, amount);
			preparedStatement.setString(2, name);
			preparedStatement.executeUpdate();
			//preparedStatement.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	//When quantity drops below 40
	public void generateOrderLine(int orderId, int toolId, int supplierId, int quantity) {
		String sql = "INSERT INTO " + orderLineTableName + " VALUES (?,?,?,?);";
		
		try{
			preparedStatement = jdbc_connection.prepareStatement(sql);
			preparedStatement.setInt(1, orderId);
			preparedStatement.setInt(2, toolId);
			preparedStatement.setInt(3, supplierId);
			preparedStatement.setInt(4, quantity);
			preparedStatement.executeUpdate();
			//preparedStatement.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

	}
	
	public void addOrder(int orderId, String date) {
		String sql = "INSERT INTO " + orderTableName + " VALUES (?,?);";
		
		try{
			preparedStatement = jdbc_connection.prepareStatement(sql);
			preparedStatement.setInt(1, orderId);
			preparedStatement.setDate(2, Date.valueOf(date));
			preparedStatement.executeUpdate();
			//preparedStatement.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void updateOrderLine(int orderId, int quantity) {
		String sql = "UPDATE " + orderLineTableName + " SET Quantity = ? WHERE OrderID = ?;";
		try{
			preparedStatement = jdbc_connection.prepareStatement(sql);
			preparedStatement.setInt(1, quantity);
			preparedStatement.setInt(2, orderId);
			preparedStatement.executeUpdate();
			//preparedStatement.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public ResultSet loadAllTools() {
		String T = nonElectricalToolTableName;
		String E = electricalToolTableName;
		
		String sql = "SELECT " + T + ".ToolID, " + T + ".Name, " + T + ".Type, " + T + ".Quantity, " + T + 
				".Price, " + T + ".SupplierID, " + E + ".PowerType FROM " + T 
				+ " LEFT OUTER JOIN " + E + " ON " + T + ".ToolID = " + E + ".ToolID;";
		ResultSet tool;
		try {
			preparedStatement = jdbc_connection.prepareStatement(sql);
			tool = preparedStatement.executeQuery();
			//preparedStatement.close();
			
			return tool;
		} catch (SQLException e) { e.printStackTrace(); }
		
		return null;
	}
	
	
	public ResultSet loadAllSuppliers() {
		String L = localSupplierTableName;
		String I = internationalSupplierTableName;
		
		String sql = "SELECT " + L + ".SupplierID, " + L + ".Name, " + L + ".Type, " + L + 
				".Address, " + L + ".CName, " + L + ".Phone, " + I + ".ImportTax FROM " + L 
				+ " LEFT OUTER JOIN " + I + " ON " + L + ".SupplierID = " + I + ".SupplierID;";
		
		ResultSet supplier;
		try {
			preparedStatement = jdbc_connection.prepareStatement(sql);
			supplier = preparedStatement.executeQuery();
			//preparedStatement.close();
			return supplier;
		} catch (SQLException e) { e.printStackTrace(); }
		
		return null;
	}
	
	public ResultSet loadAllOrders() {
		String sql = "SELECT * FROM " + orderTableName + ";";
		ResultSet order;
		try {
			preparedStatement = jdbc_connection.prepareStatement(sql);
			order = preparedStatement.executeQuery();
			//preparedStatement.close();
			return order;
		} catch (SQLException e) { e.printStackTrace(); }
		
		return null;
	}
	
	public ResultSet loadAllOrderLines() {
		String sql = "SELECT * FROM " + orderLineTableName + ";";
		ResultSet orderLine;
		try {
			preparedStatement = jdbc_connection.prepareStatement(sql);
			orderLine = preparedStatement.executeQuery();
			//preparedStatement.close();
			return orderLine;
		} catch (SQLException e) { e.printStackTrace(); }
		
		return null;
	}
	
	public ResultSet loadAllCustomers() {
		String sql = "SELECT * FROM " + customerTableName + ";";
		ResultSet customer;
		try {
			preparedStatement = jdbc_connection.prepareStatement(sql);
			customer = preparedStatement.executeQuery();
			//preparedStatement.close();
			return customer;
		} catch (SQLException e) { e.printStackTrace(); }
		
		return null;
	}
	
	public HashMap<String,String> loadUsernamesAndPasswords() {
		// To simulate retrieving username/password from a database
		HashMap<String, String> h = new HashMap<String,String>();
		h.put("user", "pass");
		return h;
	}
}

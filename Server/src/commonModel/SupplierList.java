package commonModel;

import java.util.ArrayList;

/**
 *  The SupplierList class represents a collection of the
 *  tool suppliers present within the retail store program. 
 *  It is responsible for holding a list of these suppliers.
 *  
 *
 * ENSF 607 Project - November 26, 2020
 * @author Completed by Hunter Kimmett and Lotfi Hasni
 *
 */
public class SupplierList {

	/**
	 * The suppliers who provide tools in the retail store
	 */
	private ArrayList<Supplier> supplierList;
	
	/**
	 * Constructor method for SupplierList object.
	 * @param suppliersForStore the list of suppliers for the store
	 */
	public SupplierList(ArrayList<Supplier> suppliersForStore) {
		supplierList = suppliersForStore;
	}
	
	/**
	 * Prints list of all suppliers to display.
	 */
	public void listAllSuppliers() {
		
		for(Supplier sup : supplierList) {
			System.out.println(sup.toString() + "\n");
		}
		
	}

	/**
	 * Gets the ArrayList of suppliers.
	 * @return suppliers ArrayList
	 */
	public ArrayList<Supplier> getSupplierArrayList(){
		return supplierList;
	}

}

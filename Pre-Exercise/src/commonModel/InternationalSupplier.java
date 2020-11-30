package commonModel;

import java.io.Serializable;

/**
 * ENSF 607 Project - November 26, 2020
 * @author Completed by Hunter Kimmett and Lotfi Hasni
 *
 */
public class InternationalSupplier extends Supplier implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InternationalSupplier(int id, String name, String address, String contact, String phoneNumber, double importTax) {
		super(id, name, address, contact, phoneNumber);
		this.importTax = importTax;
		type = "International";
	}

	private double importTax;
	
	public double calculateImportTax() {
		return importTax;
	}

}

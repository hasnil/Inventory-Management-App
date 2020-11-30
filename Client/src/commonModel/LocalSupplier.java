package commonModel;

/**
 * ENSF 607 Project - November 26, 2020
 * @author Completed by Hunter Kimmett and Lotfi Hasni
 *
 */
public class LocalSupplier extends Supplier{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LocalSupplier(int id, String name, String address, String contact, String phoneNumber) {
		super(id, name, address, contact, phoneNumber);
		type = "Local";
	}

}

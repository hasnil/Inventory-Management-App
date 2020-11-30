package commonModel;

/**
 * ENSF 607 Project - November 26, 2020
 * @author Completed by Hunter Kimmett and Lotfi Hasni
 *
 */
public class ResidentialCustomer extends Customer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResidentialCustomer(int id, String firstName, String lastName, String address, String postalCode,
			String phoneNumber, char type) {
		super(id, firstName, lastName, address, postalCode, phoneNumber, type);
	}

}

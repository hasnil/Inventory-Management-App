package commonModel;

/**
 * ENSF 607 Project - November 26, 2020
 * @author Completed by Hunter Kimmett and Lotfi Hasni
 *
 */
public class NonElectricalTool extends Tool{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NonElectricalTool(int id, String name, int quantity, double price, int supplierId) {
		super(id, name, quantity, price, supplierId);
		type = "Non-Electrical";
	}

	@Override
	public String getPowerType() {
		return null;
	}

}

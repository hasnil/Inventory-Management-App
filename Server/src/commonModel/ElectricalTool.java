package commonModel;

/**
 * ENSF 607 Project - November 26, 2020
 * @author Completed by Hunter Kimmett and Lotfi Hasni
 *
 */
public class ElectricalTool extends Tool {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String powerType;
	
	public ElectricalTool(int id, String name, int quantity, double price, int supplierId, String powerType) {
		super(id, name, quantity, price, supplierId);
		type = "Electrical";
		this.powerType = powerType;
	}
	
	public String getPowerType() {
		return powerType;
	}

}

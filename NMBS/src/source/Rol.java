package source;

public class Rol {

	private int rollId;
	private String Rol;
	public int getRollId() {
		return rollId;
	}
	public void setRollId(int rollId) {
		this.rollId = rollId;
	}
	public Rol(int rollId) {
		super();
		this.rollId = rollId;
	}
	
	private static int rolId;
	private String rol;
	/**
	 * 
	 */
	public Rol() {
	}
	/**
	 * @param rolId de rol id als deze rol
	 * @param rol de rol benaming
	 */
	public Rol(int rolId, String rol) {
		this.rolId = rolId;
		this.rol = rol;
	}
	/**
	 * @return de rolId
	 */
	public static int getRolId() {
		return rolId;
	}
	/**
	 * @return de rol
	 */
	public String getRol() {
		return rol;
	}
	/**
	 * @param rolId de rolId 
	 */
	public void setRolId(int rolId) {
		this.rolId = rolId;
	}
	/**
	 * @param rol de rol benaming
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}
}

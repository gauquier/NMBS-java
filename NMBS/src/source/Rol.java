package source;

public class Rol {
	private int rolId;
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
	public int getRolId() {
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

package source;

public class Rol {

	private int rolId;
	private String rol;

	/**
	 * 
	 */
	public Rol() {
	}

	public Rol(int rolId) {
		this.rolId = rolId;
	}

	/**
	 * @param rolId
	 *            de rol id als deze rol
	 * @param rol
	 *            de rol benaming
	 */
	public Rol(int rolId, String rol) {
		this.rolId = rolId;
		this.rol = rol;
	}

	/**
	 * @return de rolId
	 */
	public int getRolId() {
		return this.rolId;
	}

	/**
	 * @return de rol
	 */
	public String getRol() {
		return this.rol;
	}

	/**
	 * @param rolId
	 *            de rolId
	 */
	public void setRolId(int rolId) {
		this.rolId = rolId;
	}

	/**
	 * @param rol
	 *            de rol benaming
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Rol [rolId=" + this.rolId + ", rol=" + this.rol + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rol == null) ? 0 : rol.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rol other = (Rol) obj;
		if (rol == null) {
			if (other.rol != null)
				return false;
		} else if (!rol.equals(other.rol))
			return false;
		return true;
	}
}

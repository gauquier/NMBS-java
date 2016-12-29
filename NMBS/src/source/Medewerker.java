package source;

public class Medewerker extends Persoon {
	private int medewerkerId;
	private Rol rol;
	private Login login;
	private boolean actief;

	/**
	 * Medewerker1 == Medewerker1 als
	 * Medewerker1.getPersoonId==Medewerker2.getPersoonId
	 * Medewerker1.getMedewerkerId==Medewerker2.getMedewerkerId
	 * Medewerker1.getLogin.getLoginId== Medewerker2.getLogin.getLoginId
	 * Medewerker1.getLogin.getUsername== Medewerker2.getLogin.getUsername
	 * 
	 */

	/**
	 * @param persoonId
	 * @param voornaam
	 * @param achternaam
	 * @param adres
	 * @param medewerkerId
	 * @param rol
	 * @param login
	 * @param actief
	 */
	public Medewerker(int persoonId, String voornaam, String achternaam, String email, Adres adres, int medewerkerId,
			Rol rol, Login login, boolean actief) {
		super(persoonId, voornaam, achternaam, email, adres);
		this.medewerkerId = medewerkerId;
		this.rol = rol;
		this.login = login;
		this.actief = actief;
	}

	/**
	 * @return rol van personeel
	 */
	public Rol getRol() {
		return this.rol;
	}

	/**
	 * @param rol
	 *            rol van personeel
	 */
	public void setFunctie(Rol rol) {
		this.rol = rol;
	}

	/**
	 * @return login
	 */
	public Login getLogin() {
		return this.login;
	}

	/**
	 * @param login
	 *            login gegevens
	 */
	public void setLogin(Login login) {
		this.login = login;
	}

	/**
	 * @return actief
	 */
	public boolean isActief() {
		return this.actief;
	}

	/**
	 * @param rol
	 */
	public void setRol(Rol rol) {
		this.rol = rol;
	}

	/**
	 * @param actief
	 */
	public void setActief(boolean actief) {
		this.actief = actief;
	}

	/**
	 * @return medewerkerId
	 */
	public int getMedewerkerId() {
		return this.medewerkerId;
	}

	/**
	 * @param medewerkerId
	 */
	public void setMedewerkerId(int medewerkerId) {
		this.medewerkerId = medewerkerId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((this.login == null) ? 0 : this.login.hashCode());
		result = prime * result + this.medewerkerId;
		return result;
	}

	/*
	 * Medewerker1 == Medewerker2 als
	 * Medewerker1.getPersoonId==Medewerker2.getPersoonId
	 * Medewerker1.getMedewerkerId==Medewerker2.getMedewerkerId
	 * Medewerker1.getLogin.getLoginId== Medewerker2.getLogin.getLoginId
	 * Medewerker1.getLogin.getUsername== Medewerker2.getLogin.getUsername
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Medewerker other = (Medewerker) obj;
		if (this.login == null) {
			if (other.login != null) {
				return false;
			}
		} else if (!this.login.equals(other.login)) {
			return false;
		}
		if (this.medewerkerId != other.medewerkerId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return this.getVoornaam() + " " + this.getAchternaam() + " (" + this.getRol().getRol() + ")";
	}

	public String test() {
		return this.medewerkerId + ": (" + this.login.toString() + ") + (" + this.rol.toString() + ") + (" + "voornaam="
				+ this.getVoornaam() + ", achternaam=" + this.getAchternaam() + ", email=" + this.getEmail()
				+ ", adres=" + this.getAdres().toString() + "])!!!";
	}

}

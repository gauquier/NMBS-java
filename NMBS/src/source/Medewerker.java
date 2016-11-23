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
	public Medewerker() {
	} 
	
	
	/**
	 * @param medewerkerId
	 * @param rol
	 * @param login
	 * @param actief
	 */
	public Medewerker(int medewerkerId, Rol rol, Login login, boolean actief) {
		super();
		this.medewerkerId = medewerkerId;
		this.rol = rol;
		this.login = login;
		this.actief = actief;
	}


	/**
	 * @param persoonId
	 * @param adres
	 * @param voornaam
	 * @param achternaam
	 * @param email
	 * @param medewerkerId
	 * @param rol
	 * @param login
	 * @param actief
	 */
	public Medewerker(int persoonId, Adres adres, String voornaam, String achternaam, String email,int medewerkerId, Rol rol, Login login, boolean actief) {
		super(persoonId, adres, voornaam, achternaam, email);
		this.rol = rol;
		this.login = login;
		this.actief = actief;
	}  
	/**
	 * @param persoonId
	 * @param voornaam
	 * @param achternaam
	 */
	public Medewerker(int persoonId, String voornaam, String achternaam) {
		super(persoonId, voornaam, achternaam);
	} 
	/**
	 * @return rol van personeel
	 */
	public Rol getRol() {
		return rol;
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
		return login;
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
		return actief;
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
		return medewerkerId;
	}
	/**
	 * @param medewerkerId 
	 */
	public void setMedewerkerId(int medewerkerId) {
		this.medewerkerId = medewerkerId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + medewerkerId;
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
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medewerker other = (Medewerker) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (medewerkerId != other.medewerkerId)
			return false;
		return true;
	}

}

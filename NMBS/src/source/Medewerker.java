package source;

public class Medewerker extends Persoon {
	private int medewerkerId;
	private Rol rol;
	private Login login;
	private boolean actief;

	/**
	 * 
	 */
	public Medewerker() {
	} 
	/**
	 * @param persoonId
	 * @param voornaam
	 * @param achternaam
	 * @param adresId
	 * @param medewerkerId
	 * @param rol
	 * @param login
	 * @param actief
	 */
	public Medewerker(int persoonId, String voornaam, String achternaam, int adresId,int medewerkerId, Rol rol, Login login, boolean actief) {
		super(persoonId, voornaam, achternaam, adresId);
		this.medewerkerId = medewerkerId;
		this.rol = rol;
		this.login = login;
		this.actief = actief;
	}

	/**
	 * @param persoonId
	 * @param voornaam
	 * @param achternaam
	 * @param adresId
	 * @param rol
	 * @param login
	 * @param actief
	 */
	public Medewerker(int persoonId, String voornaam, String achternaam, int adresId, Rol rol, Login login, boolean actief) {
		super(persoonId, voornaam, achternaam, adresId);
		this.rol = rol;
		this.login = login;
		this.actief=actief;
	}

	/**
	 * @param persoonId
	 * @param voornaam
	 * @param achternaam
	 * @param adresId
	 */
	public Medewerker(int persoonId, String voornaam, String achternaam, int adresId) {
		super(persoonId, voornaam, achternaam, adresId);
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
	 * @param voornaam
	 * @param achternaam
	 * @param adresId
	 */
	public Medewerker(String voornaam, String achternaam, int adresId) {
		super(voornaam, achternaam, adresId);
	}

	/**
	 * @param voornaam
	 * @param achternaam
	 */
	public Medewerker(String voornaam, String achternaam) {
		super(voornaam, achternaam);
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

}

package source;

public class Personeel extends Persoon {
	private static int functie;
	private Login login;

	/**
	 * 
	 */
	public Personeel() {
	}

	/**
	 * @param persoonId
	 * @param voornaam
	 * @param achternaam
	 * @param adresId
	 * @param functie
	 * @param login
	 */
	public Personeel(int persoonId, String voornaam, String achternaam, int adresId, int functie, Login login) {
		super(persoonId, voornaam, achternaam, adresId);
		this.functie = functie;
		this.login = login;
	}

	/**
	 * @param persoonId
	 * @param voornaam
	 * @param achternaam
	 * @param adresId
	 */
	public Personeel(int persoonId, String voornaam, String achternaam, int adresId) {
		super(persoonId, voornaam, achternaam, adresId);
	}

	/**
	 * @param persoonId
	 * @param voornaam
	 * @param achternaam
	 */
	public Personeel(int persoonId, String voornaam, String achternaam) {
		super(persoonId, voornaam, achternaam);
	}

	/**
	 * @param voornaam
	 * @param achternaam
	 * @param adresId
	 */
	public Personeel(String voornaam, String achternaam, int adresId) {
		super(voornaam, achternaam, adresId);
	}

	/**
	 * @param voornaam
	 * @param achternaam
	 */
	public Personeel(String voornaam, String achternaam) {
		super(voornaam, achternaam);
	}

	/**
	 * @return functie van personeel
	 */
	public static int getFunctie() {
		return functie;
	}

	/**
	 * @param functie
	 *            functie van personeel
	 */
	public void setFunctie(int functie) {
		this.functie = functie;
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

}

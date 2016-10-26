package source;

public class Klant extends Persoon {
	private String email;
	private String info;
	private boolean nieuws;

	/**
	 * 
	 */
	public Klant() {
	}

	/**
	 * @param email
	 * @param info
	 * @param nieuws
	 */
	public Klant(int persoonId, String voornaam, String achternaam, int adresId, String email, String info,
			boolean nieuws) {
		super(persoonId, voornaam, achternaam, adresId);
		this.email = email;
		this.info = info;
		this.nieuws = nieuws;
	}

	/**
	 * @param persoonId
	 * @param voornaam
	 * @param achternaam
	 * @param adresId
	 */
	public Klant(int persoonId, String voornaam, String achternaam, int adresId) {
		super(persoonId, voornaam, achternaam, adresId);
	}

	/**
	 * @param persoonId
	 * @param voornaam
	 * @param achternaam
	 */
	public Klant(int persoonId, String voornaam, String achternaam) {
		super(persoonId, voornaam, achternaam);
	}

	/**
	 * @param voornaam
	 * @param achternaam
	 * @param adresId
	 */
	public Klant(String voornaam, String achternaam, int adresId) {
		super(voornaam, achternaam, adresId);
	}

	/**
	 * @param voornaam
	 * @param achternaam
	 */
	public Klant(String voornaam, String achternaam) {
		super(voornaam, achternaam);
	}

	/**
	 * @return email van klant
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            email van klant
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return extra klant informatie
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * @param info
	 *            extra klant informatie
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * return een boolean die zegt of Klant een nieuwsbrief ontvangen heeft
	 * aanvaard of niet aanvaard
	 * 
	 * @return nieuws
	 */
	public boolean getNieuws() {
		return nieuws;
	}

	/**
	 * set als Klant een nieuwsbrief ontvangen heeft aanvaard of niet aanvaard
	 * 
	 * @param nieuws
	 *            nieuwsbrief sturen of nieuwsbrief niet sturen
	 */
	public void setNieuws(boolean nieuws) {
		this.nieuws = nieuws;
	}

}

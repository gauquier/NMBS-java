package source;

public class Klant extends Persoon { 
	private String info;
	private boolean nieuws; 
	/**
	 * 
	 */
	public Klant() {
	} 
	/**
	 * @param persoonId
	 * @param adres
	 * @param voornaam
	 * @param achternaam
	 * @param email
	 * @param info
	 * @param nieuws
	 */
	public Klant(int persoonId, Adres adres, String voornaam, String achternaam, String email, String info,boolean neiuws) {
		super(persoonId, adres, voornaam, achternaam, email); 
		this.info=info;
		this.nieuws=neiuws;
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

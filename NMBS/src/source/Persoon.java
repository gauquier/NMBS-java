package source;

public class Persoon {
	private int persoonId;
	private String voornaam;
	private String achternaam;
	private int adresId;

	public Persoon() {
		// Default constructor
	}

	/**
	 * 
	 * @param voornaam
	 * @param achternaam
	 */
	public Persoon(String voornaam, String achternaam) {
		this.voornaam = voornaam;
		this.achternaam = achternaam;
	}

	/**
	 * Persoon is eerder opgeslagen in DB en heeft persoonId nog geen adres
	 * toegekend
	 * 
	 * @param persoonId
	 * @param voornaam
	 * @param achternaam
	 */
	public Persoon(int persoonId, String voornaam, String achternaam) {
		this.persoonId = persoonId;
		this.voornaam = voornaam;
		this.achternaam = achternaam;
	}

	/**
	 * Persoon is niet eerder opgeslagen in DB maar adres adress is eerder
	 * opgeslagen bv Family leden, woonen op hetzelde adres
	 * 
	 * @param voornaam
	 * @param achternaam
	 * @param adresId
	 */
	public Persoon(String voornaam, String achternaam, int adresId) {
		this.voornaam = voornaam;
		this.achternaam = achternaam;
		this.adresId = adresId;
	}

	/**
	 * Persoon eerder is opgeslagen in DB en heeft persoonId en heeft een adres
	 * 
	 * @param persoonId
	 * @param voornaam
	 * @param achternaam
	 * @param adresId
	 */
	public Persoon(int persoonId, String voornaam, String achternaam, int adresId) {
		this.persoonId = persoonId;
		this.voornaam = voornaam;
		this.achternaam = achternaam;
		this.adresId = adresId;
	}

	/**
	 * @return persoonId Persoon is eerder opgeslagen in DB en heeft id
	 */
	public int getPersoonId() {
		return persoonId;
	}

	/**
	 * @param persoonId
	 */
	public void setPersoonId(int persoonId) {
		this.persoonId = persoonId;
	}

	/**
	 * @return voornaam
	 */
	public String getVoornaam() {
		return voornaam;
	}

	/**
	 * @param voornaam
	 */
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	/**
	 * @return achternaam
	 */
	public String getAchternaam() {
		return achternaam;
	}

	/**
	 * @param achternaam
	 */
	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

	/**
	 * @return adresId als adres is toegekend of null als adres is niet
	 *         toegekend
	 */
	public int getAdresId() {
		return adresId;
	}

	/**
	 * @param adresId
	 */
	public void setAdresId(int adresId) {
		this.adresId = adresId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + persoonId;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persoon other = (Persoon) obj;
		if (persoonId != other.persoonId)
			return false;
		return true;
	}

}

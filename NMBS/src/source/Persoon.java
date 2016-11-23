package source;

public class Persoon {
	private int persoonId;
	private Adres adres;
	private String voornaam;
	private String achternaam;
	private String email;

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
	 * @param adres
	 */
	public Persoon(String voornaam, String achternaam, Adres adres) {
		this.voornaam = voornaam;
		this.achternaam = achternaam;
		this.adres = adres;
	} 
	/**
	 * Persoon eerder is opgeslagen in DB en heeft persoonId en heeft een adres
	 * 
	 * @param persoonId
	 * @param adres
	 * @param voornaam
	 * @param achternaam
	 * @param email
	 */
	public Persoon(int persoonId, Adres adres, String voornaam, String achternaam, String email) { 
		this.persoonId = persoonId;
		this.adres = adres;
		this.voornaam = voornaam;
		this.achternaam = achternaam;
		this.email = email;
	}

	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email 
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return adres als adres is toegekend of null als adres is niet
	 *         toegekend
	 */
	public Adres getAdres() {
		return adres;
	}

	/**
	 * @param adres
	 */
	public void setAdres(Adres adres) {
		this.adres = adres;
	}
	//persoon = persoon als voornaam en achternaam gelijk zijn

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((achternaam == null) ? 0 : achternaam.hashCode());
		result = prime * result + ((voornaam == null) ? 0 : voornaam.hashCode());
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
		if (achternaam == null) {
			if (other.achternaam != null)
				return false;
		} else if (!achternaam.equals(other.achternaam))
			return false;
		if (voornaam == null) {
			if (other.voornaam != null)
				return false;
		} else if (!voornaam.equals(other.voornaam))
			return false;
		return true;
	}
	

}

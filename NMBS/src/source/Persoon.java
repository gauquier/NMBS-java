<<<<<<< HEAD
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
=======
package source;

public class Persoon {

	private int persoonId;
	private String voornaam;
	private String achternaam;
	private String email;
	private Adres adres;
	
	public Persoon(int persoonId, String voornaam, String achternaam, String email, Adres adres) {
		super();
		this.persoonId = persoonId;
		this.voornaam = voornaam;
		this.achternaam = achternaam;
		this.email = email;
		this.adres = adres;
	}

	public String getNaam() {
		return voornaam + " " + achternaam;
	}
	public String getVoornaam(){
		return voornaam;
	}
	
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getAchternaam() {
		return achternaam;
	}

	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

	public Adres getAdres() {
		return adres;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}

	public int getPersoonId() {
		return persoonId;
	}

	public void setPersoonId(int persoonId) {
		if(persoonId > 0)
		this.persoonId = persoonId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Persoon [persoonId=" + persoonId + ", voornaam=" + voornaam + ", achternaam=" + achternaam + ", email="
				+ email + ", adres=" + adres + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((achternaam == null) ? 0 : achternaam.hashCode());
		result = prime * result + ((adres == null) ? 0 : adres.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + persoonId;
		result = prime * result + ((voornaam == null) ? 0 : voornaam.hashCode());
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
		Persoon other = (Persoon) obj;
		if (achternaam == null) {
			if (other.achternaam != null)
				return false;
		} else if (!achternaam.equals(other.achternaam))
			return false;
		if (adres == null) {
			if (other.adres != null)
				return false;
		} else if (!adres.equals(other.adres))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (persoonId != other.persoonId)
			return false;
		if (voornaam == null) {
			if (other.voornaam != null)
				return false;
		} else if (!voornaam.equals(other.voornaam))
			return false;
		return true;
	}

	
	
	

}
>>>>>>> b16171dd64f55c0547bfe1ffdaa9c1e4699091a4

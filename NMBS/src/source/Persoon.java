package source;

public class Persoon {

	private int id;
	private String voornaam;
	private String achternaam;
	private String email;
	private Adres adres;
	
	public Persoon(int id, String voornaam, String achternaam, String email, Adres adres) {
		super();
		this.id = id;
		this.voornaam = voornaam;
		this.achternaam = achternaam;
		this.email = email;
		this.adres = adres;
	}
	
	public Persoon( String voornaam, String achternaam, String email, Adres adres) {
		super();
		this.voornaam = voornaam;
		this.achternaam = achternaam;
		this.email = email;
		this.adres = adres;
	}

	public Persoon(int persoonId, String voornaam, String achternaam) {
		super();
		this.id = persoonId;
		this.voornaam = voornaam;
		this.achternaam = achternaam;
	}

	public Persoon(String voornaam, String achternaam, Adres adres) {
		this.voornaam = voornaam;
		this.achternaam = achternaam;
		this.adres = adres;
	}

	public Persoon(String voornaam, String achternaam) {
		this.voornaam = voornaam;
		this.achternaam = achternaam;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if(id > 0)
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Persoon [id=" + id + ", voornaam=" + voornaam + ", achternaam=" + achternaam + ", email="
				+ email + ", adres=" + adres + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((achternaam == null) ? 0 : achternaam.hashCode());
		result = prime * result + ((adres == null) ? 0 : adres.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
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
		if (id != other.id)
			return false;
		if (voornaam == null) {
			if (other.voornaam != null)
				return false;
		} else if (!voornaam.equals(other.voornaam))
			return false;
		return true;
	}

	
	
	

}

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

	public Persoon(String voornaam, String achternaam, String email, Adres adres) {
		super();
		this.voornaam = voornaam;
		this.achternaam = achternaam;
		this.email = email;
		this.adres = adres;
	}

	public String getNaam() {
		return this.voornaam + " " + this.achternaam;
	}

	public String getVoornaam() {
		return this.voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getAchternaam() {
		return this.achternaam;
	}

	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

	public Adres getAdres() {
		return this.adres;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		if (id > 0) {
			this.id = id;
		}
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Persoon [id=" + this.id + ", voornaam=" + this.voornaam + ", achternaam=" + this.achternaam + ", email="
				+ this.email + ", adres=" + this.adres + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.achternaam == null) ? 0 : this.achternaam.hashCode());
		result = prime * result + ((this.adres == null) ? 0 : this.adres.hashCode());
		result = prime * result + ((this.email == null) ? 0 : this.email.hashCode());
		result = prime * result + this.id;
		result = prime * result + ((this.voornaam == null) ? 0 : this.voornaam.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Persoon other = (Persoon) obj;
		if (this.achternaam == null) {
			if (other.achternaam != null) {
				return false;
			}
		} else if (!this.achternaam.equals(other.achternaam)) {
			return false;
		}
		if (this.adres == null) {
			if (other.adres != null) {
				return false;
			}
		} else if (!this.adres.equals(other.adres)) {
			return false;
		}
		if (this.email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!this.email.equals(other.email)) {
			return false;
		}
		if (this.id != other.id) {
			return false;
		}
		if (this.voornaam == null) {
			if (other.voornaam != null) {
				return false;
			}
		} else if (!this.voornaam.equals(other.voornaam)) {
			return false;
		}
		return true;
	}
}

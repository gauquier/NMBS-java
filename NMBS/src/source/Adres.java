package source;

public class Adres {

	private int adresId;
	private String straat;
	private int huisnr;
	private String woonplaats;
	private int postcode;
	private String bus;

	public Adres(int adresId, String straat, int huisnr, String woonplaats, int postcode, String bus) {
		super();
		this.adresId = adresId;
		this.straat = straat;
		this.huisnr = huisnr;
		this.woonplaats = woonplaats;
		this.postcode = postcode;
		this.bus = bus;
	}

	public Adres(String straat, int huisnr, String woonplaats, int postcode, String bus) {
		super();
		this.straat = straat;
		this.huisnr = huisnr;
		this.woonplaats = woonplaats;
		this.postcode = postcode;
		this.bus = bus;
	}

	public int getAdresId() {
		return this.adresId;
	}

	public void setAdresId(int adresId) {
		if (adresId > 0) {
			this.adresId = adresId;
		}
	}

	public String getStraat() {
		return this.straat;
	}

	public void setStraat(String straat) {
		this.straat = straat;
	}

	public int getHuisnr() {
		return this.huisnr;
	}

	public void setHuisnr(int huisnr) {
		if (huisnr > 0) {
			this.huisnr = huisnr;
		}
	}

	public String getWoonplaats() {
		return this.woonplaats;
	}

	public void setWoonplaats(String woonplaats) {
		this.woonplaats = woonplaats;
	}

	public int getPostcode() {
		return this.postcode;
	}

	public void setPostcode(int postcode) {
		if (postcode >= 1000 && postcode <= 9999) {
			this.postcode = postcode;
		}
	}

	public String getBus() {
		return this.bus;
	}

	public void setBus(String bus) {
		this.bus = bus;
	}

	@Override
	public String toString() {
		return this.getStraat() + " " + this.getHuisnr() + " , " + this.getPostcode() + " " + this.getWoonplaats();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.bus == null) ? 0 : this.bus.hashCode());
		result = prime * result + this.huisnr;
		result = prime * result + this.postcode;
		result = prime * result + ((this.straat == null) ? 0 : this.straat.hashCode());
		result = prime * result + ((this.woonplaats == null) ? 0 : this.woonplaats.hashCode());
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
		Adres other = (Adres) obj;
		if (this.bus == null) {
			if (other.bus != null) {
				return false;
			}
		} else if (!this.bus.equals(other.bus)) {
			return false;
		}
		if (this.huisnr != other.huisnr) {
			return false;
		}
		if (this.postcode != other.postcode) {
			return false;
		}
		if (this.straat == null) {
			if (other.straat != null) {
				return false;
			}
		} else if (!this.straat.equals(other.straat)) {
			return false;
		}
		if (this.woonplaats == null) {
			if (other.woonplaats != null) {
				return false;
			}
		} else if (!this.woonplaats.equals(other.woonplaats)) {
			return false;
		}
		return true;
	}

}
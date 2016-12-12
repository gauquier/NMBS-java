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
		return adresId;
	}
	public void setAdresId(int adresId) {
		if(adresId > 0)
		this.adresId = adresId;
	}
	public String getStraat() {
		return straat;
	}
	public void setStraat(String straat) {
		this.straat = straat;
	}
	public int getHuisnr() {
		return huisnr;
	}
	public void setHuisnr(int huisnr) {
		if(huisnr > 0)
			this.huisnr = huisnr;
	}
	public String getWoonplaats() {
		return woonplaats;
	}
	public void setWoonplaats(String woonplaats) {
		this.woonplaats = woonplaats;
	}
	public int getPostcode() {
		return postcode;
	}
	public void setPostcode(int postcode) {
		if(postcode >= 1000 && postcode <= 9999)
		this.postcode = postcode;
	}
	public String getBus() {
		return bus;
	}
	public void setBus(String bus) {
		this.bus = bus;
	}
	
	@Override
	public String toString() {
		return "Adres [adresId=" + adresId + ", straat=" + straat + ", huisnr=" + huisnr + ", woonplaats=" + woonplaats
				+ ", postcode=" + postcode + ", bus=" + bus + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bus == null) ? 0 : bus.hashCode());
		result = prime * result + huisnr;
		result = prime * result + postcode;
		result = prime * result + ((straat == null) ? 0 : straat.hashCode());
		result = prime * result + ((woonplaats == null) ? 0 : woonplaats.hashCode());
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
		Adres other = (Adres) obj;
		if (bus == null) {
			if (other.bus != null)
				return false;
		} else if (!bus.equals(other.bus))
			return false;
		if (huisnr != other.huisnr)
			return false;
		if (postcode != other.postcode)
			return false;
		if (straat == null) {
			if (other.straat != null)
				return false;
		} else if (!straat.equals(other.straat))
			return false;
		if (woonplaats == null) {
			if (other.woonplaats != null)
				return false;
		} else if (!woonplaats.equals(other.woonplaats))
			return false;
		return true;
	}
	
}

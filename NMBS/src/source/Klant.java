package source;

public class Klant extends Persoon {

	private String info;
	private boolean nieuws;
	public Klant(int id, String voornaam, String achternaam, Adres adres, String email, String info, boolean nieuws) {
		super(id, voornaam, achternaam, email, adres);
		this.info = info;
		this.nieuws = nieuws;
	}

	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public boolean isNieuws() {
		return nieuws;
	}
	public void setNieuws(boolean nieuws) {
		this.nieuws = nieuws;
	}
	@Override
	public String toString() {
		return "Klant [info=" + info + ", nieuws=" + nieuws + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + (nieuws ? 1231 : 1237);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Klant other = (Klant) obj;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		if (nieuws != other.nieuws)
			return false;
		return true;
	}
	
	


}

package source;

<<<<<<< HEAD
public class Klant extends Persoon { 
=======
public class Klant extends Persoon {

>>>>>>> refs/remotes/origin/master
	private String info;
<<<<<<< HEAD
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
=======
	private boolean nieuws;
	public Klant(int id, String voornaam, String achternaam, Adres adres, String email, String info, boolean nieuws) {
		super(id, voornaam, achternaam, email, adres);
		this.info = info;
		this.nieuws = nieuws;
	}

>>>>>>> refs/remotes/origin/master
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

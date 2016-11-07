<<<<<<< HEAD
package source;

public class Klant extends Persoon {
	private String email;
	private String info;
	private boolean nieuws;

	/**
	 * 
	 */
	public Klant() {
	}

	/**
	 * @param email
	 * @param info
	 * @param nieuws
	 */
	public Klant(int persoonId, String voornaam, String achternaam, int adresId, String email, String info,
			boolean nieuws) {
		super(persoonId, voornaam, achternaam, adresId);
		this.email = email;
		this.info = info;
		this.nieuws = nieuws;
	}

	/**
	 * @param persoonId
	 * @param voornaam
	 * @param achternaam
	 * @param adresId
	 */
	public Klant(int persoonId, String voornaam, String achternaam, int adresId) {
		super(persoonId, voornaam, achternaam, adresId);
	}

	/**
	 * @param persoonId
	 * @param voornaam
	 * @param achternaam
	 */
	public Klant(int persoonId, String voornaam, String achternaam) {
		super(persoonId, voornaam, achternaam);
	}

	/**
	 * @param voornaam
	 * @param achternaam
	 * @param adresId
	 */
	public Klant(String voornaam, String achternaam, int adresId) {
		super(voornaam, achternaam, adresId);
	}

	/**
	 * @param voornaam
	 * @param achternaam
	 */
	public Klant(String voornaam, String achternaam) {
		super(voornaam, achternaam);
	}

	/**
	 * @return email van klant
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            email van klant
	 */
	public void setEmail(String email) {
		this.email = email;
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
=======
package source;

public class Klant extends Persoon {

	private String info;
	private boolean nieuws;
	public Klant(int persoonId, String voornaam, String achternaam, Adres adres, String email, String info, boolean nieuws) {
		super(persoonId, voornaam, achternaam, email, adres);
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
>>>>>>> b16171dd64f55c0547bfe1ffdaa9c1e4699091a4

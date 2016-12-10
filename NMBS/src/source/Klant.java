package source;

public class Klant extends Persoon {

	private int klantId;
	private String info;
	private boolean actief;
	
	public Klant(int persoonId, String voornaam, String achternaam,  String email, Adres adres, String info, boolean actief) {
		super(persoonId, voornaam, achternaam, email, adres);
		this.info = info;
		this.actief=actief;
		
	}
	
	public Klant(int persoonId, String voornaam, String achternaam,  String email, Adres adres, int klantId, String info, boolean actief) {
		super(persoonId, voornaam, achternaam, email, adres);
		this.klantId = klantId;
		this.info = info;
		this.actief=actief;
		
	}
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (actief ? 1231 : 1237);
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + klantId;
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
		if (actief != other.actief)
			return false;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		if (klantId != other.klantId)
			return false;
		return true;
	}

	public boolean isActief() {
		return actief;
	}

	public void setActief(boolean actief) {
		this.actief = actief;
	}

	public String toString()
	{
			return getVoornaam() + " " + getAchternaam();
	}
	
	


}

package source;

public class Klant extends Persoon {

	private String info;
	private boolean actief;
	
	public Klant(int persoonId, String voornaam, String achternaam,  String email, Adres adres, String info, boolean actief) {
		super(persoonId, voornaam, achternaam, email, adres);
		this.info = info;
		this.actief=actief;
		
	}
	

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
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

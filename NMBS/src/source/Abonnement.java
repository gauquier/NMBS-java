package source;
import java.util.Calendar;

public class Abonnement extends Aankoop {
	private Klant klant;
	private String depZone, arrZone;
	private boolean actief;
	
	public Abonnement(double korting, double prijs, VerkoopType verkoop, Klant klant, String depZone, String arrZone,
			boolean actief) {
		super(korting, prijs, verkoop);
		this.klant = klant;
		this.depZone = depZone;
		this.arrZone = arrZone;
		this.actief = actief;
	}

	public Abonnement(double korting, double prijs, VerkoopType verkoop, Klant klant, String depZone, String arrZone) {
		super(korting, prijs, verkoop);
		this.klant = klant;
		this.depZone = depZone;
		this.arrZone = arrZone;
	}



	public Klant getKlant() {
		return klant;
	}

	public void setKlant(Klant klant) {
		this.klant = klant;
	}

	public String getDepZone() {
		return depZone;
	}

	public void setDepZone(String depZone) {
		this.depZone = depZone;
	}

	public String getArrZone() {
		return arrZone;
	}

	public void setArrZone(String arrZone) {
		this.arrZone = arrZone;
	}

	public boolean isActief() {
		return actief;
	}

	public void setActief(boolean actief) {
		this.actief = actief;
	}

	
	
	
}
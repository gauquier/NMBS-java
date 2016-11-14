package source;
import java.util.Calendar;

public class Abonnement extends Aankoop {

    //aankoopId = abonnementId
	private int klantId;//Foreign key met Klant
	private String depZone;
	private String arrZone;
	private boolean actief;

    //Volgorde van constructorparameterlijst laten zoals het is. Het matcht de volgorde van de DB-tabel en wordt/werd ook zo gebruikt in AbonnementDao.
	public Abonnement(int aankoopId, int klantId, String depZone, String arrZone, double prijs, VerkoopType verkoop, double korting, boolean actief) {
		super(aankoopId, korting, prijs, verkoop);
		setAbonnementId(abonnementId);
		setKlantId(klantId);
		setDepZone(depZone);
		setArrZone(arrZone);
		setActief(actief);
	}

	/*
	    Constructor zonder aankoopId (= abonnementId uit DB)
	 */
    public Abonnement(int klantId, String depZone, String arrZone, double prijs, VerkoopType verkoop, double korting, boolean actief) {
        aankoopId = 0;
        super(aankoopId, korting, prijs, verkoop);
        setAbonnementId(abonnementId);
        setKlantId(klantId);
        setDepZone(depZone);
        setArrZone(arrZone);
        setActief(actief);
    }
	
	public int getAbonnementId() {
		return abonnementId;
	}
	public void setAbonnementId(int abonnementId) {
		this.abonnementId = abonnementId;
	}

	public int getKlantId() {
		return klantId;
	}
	public void setKlantId(int klantId) {
		this.klantId = klantId;
	}
	
	public boolean isActief() {
		return actief;
	}
	public void setActief(boolean actief) {
		this.actief = actief;
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
	
	
	
}
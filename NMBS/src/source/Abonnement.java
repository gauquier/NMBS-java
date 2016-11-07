package source;
import java.util.Calendar;

public class Abonnement extends Aankoop {
	
	private int abonnementId;
	private int periodeId;
	private int klantId;//Foreign key met Klant
	private String depZone;
	private String arrZone;
	private boolean actief;
	
	public Abonnement(int aankoopId, int abonnementId, int periodeId,int klantId,String depZone, String arrZone, double prijs, VerkoopType verkoop,double korting,boolean actief) {
		super(aankoopId, korting, prijs, verkoop);
		setAbonnementId(abonnementId);
		setPeriodeId(periodeId);
		setDepZone(depZone);
		setArrZone(arrZone);
		setKlantId(klantId);
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
	
	public int getPeriodeId() {
		return periodeId;
	}

	public void setPeriodeId(int periodeId) {
		this.periodeId = periodeId;
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
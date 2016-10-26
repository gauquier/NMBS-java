package source;
import java.util.Date;

public class Abonnement extends Aankoop {
	
	private int abonnementId;
	private Date startDate;
	private Date endDate;
	private String regio;
	private int klantId;//Foreign key met Klant
	private boolean actief;
	
	public Abonnement(int aankoopId, double korting, double prijs, VerkoopType verkoop, int abonnementId, Date startDate, Date endDate, String regio, int klantId, boolean actief) {
		super(aankoopId, korting, prijs, verkoop);
		setAbonnementId(abonnementId);
		setStartDate(startDate);
		setEndDate(endDate);
		setRegio(regio);
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getRegio() {
		return regio;
	}
	public void setRegio(String regio) {
		this.regio = regio;
	}
	public boolean isActief() {
		return actief;
	}
	public void setActief(boolean actief) {
		this.actief = actief;
	}
	
}
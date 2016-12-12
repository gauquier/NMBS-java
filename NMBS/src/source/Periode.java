package source;
import java.util.Date;


public class Periode {
	private int periodeId;
	private int abonnementId;
	private int medewerkerId;
	private Date startDate;
	private Date endDate;
	private Date verkoopdatum;
	
	public Periode(int periodeId, int abonnementId, int medewerkerId, Date startDate, Date endDate, Date verkoopdatum) {
		super();
		this.periodeId = periodeId;
		this.abonnementId = abonnementId;
		this.medewerkerId = medewerkerId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.verkoopdatum = verkoopdatum;

	}

	public int getPeriodeId() {
		return periodeId;
	}

	public void setPeriodeId(int periodeId) {
		this.periodeId = periodeId;
	}

	public int getAbonnementId() {
		return abonnementId;
	}

	public void setAbonnementId(int abonnementId) {
		this.abonnementId = abonnementId;
	}

	public int getMedewerkerId() {
		return medewerkerId;
	}

	public void setMedewerkerId(int medewerkerId) {
		this.medewerkerId = medewerkerId;
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

	public Date getVerkoopdatum() {
		return verkoopdatum;
	}

	public void setVerkoopdatum(Date verkoopdatum) {
		this.verkoopdatum = verkoopdatum;
	}
	
	
}

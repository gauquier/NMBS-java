package source;

import java.util.Date;

public class Periode {
	private int periodeId;
	private Date startDate;
	private Date endDate;
	private Date verkoopdatum;
	private int medewerkerId;

	public Periode(int periodeId, Date startDate, Date endDate, Date verkoopdatum) {
		super();
		this.periodeId = periodeId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.verkoopdatum = verkoopdatum;

	}

	public Periode(int periodeId, int medewerkerId, Date startDate, Date endDate, Date verkoopdatum) {
		super();
		this.periodeId = periodeId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.verkoopdatum = verkoopdatum;
		this.medewerkerId = medewerkerId;
		// this.abonnement = abonnement;
	}

	public Periode(int periodeId, Date endDate) {
		this.periodeId = periodeId;
		this.endDate = endDate;
	}

	public int getPeriodeId() {
		return this.periodeId;
	}

	public void setPeriodeId(int periodeId) {
		this.periodeId = periodeId;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getVerkoopdatum() {
		return this.verkoopdatum;
	}

	public void setVerkoopdatum(Date verkoopdatum) {
		this.verkoopdatum = verkoopdatum;
	}

	public int getMedewerkerId() {
		return this.medewerkerId;
	}

	public void setMedewerkerId(int medewerkerId) {
		this.medewerkerId = medewerkerId;
	}

}

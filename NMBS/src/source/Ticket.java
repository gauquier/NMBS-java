package source;

import java.util.Date;

public class Ticket extends Aankoop {

	private int medewerkerId;
	private String depZone;
	private String arrZone;
	private int verkoopStation;
	private int klasse = 2;
	private int aantal = 1;
	private Date verkoopDatum;
	private Date heenDatum;
	private Date terugDatum = null;

	public Ticket(int aankoopId, int medewerkerId, String depZone, String arrZone, int verkoopStation, double prijs,
			VerkoopType verkoop, double korting, int klasse, int aantal, Date verkoopDatum, Date heenDatum,
			Date terugDatum) {
		super(aankoopId, korting, prijs, verkoop);
		this.setMedewerkerId(medewerkerId);
		this.setDepZone(depZone);
		this.setArrZone(arrZone);
		this.setVerkoopStation(verkoopStation);
		this.setKlasse(klasse);
		this.setAantal(aantal);
		this.setVerkoopDatum(verkoopDatum);
		this.setHeenDatum(heenDatum);
		this.setTerugDatum(terugDatum);
	}

	public int getMedewerkerId() {
		return this.medewerkerId;
	}

	public void setMedewerkerId(int medewerkerId) {
		this.medewerkerId = medewerkerId;
	}

	public String getDepZone() {
		return this.depZone;
	}

	public void setDepZone(String depZone) {
		this.depZone = depZone;
	}

	public String getArrZone() {
		return this.arrZone;
	}

	public void setArrZone(String arrZone) {
		this.arrZone = arrZone;
	}

	public Date getVerkoopDatum() {
		return this.verkoopDatum;
	}

	public void setVerkoopDatum(Date verkoopDatum) {
		this.verkoopDatum = verkoopDatum;
	}

	public int getKlasse() {
		return this.klasse;
	}

	public void setKlasse(int klasse) {
		this.klasse = klasse;
	}

	public int getAantal() {
		return this.aantal;
	}

	public void setAantal(int aantal) {
		this.aantal = aantal;
	}

	public Date getHeenDatum() {
		return this.heenDatum;
	}

	public void setHeenDatum(Date heenDatum) {
		this.heenDatum = heenDatum;
	}

	public Date getTerugDatum() {
		return this.terugDatum;
	}

	public void setTerugDatum(Date terugDatum) {
		this.terugDatum = terugDatum;
	}

	public int getVerkoopStation() {
		return this.verkoopStation;
	}

	public void setVerkoopStation(int verkoopStation) {
		this.verkoopStation = verkoopStation;
	}
}
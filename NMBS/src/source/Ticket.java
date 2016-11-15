package source;
import java.util.Calendar;
import java.util.Date;

import source.Aankoop;
import source.VerkoopType;

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
	
	
	public Ticket(int aankoopId,   int medewerkerId, String depZone, String arrZone,int verkoopStation, double prijs, VerkoopType verkoop,double korting,int klasse, int aantal, Date verkoopDatum, Date heenDatum, Date terugDatum) {
		super(aankoopId, korting, prijs, verkoop);
		setMedewerkerId(medewerkerId);
		setDepZone(depZone);
		setArrZone(arrZone);
		setVerkoopStation(verkoopStation);
		setKlasse(klasse);
		setAantal(aantal);
		setVerkoopDatum(verkoopDatum);
		setHeenDatum(heenDatum);
		setTerugDatum(terugDatum);
	}

	public Ticket(int aankoopId, int medewerkerId, String depZone,
			String arrZone, int verkoopStation,double prijs, VerkoopType verkoop,double korting, Date verkoopDatum, Date heenDatum) {
		super(aankoopId,korting, prijs, verkoop);
		this.medewerkerId = medewerkerId;
		this.depZone = depZone;
		this.arrZone = arrZone;
		this.verkoopStation = verkoopStation;
		this.verkoopDatum = verkoopDatum;
		this.heenDatum = heenDatum;
	}

	
	public int getMedewerkerId() {
		return medewerkerId;
	}

	public void setMedewerkerId(int medewerkerId) {
		this.medewerkerId = medewerkerId;
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

	public Date getVerkoopDatum() {
		return verkoopDatum;
	}

	public void setVerkoopDatum(Date verkoopDatum) {
		this.verkoopDatum = verkoopDatum;
	}
	
	public int getKlasse() {
		return klasse;
	}

	public void setKlasse(int klasse) {
		this.klasse = klasse;
	}

	public int getAantal() {
		return aantal;
	}

	public void setAantal(int aantal) {
		this.aantal = aantal;
	}

	public Date getHeenDatum() {
		return heenDatum;
	}

	public void setHeenDatum(Date heenDatum) {
		this.heenDatum = heenDatum;
	}

	public Date getTerugDatum() {
		return terugDatum;
	}

	public void setTerugDatum(Date terugDatum) {
		this.terugDatum = terugDatum;
	}

	public int getVerkoopStation() {
		return verkoopStation;
	}

	public void setVerkoopStation(int verkoopStation) {
		this.verkoopStation = verkoopStation;
	}
}
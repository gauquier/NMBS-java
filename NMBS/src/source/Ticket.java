package source;
import java.util.Calendar;
import java.util.Date;

import source.Aankoop;
import source.VerkoopType;

public class Ticket extends Aankoop {
	private int ticketId;
	private int medewerkerId;
	private String depZone;
	private String arrZone;
	private boolean actief;
	private Calendar verkoopDatum;
	
	
	

	public Ticket(int aankoopId, double korting, double prijs, VerkoopType verkoop, int ticketId, int medewerkerId, String depZone, String arrZone, boolean actief , Calendar verkoopDatum) {
		super(aankoopId, korting, prijs, verkoop);
		setTicketId(ticketId);
		setMedewerkerId(medewerkerId);
		setDepZone(depZone);
		setArrZone(arrZone);
		setActief(actief);
		setVerkoopDatum(verkoopDatum);
	}

	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	
	public boolean getActief() {
		return actief;
	}
	public void setActief(boolean actief) {
		this.actief = actief;
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

	public Calendar getVerkoopDatum() {
		return verkoopDatum;
	}

	public void setVerkoopDatum(Calendar verkoopDatum) {
		this.verkoopDatum = verkoopDatum;
	}
}
package source;
import java.util.Date;

import source.Aankoop.VerkoopType;

public class Ticket extends Aankoop {
	private int ticketId;
	private Date dag;
	private String dep;
	private String arr;
	private int aantal;
	private boolean actief;
	
	//constructor zonder aankoopId en ticketId maken?
	
	public Ticket(int aankoopId, double korting, double prijs, VerkoopType verkoop, int ticketId, Date dag, String dep, String arr, int aantal, boolean actief) {
		super(aankoopId, korting, prijs, verkoop);
		setTicketId(ticketId);
		setDag(dag);
		setDep(dep);
		setArr(arr);
		setAantal(aantal);
		setActief(actief);
	}
	
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public String getDep() {
		return dep;
	}
	public void setDep(String dep) {
		this.dep = dep;
	}
	public String getArr() {
		return arr;
	}
	public void setArr(String arr) {
		this.arr = arr;
	}
	public int getAantal() {
		return aantal;
	}
	public void setAantal(int aantal) {
		this.aantal = aantal;
	}
	public boolean isActief() {
		return actief;
	}
	public void setActief(boolean actief) {
		this.actief = actief;
	}
	
}
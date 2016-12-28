package source;

public class Ticketstatistiek {
	private String verkoopdatum;
	private int verkochteTickets;
	
	public Ticketstatistiek(String verkoopdatum, int verkochteTickets) {
		setVerkoopdatum(verkoopdatum);
		setVerkochteTickets(verkochteTickets);
	}
	
	public String getVerkoopdatum() {
		return verkoopdatum;
	}
	public void setVerkoopdatum(String verkoopdatum) {
		this.verkoopdatum = verkoopdatum;
	}
	public int getVerkochteTickets() {
		return verkochteTickets;
	}
	public void setVerkochteTickets(int verkochteTickets) {
		this.verkochteTickets = verkochteTickets;
	}
}

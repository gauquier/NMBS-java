package source;

public class Ticketstatistiek {
	private String verkoopdatum;
	private int verkochteTickets;

	public Ticketstatistiek(String verkoopdatum, int verkochteTickets) {
		this.setVerkoopdatum(verkoopdatum);
		this.setVerkochteTickets(verkochteTickets);
	}

	public String getVerkoopdatum() {
		return this.verkoopdatum;
	}

	public void setVerkoopdatum(String verkoopdatum) {
		this.verkoopdatum = verkoopdatum;
	}

	public int getVerkochteTickets() {
		return this.verkochteTickets;
	}

	public void setVerkochteTickets(int verkochteTickets) {
		this.verkochteTickets = verkochteTickets;
	}
}

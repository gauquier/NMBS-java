package source;

import java.util.ArrayList;

public class TicketsOffline {
	private static ArrayList<Ticket> tickets = new ArrayList<Ticket>();

	public TicketsOffline() {

	}

	public static ArrayList<Ticket> getTickets() {
		return tickets;
	}

	public static void setTickets(ArrayList<Ticket> tickets) {
		TicketsOffline.tickets = tickets;
	}

	public static void addTicket(Ticket ticket) {
		tickets.add(ticket);
	}
}

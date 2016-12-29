package handler;

import java.text.SimpleDateFormat;
import java.util.Date;

import dao.StationDAO;
import dao.TicketDao;
import gui.TicketVerkoopGui;
import source.Ticket;
import source.TicketsOffline;

public class VerkoopController {
	public VerkoopController() {
	}

	public static boolean ticketValidate(Ticket ticket, TicketVerkoopGui tvGui, boolean isOffline) {

		boolean depZone = false, arrZone = false, klasse = false, aantal = false, heenDatum = false, terugDatum = false,
				prijs = false;

		if (!isOffline) {
			if (StationDAO.checkStation(ticket.getDepZone()) != 0) {
				depZone = true;
			} else {
				depZone = false;
			}

			if (StationDAO.checkStation(ticket.getArrZone()) != 0) {
				arrZone = true;
			} else {
				arrZone = false;
			}
		} else {
			depZone = true;
			arrZone = true;
		}

		if (ticket.getKlasse() == 1 || ticket.getKlasse() == 2) {
			klasse = true;
		} else {
			klasse = false;
		}
		if (ticket.getAantal() >= 1) {
			aantal = true;
		} else {
			aantal = false;
		}
		SimpleDateFormat compareFormat = new SimpleDateFormat("yyyyMMdd");

		if (Integer.parseInt(compareFormat.format(ticket.getHeenDatum())) >= Integer
				.parseInt(compareFormat.format(new Date()))) {
			heenDatum = true;
		} else {
			heenDatum = false;
		}
		if (ticket.getTerugDatum() == null || Integer.parseInt(compareFormat.format(ticket.getTerugDatum())) >= Integer
				.parseInt(compareFormat.format(ticket.getHeenDatum()))
				&& Integer.parseInt(compareFormat.format(ticket.getTerugDatum())) >= Integer
						.parseInt(compareFormat.format(new Date()))) {
			terugDatum = true;
		} else {
			terugDatum = false;
		}
		if (ticket.getPrijs() < 0) {
			prijs = false;
		} else {
			prijs = true;
		}

		// System.out.println(compareFormat.format(ticket.getHeenDatum()) + " "
		// + compareFormat.format(ticket.getTerugDatum()) + " " +
		// compareFormat.format(new Date()));
		tvGui.setColor(depZone, arrZone, klasse, aantal, heenDatum, terugDatum, prijs);
		if (!depZone || !arrZone) {
			// JOptionPane.showMessageDialog(new JFrame(), "Vul alle velden
			// in!");
		}

		if (depZone && arrZone && klasse && aantal && heenDatum && terugDatum && prijs) {
			if (!isOffline) {
				// ticket.setPrijs(PrijsDAO.getPrijsByVerkoopType(ticket.getVerkoop()));
				TicketDao.insertTicket(ticket);
			} else {
				TicketsOffline.addTicket(ticket);
			}
			tvGui.setTickettenVerkocht(true);
			return true;
		} else {
			tvGui.setTickettenVerkocht(false);

			return false;

		}
	}
}
package handler;

import java.util.Date;

import dao.StationDAO;
import gui.TicketVerkoopGui;
import source.Ticket;

public class VerkoopController {
	private Ticket ticket = null;
	boolean validate = false;

	private TicketVerkoopGui ticketVerkoopGUI = new TicketVerkoopGui();
	
	
	public VerkoopController(){
		Controller.adminInterface.getContentPane().add(ticketVerkoopGUI);
		ticketVerkoopGUI.setVisible(true);
		ticketVerkoop();
	}
	
	
	private void ticketVerkoop(){
		boolean depZone = false, arrZone = false, klasse = false, aantal = false, heenDatum = false, terugDatum = false;
		while(!validate){
			while(ticket == null){
			ticket = ticketVerkoopGUI.getTicket();
			}
			if(StationDAO.checkStationZone(ticket.getDepZone()) != 0){
				depZone = true;
			}
			else{
				depZone = false;
			}
			if(StationDAO.checkStationZone(ticket.getArrZone()) != 0){
				arrZone = true;
			}
			else{
				arrZone = false;
			}
			if(ticket.getKlasse() == 1 || ticket.getKlasse() == 2){
				klasse = true;
			}
			else{
				klasse = false;
			}
			if(ticket.getAantal() >= 1){
				aantal = true;
			}
			else{
				aantal = false;
			}
			if(ticket.getHeenDatum().equals(new Date()) || ticket.getHeenDatum().after(new Date())){
				heenDatum = true;
			}
			else{
				heenDatum = false;
			}
			if(ticket.getTerugDatum().equals(ticket.getHeenDatum()) || ticket.getTerugDatum().after(ticket.getHeenDatum())){
				terugDatum = true;
			}
			else{
				terugDatum = false;
			}
			
			ticketVerkoopGUI.setColor(depZone, arrZone, klasse, aantal, heenDatum, terugDatum);
			
			if(depZone && arrZone && klasse && aantal && heenDatum && terugDatum){
				validate = true;
			}
			else{
				validate = false;
			}
		}
	}
}

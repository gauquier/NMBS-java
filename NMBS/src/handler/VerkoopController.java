package handler;

import java.text.SimpleDateFormat;
import java.util.Date;

import dao.StationDAO;
import dao.TicketDao;
import gui.TicketVerkoopGui;
import source.Ticket;
import dao.PrijsDAO;

public class VerkoopController {	
	public VerkoopController(){}
	
	public static boolean ticketValidate(Ticket ticket, TicketVerkoopGui tvGui){
		
		boolean depZone = false, arrZone = false, klasse = false, aantal = false, heenDatum = false, terugDatum = false;

		if(StationDAO.checkStation(ticket.getDepZone()) != 0){
			depZone = true;
		}
		else{
			depZone = false;
		}
		if(StationDAO.checkStation(ticket.getArrZone()) != 0){
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
	      SimpleDateFormat compareFormat = new SimpleDateFormat("yyyyMMdd");
	      
		if(Integer.parseInt(compareFormat.format(ticket.getHeenDatum())) >= Integer.parseInt(compareFormat.format(new Date()))){
			heenDatum = true;
		}
		else{
			heenDatum = false;
		}
		if(Integer.parseInt(compareFormat.format(ticket.getTerugDatum())) >= Integer.parseInt(compareFormat.format(new Date()))){
			terugDatum = true;
		}
		else{
			terugDatum = false;
		}

		System.out.println(compareFormat.format(ticket.getHeenDatum()) + "         " + compareFormat.format(ticket.getTerugDatum()) + "         " + compareFormat.format(new Date()));
		tvGui.setColor(depZone, arrZone, klasse, aantal, heenDatum, terugDatum);
		
		if(depZone && arrZone && klasse && aantal && heenDatum && terugDatum){
			ticket.setPrijs(PrijsDAO.getPrijsByVerkoopType(ticket.getVerkoop()));
			TicketDao.insertTicket(ticket);
			tvGui.setTickettenVerkocht(true, ticket);
			return true;
		}
		else{
			tvGui.setTickettenVerkocht(false, ticket);
			
			return false;
			
		}		
	}
	
	/*
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
	*/
}
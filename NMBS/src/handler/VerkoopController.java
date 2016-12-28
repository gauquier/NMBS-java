package handler;

import java.sql.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dao.StationDAO;
import dao.TicketDao;
import gui.TicketVerkoopGui;
import source.Ticket;
import source.TicketsOffline;
import dao.PrijsDAO;

public class VerkoopController {	
	public VerkoopController(){}
	
	public static boolean ticketValidate(Ticket ticket, TicketVerkoopGui tvGui, boolean isOffline){
		
		boolean depZone = false, arrZone = false, klasse = false, aantal = false, heenDatum = false, terugDatum = false, prijs = false;

		if (!isOffline) {
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
		}
		else {
			depZone = true;
			arrZone = true;
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
		if(Integer.parseInt(compareFormat.format(ticket.getTerugDatum())) >= Integer.parseInt(compareFormat.format(ticket.getHeenDatum())) && Integer.parseInt(compareFormat.format(ticket.getTerugDatum())) >= Integer.parseInt(compareFormat.format(new Date()))){
			terugDatum = true;
		}
		else{
			terugDatum = false;
		}
		if(ticket.getPrijs() < 0){
			prijs = false;
		}
		else{
			prijs = true;
		}

		//System.out.println(compareFormat.format(ticket.getHeenDatum()) + "         " + compareFormat.format(ticket.getTerugDatum()) + "         " + compareFormat.format(new Date()));
		tvGui.setColor(depZone, arrZone, klasse, aantal, heenDatum, terugDatum, prijs);
		if(!depZone || !arrZone) {
			//JOptionPane.showMessageDialog(new JFrame(), "Vul alle velden in!");
		}
		
		if(depZone && arrZone && klasse && aantal && heenDatum && terugDatum && prijs){
			if (!isOffline) {
				//ticket.setPrijs(PrijsDAO.getPrijsByVerkoopType(ticket.getVerkoop()));
				TicketDao.insertTicket(ticket);
			}
			else {
				TicketsOffline.addTicket(ticket);
			}
			tvGui.setTickettenVerkocht(true);
			return true;
		}
		else{
			tvGui.setTickettenVerkocht(false);
			
			return false;
			
		}		
	}
	/*
	private static boolean isDate(Date date){
		DateFormat month = new SimpleDateFormat("MM");
		DateFormat day = new SimpleDateFormat("dd");
		ArrayList<Integer> month31 = new ArrayList<>(Arrays.asList(1,3,5,7,8,10,12));
		ArrayList<Integer> month30 = new ArrayList<>(Arrays.asList(4,6,9,11));
		int month28 = 2;
		
		if(Integer.parseInt(day.format(date)) > 31 || Integer.parseInt(day.format(date)) < 1 || Integer.parseInt(month.format(date)) > 12 || Integer.parseInt(month.format(date)) < 1){
			return false;
		}
		if(month31.contains(Integer.parseInt(month.format(date)))){
			System.out.println("month31" + day.format(date) + month.format(date));
			return true;
		}
		if(month30.contains(Integer.parseInt(month.format(date))) && Integer.parseInt(day.format(date)) < 30){
			System.out.println("month30" + day.format(date) + month.format(date));
			return true;
		}
		if(Integer.parseInt(month.format(date)) == month28 && Integer.parseInt(day.format(date)) < 28){
			System.out.println("month28" + day.format(date) + month.format(date));
			return true;
		}
		return false;
	}
	*/
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
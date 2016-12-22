package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import source.Klant;
import source.Persoon;
import source.Ticket;
import source.Ticketstatistiek;
import source.VerkoopType;


public class TicketDao {


	private static DBA dba = new DBA();
	
	public static int insertTicket(Ticket ticket){
		if(getId(ticket) == 0){
			dba.createInsert("Ticket");
			dba.addValue(ticket.getMedewerkerId());
			dba.addValue(ticket.getDepZone());
			dba.addValue(ticket.getArrZone());
			dba.addValue(ticket.getVerkoopStation());
			dba.addValue(ticket.getPrijs());
			dba.addValue(ticket.getVerkoop().toString()); 
			dba.addValue(ticket.getKorting());
			dba.addValue(ticket.getKlasse());
			dba.addValue(ticket.getAantal());
			dba.addValue(ticket.getVerkoopDatum());
			dba.addValue(ticket.getHeenDatum());
			dba.addValue(ticket.getTerugDatum());
			dba.commit();
		}
		return getId(ticket);
	}
	
	public static int getId(Ticket ticket){
		
		
		dba.createSelect("Ticket", "ticketId");
		dba.addWhere("medewerkerId", ticket.getMedewerkerId()); 
		dba.addWhere("depZone", ticket.getDepZone());
		dba.addWhere("arrZone", ticket.getArrZone());
		dba.addWhere("verkoopStation", ticket.getVerkoopStation());
		dba.addWhere("prijs", ticket.getPrijs());
		dba.addWhere("verkoopType", ticket.getVerkoop().toString());
		dba.addWhere("korting", ticket.getKorting());
		dba.addWhere("klasse", ticket.getKlasse());
		dba.addWhere("aantal", ticket.getAantal());
		dba.addWhere("verkoopdatum", ticket.getVerkoopDatum());
		dba.addWhere("heenDatum", ticket.getHeenDatum());
		dba.addWhere("terugDatum", ticket.getTerugDatum());
		ResultSet rs = dba.commit();
		try {
			if(rs.next()){
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return 0;
	}
	
	public static Ticket getTicket(int id){
		Ticket ticket = null;
		dba.createSelect("Ticket");
		dba.addWhere("ticketId", id);
		ResultSet rs = dba.commit();	
		try {
			if(rs.next()){
				DateFormat dt = new SimpleDateFormat("dd-MM-YYYY");// aan te passen wijzing boris
				ticket = new Ticket(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDouble(6), VerkoopType.VerkoopTypeCasting(rs.getString(7)), rs.getDouble(8), rs.getInt(9), rs.getInt(10), dt.parse(rs.getString(11)),dt.parse(rs.getString(12)),dt.parse(rs.getString(13)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ticket;
		

	}
	
	public static ArrayList<Ticketstatistiek> getTicketstatistieken(){
		/*
		 	SELECT verkoopDatum, COUNT(verkoopDatum)
			FROM Ticket
			GROUP BY verkoopDatum
			LIMIT 5;
		*/
		ArrayList<Ticketstatistiek> ticketstatistieken = new ArrayList<Ticketstatistiek>();
		dba.createTicketstatistiekenSelect();
		ResultSet rs = dba.commit();
		
		//gebaseerd op http://stackoverflow.com/questions/5301226/convert-string-to-calendar-object-in-java
		//Calendar tijd = Calendar.getInstance();
		//SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
		
		try {
			while(rs.next()){
				//tijd.setTime(sdf.parse(rs.getString(1)));
				//ticketstatistieken.add(new Ticketstatistiek(tijd,rs.getInt(2)));
				ticketstatistieken.add(new Ticketstatistiek(rs.getString(1),rs.getInt(2)));
			}
			return ticketstatistieken;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}

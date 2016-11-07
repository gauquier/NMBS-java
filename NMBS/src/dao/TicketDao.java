package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import source.Ticket;


public class TicketDao {


	private DBA dba = new DBA();
	
	public int insertTicket(Ticket ticket){
		if(getId(ticket) == 0){
			dba.createInsert("Ticket");
			dba.addValue(ticket.getMedewerkerId());
			dba.addValue(ticket.getDepZone());
			dba.addValue(ticket.getArrZone());
			dba.addValue(ticket.getPrijs());
			dba.addValue(ticket.getVerkoop());
			dba.addValue(ticket.getKorting());
			dba.addValue(ticket.getActief());
			dba.addValue(ticket.getVerkoopDatum());
			dba.commit();
		}
		return getId(ticket);
	}
	
	public int getId(Ticket ticket){
		
		dba.createSelect("Ticket", "tickedId");
		dba.addWhere("medewerkerId", ticket.getMedewerkerId()); //VERANDEREN NAAR HUIDIG INGELOGDE MEDEWERKER
		dba.addWhere("depZone", ticket.getDepZone());
		dba.addWhere("arrZone", ticket.getArrZone());
		// -> ANDERE ATTRIBUTEN VAN AANKOOP KLASSE
		dba.addWhere("actief", 1);
		dba.addWhere("verkoopdatum", ticket.getVerkoopDatum());
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
	
	/*public Ticket getTicket(int id){
		Ticket ticket = null;
		dba.createSelect("Ticket");
		dba.addWhere("ticketId", id);
		ResultSet rs = dba.commit();	
		try {
			if(rs.next()){
				ticket = new Ticket(rs.getInt(1),rs.getDouble(2), rs.getDouble(3), rs.getObject(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getBoolean(9), rs.getString(10));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ticket;
		
	->TICKETCONSTRUCTOR MET VERKOOPTYPE -> STRING en CALENDAR
	}*/


}





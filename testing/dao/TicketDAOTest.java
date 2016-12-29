package dao;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import source.Adres;
import source.Login;
import source.Medewerker;
import source.Persoon;
import source.Rol;
import source.Station;
import source.Ticket;
import source.VerkoopType;

public class TicketDAOTest {
	private String testClassName = "TicketDAOTest"; 
	private Persoon medewerker;
	private Station verkoopStation;
	private Ticket ticket;
	@Before
	public void initialize() throws ParseException { 
		medewerker = new Medewerker(0, 
				"voornaam"+testClassName, "achternaam"+testClassName,
				"email"+testClassName, 
				new Adres("straatnaam" + testClassName, 170, "woonplaats" + testClassName, 1070, "6") 
				);
		((Medewerker)medewerker).setLogin(
				new Login("username"+ testClassName, "password"+ testClassName)
				);
		((Medewerker)medewerker).setRol(
				new Rol(0, "rol"+ testClassName)
				);
		medewerker=Help.medewerkerToevoegen((Medewerker)medewerker);
		verkoopStation=Help.stationToevoegen(new Station(0,testClassName));
		DateFormat date = new SimpleDateFormat("dd-MM-yyyy"); 
		
		ticket=new Ticket(0,
				((Medewerker)medewerker).getMedewerkerId() , 
				"depZone"+testClassName, 
				"arrZone"+testClassName, 
				verkoopStation.getStationID(), 
				9119, 
				VerkoopType.STANDAARD,
				1, 2,1,
				date.parse("26-12-2999"),
				date.parse("26-12-2999"),
				date.parse("26-12-2999")); 
}

	@After
	public void terminate() {
		ticketVerwijderen();
	} 
	@Test
	public void testInsertTicket() {
		int nieuwTicketId=TicketDao.insertTicket(ticket);
	assertEquals(Help.ticketOphalen(ticket).getAankoopId(), nieuwTicketId);
	System.out.println("");
	
	}
	@Test
	public void testGetId() {
		Ticket ticketTmp= Help.ticketToevoegen(ticket); 
	assertEquals(ticketTmp.getAankoopId(), TicketDao.getId(ticket));
	}
	@Test
	public void testGetIdMetOnbestaandeTicket() { 
	assertEquals(0, TicketDao.getId(ticket));
	}
	@Test
	public void testGetTicket() { 
		Ticket ticketTmp= Help.ticketToevoegen(ticket);
	assertEquals(ticketTmp, TicketDao.getTicket(ticketTmp.getAankoopId()));
	}
	@Test
	public void testGetTicketMetOnbestaandeTickeId() {
		int onbestaandeTickeId=9119;
		assertNull(TicketDao.getTicket(onbestaandeTickeId));
	}
	
	
	
private void ticketVerwijderen() {
	
	String verwijderTicket = "DELETE FROM Ticket WHERE depZone LIKE '%"+testClassName+"%'";
	
	String verwijderMedewerker = "DELETE FROM Medewerker WHERE persoonId= ?";
	
	String verwijderPersoon = "DELETE FROM Persoon WHERE voornaam LIKE '%"+testClassName+"%'";
	
	String verwijderRol = "DELETE FROM Rol WHERE rol LIKE '%"+testClassName+"%'"; 
	String verwijderAdres = "DELETE FROM Adres WHERE straat LIKE '%"+testClassName+"%'";
	String verwijderLogin = "DELETE FROM Login WHERE username LIKE '%"+testClassName+"%'";
	String verwijderStation = "DELETE FROM Station WHERE naam LIKE '%"+testClassName+"%'";
	
	Help.executeQuery(verwijderTicket, true);
	
	Help.executeQuery(verwijderMedewerker, true, medewerker.getId());
	
	Help.executeQuery(verwijderPersoon, true);
	
	 
	Help.executeQuery(verwijderRol, true); 
	Help.executeQuery(verwijderAdres, true);
	Help.executeQuery(verwijderLogin, true);
	Help.executeQuery(verwijderStation, true); 
		
	}
}

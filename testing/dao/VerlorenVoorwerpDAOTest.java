package dao;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import source.Station;
import source.VerlorenVoorwerp;

public class VerlorenVoorwerpDAOTest {
	private String testClassName = "VerlorenVoorwerpDAOTest";
	private Station station;
	private VerlorenVoorwerp verlorenVoorwerp;
	@Before
	public void initialize() throws ParseException{
		station=Help.stationToevoegen(new Station(0,testClassName)); 
		DateFormat date = new SimpleDateFormat("dd-MM-yyyy");  
		verlorenVoorwerp=new VerlorenVoorwerp(0, testClassName, date.parse("26-12-2016"), false);
	} 
	@After
	public void terminate() {
		verlorenVoorwerpVerwijderen();
	}  
	@Test
	public void testInsertVerlorenVoorwerp() { 
		int nieuweVerlorenVoorwerpId= VerlorenVoorwerpDAO.insertVerlorenVoorwerp(verlorenVoorwerp, station.getStationID());
		assertEquals(Help.verlorenVoorwerpOphalen(verlorenVoorwerp).getVerlorenVoorwerpId(), nieuweVerlorenVoorwerpId); 
	}
	@Test
	public void testGetId() { 
		VerlorenVoorwerp verlorenVoorwerpTmp=Help.verlorenVoorwerpToevoegen(verlorenVoorwerp, station.getStationID()); 
		assertEquals(verlorenVoorwerpTmp.getVerlorenVoorwerpId(), VerlorenVoorwerpDAO.getId(verlorenVoorwerpTmp));  
	}
	@Test
	public void testGetIdMetOnbestaandeVerlorenVoorwerp() { 
		assertEquals(0, VerlorenVoorwerpDAO.getId(verlorenVoorwerp)); 
	} 
	@Test
	public void testGetVerlorenVoorwerpByStation() { 
		Help.verlorenVoorwerpToevoegen(verlorenVoorwerp, station.getStationID()); 
		assertEquals(1, VerlorenVoorwerpDAO.getVerlorenVoorwerpByStation(station.getStationID()).size()); 
	}@Test
	public void testGetVerlorenVoorwerpByStationMetOnbestaandeStationId() { 
		int onbestaandeStationId=9119;
		 assertEquals(0, VerlorenVoorwerpDAO.getVerlorenVoorwerpByStation(onbestaandeStationId).size()); 
	}
	
	private void verlorenVoorwerpVerwijderen() {
		String verwijderVerlorenVoorwerp = "DELETE FROM VerlorenVoorwerp WHERE beschrijving LIKE '%"+testClassName+"%'";
		
		String verwijderStation = "DELETE FROM Station WHERE naam LIKE '%"+testClassName+"%'";
		
		Help.executeQuery(verwijderVerlorenVoorwerp, true);
	 
		Help.executeQuery(verwijderStation, true);
	}

}

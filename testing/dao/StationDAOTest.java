package dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import source.Station;

public class StationDAOTest {
private String testClassName = "StationDAOTest";
private Station station;
private StationDAO stationDao;
	@Before
	public void initialize(){
		station=new Station(0,testClassName);
		stationDao= new StationDAO();
	} 
	@After
	public void terminate() {
		stationVerwijderen();
	} 
	@Test
	public void testInsertStation() {
		int nieuwStationId=stationDao.insertStation(station);
		assertEquals(Help.stationOphalen(station).getStationID(), nieuwStationId);
	}
	@Test(expected=Exception.class)
	public void testInsertStationMetBestaandeStation() {
		 stationDao.insertStation(station); 
		 stationDao.insertStation(station); 
	}
	@Test
	public void testGetStationId() {
		Station stationTmp= Help.stationToevoegen(station); 
		int stationId=stationDao.getStationId(station);
		assertEquals(stationTmp.getStationID(), stationId);
	}
	@Test
	public void testGetStationIdMetOnbestaandeStation() { 
		int stationId=stationDao.getStationId(station);
		assertEquals(0, stationId);
	}
	@Test
	public void testGetStation() {
		Station stationDB= Help.stationToevoegen(station); 
		Station stationTmp=stationDao.getStation(stationDB.getStationID());
		assertEquals(stationDB, stationTmp);
	}
	@Test
	public void testGetStationMetOnbestaandeStationId() { 
		int onbestaandeStationId=9119;
		Station stationTmp=stationDao.getStation(onbestaandeStationId);
		assertNull(stationTmp);
	}
	@Test
	public void testGetAll() { 
		int before=StationDAO.getAll().size();
		Help.stationToevoegen(station);
		int after=StationDAO.getAll().size();
		assertEquals(before+1, after);
	}
	private void stationVerwijderen() {
		String verwijderStation = "DELETE FROM Station WHERE naam LIKE '%"+testClassName+"%'";
		Help.executeQuery(verwijderStation, true);
	}

}

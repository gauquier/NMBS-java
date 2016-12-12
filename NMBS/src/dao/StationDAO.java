package dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.PerVmServerConfigCacheFactory;

import source.Station;
import source.VerlorenVoorwerp;
import source.api.aangepast.*;

public class StationDAO {

	private static DBA dba = new DBA();
	private VerlorenVoorwerpDAO vvDAO = new VerlorenVoorwerpDAO();

	public int insertStation(Station station) {
		if (getStationId(station) == 0) {
			dba.createInsert("Station");
			dba.addValue(station.getNaam());
			dba.commit();
		}
		for (int i = 0; i < station.getVerlorenVoorwerpen().size(); i++) {
			vvDAO.insertVerlorenVoorwerp(station.getVerlorenVoorwerpen().get(i), station.getStationID());
		}
		return getStationId(station);
	}

	public int getStationId(Station station) {

		dba.createSelect("Station", "stationId");
		dba.addWhere("naam", station.getNaam());  
		ResultSet rs = dba.commit();
		try {
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public int checkStation(String naam) {
		dba.createSelect("Station", "naam");
		// dba.addWhere("naam", station.getNaam());

		ResultSet rs = dba.commit();
		try {
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static int checkStationZone(String stationZone) {

		dba.createSelect("Station", "stationId");
		dba.addWhere("zone", stationZone);
		ResultSet rs = dba.commit();
		try {
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public Station getStation(int id) {
		Station station = null;
		dba.createSelect("Station");
		dba.addWhere("stationId", id);
		ResultSet rs = dba.commit();
		try {
			if (rs.next()) {
				station = new Station(rs.getInt(1), rs.getString(2), vvDAO.getVerlorenVoorwerpByStation(rs.getInt(1)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch blocks
			e.printStackTrace();
		}
		return station;
	} 
	public List<Station> getStations() {
		dba.createSelect("Station");
		ResultSet rs = dba.commit();
		List<Station> stations = new ArrayList<>();
		VerlorenVoorwerpDAO vvDAO = new VerlorenVoorwerpDAO();
		try {
			while (rs.next()) {
				stations.add( new Station(rs.getInt(1), rs.getString(2), vvDAO.getVerlorenVoorwerpByStation(rs.getInt(1))));
			}
			return stations;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	} 
	public List<Station> getStationsLazyLoading() {
		dba.createSelect("Station");
		ResultSet rs = dba.commit();
		List<Station> stations = new ArrayList<>();
		VerlorenVoorwerpDAO vvDAO = new VerlorenVoorwerpDAO();
		try {
			while (rs.next()) {

				stations.add( new Station(rs.getInt(1), rs.getString(2), null)); 
			}
			return stations;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	} 
	public void updateStationsInDB() {
		try {
			List<Station> bestaandeStations = getStations();
			List<Station> nieuwStations = new ArrayList<>();
			for (Station station : Parser.parseStations()) {
				if (!bestaandeStations.contains(station))
					nieuwStations.add(station);
			}
			for (Station station : nieuwStations)
				insertStation(station); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// public static void main(String[] args) {
	/*
	 * ArrayList<VerlorenVoorwerp> vv = new ArrayList<VerlorenVoorwerp>();
	 * vv.add(new VerlorenVoorwerp(-1, "gsm", new java.sql.Date(11,11,1900),
	 * false)); Station s = new Station(-1, "Luxembourg","brussel", 2, 2, false,
	 * true, vv); StationDAO sd = new StationDAO();
	 * System.out.println(sd.getStationId(s)); /*sd.insertStation(s);
	 * System.out.println(sd.getStation(sd.getStationId(s)).getZone());
	 * System.out.println(sd.getStationId(f));
	 */
	// }
}

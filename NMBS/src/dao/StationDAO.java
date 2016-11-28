package dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import source.Station; 


public class StationDAO {

	private static DBA dba = new DBA();
	private VerlorenVoorwerpDAO vvDAO = new VerlorenVoorwerpDAO();
	
	public int insertStation(Station station){
		if(getStationId(station) == 0){
			dba.createInsert("Station");
			dba.addValue(station.getStationID());
			dba.addValue(station.getZone());
			dba.addValue(station.getAantalLoketten());
			dba.addValue(station.getParkingplaatsen());
			dba.addValue(station.getWifi());
			dba.addValue(station.getGehandicapte());
			dba.commit();
		}
		for(int i = 0; i < station.getVerlorenVoorwerpen().size(); i++){
			vvDAO.insertVerlorenVoorwerp(station.getVerlorenVoorwerpen().get(i));
		}
		return getStationId(station);
	}

	public int getStationId(Station station){
		
		dba.createSelect("Station", "stationId");
		dba.addWhere("zone", station.getZone());
		dba.addWhere("aantalLoketten", station.getAantalLoketten());
		dba.addWhere("parkingPLaatsen", station.getParkingplaatsen());
		dba.addWhere("wifi", station.getWifi());
		dba.addWhere("gehandicapt", station.getGehandicapte());
		
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
	
	public static int getStationId(String station){
		
		dba.createSelect("Station", "stationId");
		dba.addWhere("zone", station);
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
	public static int checkStationZone(String stationZone){
		
		dba.createSelect("Station", "stationId");
		dba.addWhere("zone", stationZone);
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

	public Station getStation(int id){
		Station station = null;
		dba.createSelect("Station");
		dba.addWhere("stationId", id);
		ResultSet rs = dba.commit();	
		try {
			if(rs.next()){
				station = new Station(rs.getInt(1), rs.getString(2), rs.getShort(3), rs.getInt(4), rs.getBoolean(5), rs.getBoolean(6), vvDAO.getVerlorenVoorwerpByStation(rs.getInt(1)));
						}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return station;
	}

}

package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import source.Medewerker;
import source.Station;

public class StationDAO {

	private static DBA dba = new DBA();

	public int insertStation(Station station) {
		if (this.getStationId(station) == 0) {
			dba.createInsert("Station");
			dba.addValue(station.getNaam());
			dba.commit();
		}
		for (int i = 0; i < station.getVerlorenVoorwerpen().size(); i++) {
			VerlorenVoorwerpDAO.insertVerlorenVoorwerp(station.getVerlorenVoorwerpen().get(i), station.getStationID());
		}
		return this.getStationId(station);
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

	public static int checkStation(String naam) {
		dba.createSelect("Station", "stationId");
		dba.addWhere("naam", naam);

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

	public static ArrayList<Station> getAll() {
		dba.createSelect("Station");
		ArrayList<Station> lijst = new ArrayList<>();
		ResultSet rs = dba.commit();

		try {
			while (rs.next()) {
				Station station = new Station(rs.getInt(1), rs.getString(2));

				lijst.add(station);
			}
			
			Collections.sort(lijst, new Comparator<Station>() {

	            @Override
	            public int compare(Station m1, Station m2) {
	                if (m1.getNaam().toLowerCase().equals(m2.getNaam().toLowerCase())) {
	                    return 0;
	                }
	                return m1.getNaam().toLowerCase().compareTo(m2.getNaam().toLowerCase());
	            }
	        });
			
			return lijst;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public List<Station> getStations() {
		dba.createSelect("Station");
		ResultSet rs = dba.commit();
		List<Station> stations = new ArrayList<>();
		try {
			while (rs.next()) {
				stations.add(new Station(rs.getInt(1), rs.getString(2),
						VerlorenVoorwerpDAO.getVerlorenVoorwerpByStation(rs.getInt(1))));
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
		try {
			while (rs.next()) {

				stations.add(new Station(rs.getInt(1), rs.getString(2), null));
			}
			return stations;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import source.VerlorenVoorwerp;

public class VerlorenVoorwerpDAO {

	private DBA dba = new DBA();
	private StationDAO stationDAO;
	
	public int insertVerlorenVoorwerp(VerlorenVoorwerp verlorenVoorwerp){
		if(getVerlorenVoorwerpId(verlorenVoorwerp) == 0){
			dba.createInsert("VerlorenVoorwerp");
			dba.addValue(stationDAO.insertStation(verlorenVoorwerp.getStation()));
			dba.addValue(verlorenVoorwerp.getBeschrijving());
			dba.addValue(verlorenVoorwerp.getDate());
			dba.addValue(verlorenVoorwerp.getGevonden());
			dba.commit();
		}
		return getVerlorenVoorwerpId(verlorenVoorwerp);
	}
	
	public int getVerlorenVoorwerpId(VerlorenVoorwerp verlorenVoorwerp){
		
		dba.createSelect("VerlorenVoorwerp", "verlorenVoorwerpId");
		dba.addWhere("stationId", stationDAO.getStationId(verlorenVoorwerp.getStation())); 
		dba.addWhere("beschrijving", verlorenVoorwerp.getBeschrijving());
		dba.addWhere("datum", verlorenVoorwerp.getBeschrijving()	);
		dba.addWhere("gevonden", verlorenVoorwerp.getGevonden());
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
	
	
	public VerlorenVoorwerp getVerlorenVoorwerp(int id){
		VerlorenVoorwerp verlorenVoorwerp = null;
		dba.createSelect("Verlorenvoorwerp");
		dba.addWhere("verlorenVoorwerpId", id);
		ResultSet rs = dba.commit();	
		try {
			if(rs.next()){
				verlorenVoorwerp = new VerlorenVoorwerp(rs.getInt(1), stationDAO.getStation(rs.getInt(2)), rs.getString(3), rs.getDate(4), rs.getBoolean(5));
						}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return verlorenVoorwerp;
	}
	public ArrayList<VerlorenVoorwerp> getVerlorenVoorwerpByStation(int stationId){
		
		dba.createSelect("VerlorenVoorwerp");
		dba.addWhere("stationId", stationId);
		ResultSet rs = dba.commit();
		ArrayList<VerlorenVoorwerp> arr = new ArrayList<VerlorenVoorwerp>();
		
		try {
			while(rs.next()){
				Date datum = new Date();
				DateFormat df = new SimpleDateFormat("dd-MM-YYYY");
				datum = df.parse(rs.getString(4));
				arr.add(new VerlorenVoorwerp(rs.getInt(1), stationDAO.getStation(rs.getInt(2)), rs.getString(3), rs.getDate(4), rs.getBoolean(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	

}

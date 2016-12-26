package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import source.Station;
import source.VerlorenVoorwerp;

public class VerlorenVoorwerpDAO {

	private DBA dba = new DBA();
	private StationDAO stationDAO;
	
	public int insertVerlorenVoorwerp(VerlorenVoorwerp verlorenVoorwerp, int stationId){
		if(getId(verlorenVoorwerp) == 0)
		{
			dba.createInsert("VerlorenVoorwerp");
			dba.addValue(stationId);
			dba.addValue(verlorenVoorwerp.getBeschrijving());
			dba.addValue(verlorenVoorwerp.getDate());
			dba.addValue(verlorenVoorwerp.getGevonden());
			dba.commit();
		}
		return getId(verlorenVoorwerp);
	}
	
	public int getId(VerlorenVoorwerp vv){
		
		dba.createSelect("VerlorenVoorwerp", "verlorenVoorwerpId");
		dba.addWhere("beschrijving", vv.getBeschrijving());
		dba.addWhere("datum", vv.getDate()	);
		dba.addWhere("gevonden", vv.getGevonden());
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
		dba.createSelect("VerlorenVoorwerp");
		dba.addWhere("verlorenVoorwerpId", id);
		ResultSet rs = dba.commit();	
		try {
			if(rs.next()){
				verlorenVoorwerp = new VerlorenVoorwerp(rs.getInt(1), rs.getString(3), rs.getDate(4), rs.getBoolean(5));
						}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return verlorenVoorwerp;
	}
	
	private static Date formatDatum(String datum){
		DateFormat formatter;
		
		formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date geformateerdeDatum = null;
		try {
			geformateerdeDatum = formatter.parse(datum);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return geformateerdeDatum;
	}
	
	
	public ArrayList<VerlorenVoorwerp> getVerlorenVoorwerpByStation(int stationId){
		
		dba.createSelect("VerlorenVoorwerp");
		dba.addWhere("stationId", stationId);
		ResultSet rs = dba.commit();
		ArrayList<VerlorenVoorwerp> arr = new ArrayList<VerlorenVoorwerp>();
		
		try {
			while(rs.next()){
				/*Date datum = new Date();
				DateFormat df = new SimpleDateFormat("dd-MM-YYYY");
				datum = df.parse(rs.getString(4));*/
				arr.add(new VerlorenVoorwerp(rs.getInt(1), rs.getString(3), rs.getDate(4), rs.getBoolean(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /*catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		return arr;	
	}
	
	public static void main(String[] args) {
		/*@SuppressWarnings("deprecation")
		VerlorenVoorwerp v = new VerlorenVoorwerp(-1, "gsm", new java.sql.Date(11,11,2016), false);
		VerlorenVoorwerp s = new VerlorenVoorwerp(-1, "headPhone", new java.sql.Date(1, 2, 2013), true);
		VerlorenVoorwerpDAO vvd = new VerlorenVoorwerpDAO();
		vvd.insertVerlorenVoorwerp(s, 4);
		
		VerlorenVoorwerp za = vvd.getVerlorenVoorwerp(35);
		System.out.println(za.toString());
		ArrayList<VerlorenVoorwerp> ve = vvd.getVerlorenVoorwerpByStation(2);
		
		for (int i = 0; i < ve.size(); i++) {
			System.out.println(ve.get(i).toString());
		}*/
	}
	
	
}

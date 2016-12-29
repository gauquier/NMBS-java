package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import source.VerlorenVoorwerp;

public class VerlorenVoorwerpDAO {

	private static DBA dba = new DBA();
	
	public static int insertVerlorenVoorwerp(VerlorenVoorwerp verlorenVoorwerp, int stationId){
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
	
	public static int getId(VerlorenVoorwerp vv){
		
		dba.createSelect("VerlorenVoorwerp", "verlorenVoorwerpId");
		dba.addWhere("beschrijving", vv.getBeschrijving());
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
	
	public static ArrayList<VerlorenVoorwerp> getVerlorenVoorwerpByStation(int stationId){
		
		dba.createSelect("VerlorenVoorwerp");
		dba.addWhere("stationId", stationId);
		dba.addWhere("gevonden", 0);
		ResultSet rs = dba.commit();
		ArrayList<VerlorenVoorwerp> arr = new ArrayList<VerlorenVoorwerp>();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		try {
			while(rs.next()){
				arr.add(new VerlorenVoorwerp(rs.getInt(1), rs.getString(3), df.parse(rs.getString(4)), rs.getBoolean(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return arr;	
	}
	
	public static void verlorenVoowerpUpdate(int verlorenVoorwerpId) {
		dba.createUpdate("VerlorenVoorwerp", "gevonden", 1);
		dba.addWhere("verlorenVoorwerpId", verlorenVoorwerpId);
		dba.commit();

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
		/*VerlorenVoorwerpDAO verlorenVoorwerpDAO = new VerlorenVoorwerpDAO();
		VerlorenVoorwerp test = verlorenVoorwerpDAO.getVerlorenVoorwerp(33);
		System.out.println(test.toString());
		// Create an instance of SimpleDateFormat used for formatting 
		// the string representation of date (month/day/year)
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

		// Get the date today using Calendar object.
		Date today = test.getDate();        
		// Using DateFormat format method we can create a string 
		// representation of a date with the defined format.
		String reportDate = df.format(today);

		// Print what date is today!
		//System.out.println("Report Date: " + reportDate);
		//verlorenVoorwerpDAO.verlorenVoowerpUpdate(57, test);*/
		
		new VerlorenVoorwerpDAO();
		ArrayList<VerlorenVoorwerp> list = VerlorenVoorwerpDAO.getVerlorenVoorwerpByStation(1);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getDate());
		}
		
		
		
	}
	
	
}

package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import source.VerkoopTypeNew;

public class VerkoopTypeDAO {
	private static DBA dba = new DBA();
	
	public static double getPrijsByVerkoopType(String verkoopType) {
		dba.createSelect("VerkoopType", "prijs");
		dba.addWhere("verkoopType", verkoopType);
		ResultSet rs = dba.commit();
		try {
			if(rs.next())
			{
				return rs.getDouble(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	};
	
	public static ArrayList<VerkoopTypeNew> getAllVerkoopTypes(){
		ArrayList<VerkoopTypeNew> verkoopTypes = new ArrayList<VerkoopTypeNew>();
		dba.createSelect("VerkoopType");
		dba.addWhere("actief", true);
		ResultSet rs = dba.commit();
		try {
			while(rs.next()){
				verkoopTypes.add(new VerkoopTypeNew(rs.getInt(1),rs.getString(2),rs.getDouble(3)));
			}
			
			
			return verkoopTypes;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	public static void insertVerkoopType(VerkoopTypeNew verkoopType){
		dba.createInsert("VerkoopType");
		dba.addValue(verkoopType.getVerkoopType());
		dba.addValue(verkoopType.getPrijs());
		dba.commit();
	}
	*/
}

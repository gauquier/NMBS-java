package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import source.VerkoopType;

public class VerkoopTypeDao {
	private static DBA dba = new DBA();
	
	//work-in-progress / needs to be tested
	/*public static double getPrijsByVerkoopType(VerkoopType verkoopType) {
		dba.createSelect("VerkoopType", "prijs");
		dba.addWhere("verkoopType", verkoopType.name());
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
	};*/
}

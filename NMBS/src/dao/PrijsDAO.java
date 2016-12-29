package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import source.Prijs;
import source.VerkoopType;

public class PrijsDAO {
	private static DBA dba = new DBA();

	public static void updatePrijsByVerkoopType(String verkoopType, double nieuwePrijs) {
		dba.createUpdate("Prijs", "prijs", nieuwePrijs);
		dba.addWhere("verkoopType", verkoopType);
		dba.commit();
	}

	public static double getPrijsByVerkoopType(VerkoopType verkoopType) {
		dba.createSelect("Prijs", "prijs");
		dba.addWhere("verkoopType", verkoopType.name());
		ResultSet rs = dba.commit();
		try {
			if (rs.next()) {
				return rs.getDouble(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	};

	public static ArrayList<Prijs> getAllPrijzen() {
		ArrayList<Prijs> prijzen = new ArrayList<Prijs>();
		dba.createSelect("Prijs");
		ResultSet rs = dba.commit();
		try {
			while (rs.next()) {
				prijzen.add(new Prijs(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
			}

			return prijzen;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * public static void insertVerkoopType(Prijs prijs){
	 * dba.createInsert("Prijs"); dba.addValue(prijs.getVerkoopType());
	 * dba.addValue(prijs.getPrijs()); dba.commit(); }
	 */
}

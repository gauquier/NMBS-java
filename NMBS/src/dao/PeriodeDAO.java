package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import source.Abonnement;
import source.Periode;

public class PeriodeDAO {

	private static DBA dba = new DBA();

	private static java.sql.Connection connection;
	private static PreparedStatement stmt = null;

	public static void addPeriode(Periode periode, Abonnement abonnement, int medewerkerId) {

		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		dba.createInsert("Periode");
		dba.addValue(abonnement.getAbonnementId());
		dba.addValue(medewerkerId);
		dba.addValue(formatter.format(periode.getStartDate()));
		dba.addValue(formatter.format(periode.getEndDate()));
		dba.addValue(formatter.format(periode.getVerkoopdatum()));
		dba.commit();
	}

	public static void updatePeriode(Periode periode, int medewerkerId) {
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		try {
			connection = Connection.getDBConnection();

			stmt = connection.prepareStatement("UPDATE Periode SET medewerkerId=?, endDate=? WHERE periodeId=?");
			stmt.setInt(1, medewerkerId);
			stmt.setString(2, formatter.format(periode.getEndDate()));
			stmt.setInt(3, periode.getPeriodeId());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {// Handle errors for Class.forName
			e.printStackTrace();
		}

	}

	public static Periode getPeriode(Abonnement abonnement) {
		dba.createSelect("Periode");
		dba.addWhere("abonnementId", abonnement.getAbonnementId());
		ResultSet rs = dba.commit();
		try {
			if (rs.next()) {
				return new Periode(rs.getInt(1), rs.getInt(3), PeriodeDAO.formatDatum(rs.getString(4)),
						PeriodeDAO.formatDatum(rs.getString(5)), PeriodeDAO.formatDatum(rs.getString(6)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static Date formatDatum(String datum) {
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

}

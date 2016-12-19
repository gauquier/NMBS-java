package dao;

import source.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class PeriodeDAO {
	
private static DBA dba = new DBA();
	
	private static java.sql.Connection connection;
	private static Statement command;
	private static ResultSet data;
	private static PreparedStatement stmt = null;
	
	public static void addPeriode(Periode periode, Abonnement abonnement, int medewerkerId){
        
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		dba.createInsert("Periode");
		dba.addValue(abonnement.getAankoopId());
		dba.addValue(medewerkerId);
		dba.addValue(formatter.format(periode.getStartDate()));
		dba.addValue(formatter.format(periode.getEndDate()));
		dba.addValue(formatter.format(periode.getVerkoopdatum()));
		dba.commit();
	}
	
	public static Periode getPeriode(Abonnement abonnement){
		dba.createSelect("Periode");
		dba.addWhere("abonnementId", abonnement.getAbonnementId());
		ResultSet rs = dba.commit();
		try {
			if(rs.next()){
				return new Periode(rs.getInt(1), abonnement, rs.getInt(3), formatDatum(rs.getString(4)), formatDatum(rs.getString(5)), formatDatum(rs.getString(6)) );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
	
}

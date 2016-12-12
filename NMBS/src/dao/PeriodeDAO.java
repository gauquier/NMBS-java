package dao;

import source.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class PeriodeDAO {
	
private static DBA dba = new DBA();
	
	private static java.sql.Connection connection;
	private static Statement command;
	private static ResultSet data;
	private static PreparedStatement stmt = null;
	
	public static void addPeriode(Periode periode, Abonnement abonnement, Medewerker medewerker){
        
		dba.createInsert("Periode");
		dba.addValue(abonnement.getAankoopId());
		dba.addValue(medewerker.getMedewerkerId());
		dba.addValue(periode.getStartDate());
		dba.addValue(periode.getEndDate());
		dba.addValue(periode.getVerkoopdatum());
		dba.commit();
	}
	
	
}

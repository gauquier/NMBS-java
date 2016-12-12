package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import source.*;

public class AbonnementDAO {
	private static DBA dba = new DBA();
	
	private static java.sql.Connection connection;
	private static Statement command;
	private static ResultSet data;
	private static PreparedStatement stmt = null;
	
	public static void addAbonnement(Abonnement abonnement){
        
		dba.createInsert("Abonnement");
		dba.addValue(abonnement.getKlant().getKlantId());
		dba.addValue(abonnement.getDepZone());
		dba.addValue(abonnement.getArrZone());
		dba.addValue(abonnement.getPrijs());
		dba.addValue(abonnement.getVerkoop().toString());
		dba.addValue(abonnement.getKorting());
		dba.addValue(1);
		dba.commit();
	}
	
}

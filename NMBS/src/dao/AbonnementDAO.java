package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import source.*;

public class AbonnementDAO {
	private static DBA dba = new DBA();
	
	private static java.sql.Connection connection;
	private static Statement command;
	private static ResultSet data;
	private static PreparedStatement stmt = null;
	
	public static int addAbonnement(Abonnement abonnement){
        
		dba.createInsert("Abonnement");
		dba.addValue(abonnement.getKlant().getKlantId());
		dba.addValue(abonnement.getDepZone());
		dba.addValue(abonnement.getArrZone());
		dba.addValue(abonnement.getPrijs());
		dba.addValue(abonnement.getVerkoop().toString());
		dba.addValue(abonnement.getKorting());
		dba.addValue(1);
		return getLastId(dba.getSql()+");");
	}
	
	public static void removeAbonnement(int id){
		dba.createUpdate("Abonnement", "actief", 0);;
		dba.addWhere("abonnementId", id);
		ResultSet rs = dba.commit();
		
	}
	
	public static int getLastId(String query){
		int resultaat=0;
	    connection = Connection.getDBConnection();
		
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.executeUpdate();  
			ResultSet keys = pstmt.getGeneratedKeys();    
			keys.next();  
			resultaat = keys.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		
		
	  return resultaat;
      
    }
	
	public static ArrayList<Abonnement> getAllAbonnementen(){
		ArrayList<Abonnement> abonnementen = new ArrayList<Abonnement>();

		dba.createSelect("Abonnement");
		dba.addWhere("actief", true);
		ResultSet rs = dba.commit();
		try {
			while(rs.next()){
				abonnementen.add(new Abonnement(rs.getInt(1), KlantDAO.getKlant(rs.getInt(2)), rs.getString(3), rs.getString(4), rs.getDouble(5), VerkoopType.ABONNEMENT, rs.getDouble(7), rs.getBoolean(8) ));
			}
			
			
			return abonnementen;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void updatePrijs(Abonnement abonnement, double Prijs){
		dba.createUpdate("Abonnement", "prijs", Prijs);;
		dba.addWhere("abonnementId", abonnement.getAbonnementId());
		ResultSet rs = dba.commit();
		
	}
    
  
	
}

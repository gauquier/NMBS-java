package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import source.Abonnement;
import source.Adres;
import source.Klant;
import source.Periode;
import source.VerkoopType;

public class AbonnementDAO {
	private static DBA dba = new DBA();
	
	private static java.sql.Connection connection;
	
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

		String sql = "SELECT * FROM Abonnement b JOIN Klant k ON b.klantId = k.klantId JOIN Persoon p ON k.persoonId = p.persoonId JOIN Adres a ON p.adresId = a.adresId WHERE b.actief = true;";
		String sql2 = "SELECT * FROM Periode;";
		Statement stmt, stmt2;
		ResultSet rs = null, rs2 = null;
		java.sql.Connection conn = dao.Connection.getDBConnection();
		Periode periode;
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		DateFormat compareFormat = new SimpleDateFormat("yyyyMMdd");

		try {
			stmt = conn.prepareStatement(sql);
			stmt2 = conn.prepareStatement(sql2);
			rs = stmt.executeQuery(sql);
			rs2 = stmt2.executeQuery(sql2);
		
			while(rs.next()){
				periode = null;
				while(rs2.next()){
					if(rs2.getInt(2) == rs.getInt(1) && Integer.parseInt(compareFormat.format(df.parse(rs2.getString(4)))) < Integer.parseInt(compareFormat.format(new Date())) - 1 && Integer.parseInt(compareFormat.format(df.parse(rs2.getString(5)))) > Integer.parseInt(compareFormat.format(new Date()))){
						periode = new Periode(rs2.getInt(1), rs2.getInt(3), df.parse(rs2.getString(4)), df.parse(rs2.getString(5)), df.parse(rs2.getString(6)));
					}
				}
				rs2.absolute(0);
				
				abonnementen.add(new Abonnement(rs.getInt(1), new Klant(rs.getInt(13), rs.getString(15), rs.getString(16), rs.getString(17),
						new Adres(rs.getInt(18), rs.getString(19), rs.getInt(20), rs.getString(21), rs.getInt(22), rs.getString(23)), 
						rs.getInt(9), rs.getString(11), rs.getBoolean(12)), rs.getString(3), rs.getString(4), rs.getDouble(5),
						VerkoopType.valueOf(rs.getString(6)), rs.getDouble(7), periode, rs.getBoolean(8)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return abonnementen;
	}
	
	public static void updatePrijs(Abonnement abonnement, double Prijs){
		dba.createUpdate("Abonnement", "prijs", Prijs);;
		dba.addWhere("abonnementId", abonnement.getAbonnementId());
		ResultSet rs = dba.commit();
		
	}

  
	
}

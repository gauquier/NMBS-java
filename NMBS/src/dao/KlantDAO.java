package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import source.Adres;
import source.Klant;
import source.Persoon;
import source.Rol;

public class KlantDAO {
	
	private static DBA dba = new DBA();
	
	private static java.sql.Connection connection;
	private static Statement command;
	private static ResultSet data;
	private static PreparedStatement stmt = null;

	public static void addKlant(Persoon persoon, Adres adres, String info){
		int persoonId = 0;
		
        persoonId = PersoonDao.addPersoon(persoon);
        
		dba.createInsert("Klant");
		dba.addValue(persoonId);
		dba.addValue(info);
		dba.addValue(1);
		dba.commit();
	}
	
	public static ArrayList<Klant> getAllKlanten(){
		ArrayList<Klant> klanten = new ArrayList<Klant>();
		Persoon persoon = null;
		dba.createSelect("Klant");
		dba.addWhere("actief", true);
		ResultSet rs = dba.commit();
		try {
			while(rs.next()){
				persoon = PersoonDao.getPersoon(rs.getInt(2));
				klanten.add(new Klant(persoon.getId(), persoon.getVoornaam(), persoon.getAchternaam(), persoon.getEmail(), persoon.getAdres(),rs.getInt(1),rs.getString(3) , true));
			}
			
			
			return klanten;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void removeKlant(int id){
		dba.createUpdate("Klant", "actief", 0);;
		dba.addWhere("klantId", id);
		ResultSet rs = dba.commit();
		
	}
	
public static void bijwerkenKlant(int klantId, int persoonId, Persoon persoon, String info, int adresId, Adres adres){ 
		
	    try {
	        connection = Connection.getDBConnection();    
	       
	        stmt = connection.prepareStatement("UPDATE Persoon SET voornaam=?, achternaam=?, email=? WHERE persoonId=?");
	        stmt.setString(1,persoon.getVoornaam());
	        stmt.setString(2, persoon.getAchternaam());
	        stmt.setString(3, persoon.getEmail());
	        stmt.setInt(4, persoonId);
	        stmt.executeUpdate();
	        
	        
	        stmt = connection.prepareStatement("UPDATE Adres SET straat=?, huisnr=?, woonplaats=?, postcode=?, bus=? WHERE adresId=?");
	        stmt.setString(1,adres.getStraat());
	        stmt.setInt(2, adres.getHuisnr());
	        stmt.setString(3, adres.getWoonplaats());
	        stmt.setInt(4, adres.getPostcode());
	        stmt.setString(5, adres.getBus());
	        stmt.setInt(6, adresId);
	        stmt.executeUpdate();
	       
	    
	    }catch (SQLException e){
	        e.printStackTrace();
	    }catch(Exception e) {//Handle errors for Class.forName
	        e.printStackTrace();
	    }
		
	    
	    dba.createUpdate("Klant", "info", info);;
		dba.addWhere("klantId", klantId);
		ResultSet rs = dba.commit();
	    
	    
	}
	
}

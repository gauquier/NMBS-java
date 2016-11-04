package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import source.Adres;
import source.Login;
import source.Medewerker;
import source.Persoon;

public class PersoonDao {
	
	private static java.sql.Connection connection;
	private static Statement command;
	private static ResultSet data;
	private static PreparedStatement stmt = null;
	
	public static int addPersoon(Persoon persoon, Adres adres){
		int adresId = 0;
		adresId = AdressDAO.addAdress(adres);
		DataSource ds = null;
		
		try {
			adresId = AdressDAO.addAdress(adres);
	        if (connection == null){connection = Connection.getDBConnection();}
	        command = connection.createStatement();
	        
	        stmt = connection.prepareStatement("INSERT INTO Login (adresId, voornaam, achternaam) VALUES(?,?,?);");
	        stmt.setInt(1, adresId);
	        stmt.setString(2,persoon.getVoornaam());
	        stmt.setString(3, persoon.getAchternaam());
	        stmt.executeUpdate();
	        data = command.executeQuery("SELECT MAX(adressId) FROM adress");
	        if (data.next()) {
	        	adresId=data.getInt(1);
	        }
	        data.close();
	    }catch (SQLException e){
	        e.printStackTrace();
	    }catch(Exception e) {
	        e.printStackTrace();
	    }   
	    return adresId;
	}
	public static boolean persoonWijzigen(Persoon persoon) throws Exception{
		
		return false;
	}
	public static Persoon zoekPersoonOpPersoonId(int persoonId) {
		return null;
	}
	public static Persoon zoekPersoonOpPersoonId(Persoon medewerker) { 
		return null;
	}
}

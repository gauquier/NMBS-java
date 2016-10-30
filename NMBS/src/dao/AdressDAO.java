package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import source.Adres;

public class AdressDAO {
	
	private static java.sql.Connection connection;
	private static Statement command;
	private static ResultSet data;
	private static PreparedStatement stmt = null;
	private static Adres adress;
	
	public static int addAdress(Adres adres){
		int id = 0;
		DataSource ds = null;
		
		try {
	        if (connection == null){connection = Connection.getDBConnection();}
	        command = connection.createStatement();
	        
	        stmt = connection.prepareStatement("INSERT INTO Adres (straat, huisnr, woonplaats, postcode, bus) VALUES(?,?,?,?,?);");
	        stmt.setString(1, adres.getStraat());
	        stmt.setInt(2,adres.getHuisNr());
	        stmt.setString(3,adres.getWoonplaats());
	        stmt.setInt(4, adres.getPostcode() );
	        stmt.setInt(5, adres.getBus());
	        stmt.executeUpdate();
	        data = command.executeQuery("SELECT MAX(adresId) FROM Adres");
	        if (data.next()) {
	        	id=data.getInt(1);
	        }
	        data.close();
	    }catch (SQLException e){
	        e.printStackTrace();
	    }catch(Exception e) {
	        e.printStackTrace();
	    }   
	    return id;
	}
}

package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import source.Aankoop;

public class AankoopDAO {
	
	private static java.sql.Connection connection;
	private static Statement command;
	private static ResultSet data;
	private static PreparedStatement stmt = null;
	private static Aankoop aankoop;
	
	public static int addAankoop(Aankoop aankoop){
		int id = 0;
		DataSource ds = null;
		
		try {
	        if (connection == null){connection = Connection.getDBConnection();}
	        command = connection.createStatement();
	        String teststring = "Test";
	        stmt = connection.prepareStatement("INSERT INTO Aankoop (korting, prijs, verkoop) VALUES(?,?,?);");
	        stmt.setDouble(1, aankoop.getKorting());
	        stmt.setDouble(2,aankoop.getPrijs());
	        stmt.setString(3,teststring);
	        //stmt.setString(3,aankoop.getVerkoop().name());
	        stmt.executeUpdate();
	        data = command.executeQuery("SELECT MAX(aankoopId) FROM Aankoop");
	        if (data.next()) {
	        	id=data.getInt(1);
	        }
	        data.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    return id;
	}
}
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import source.*;

public class GebruikerBeheerDAO {

	// gebruiker bewerken sql
	private static final String ADRES_WIJZIGEN = "UPDATE Adres SET straat = ?, woonplaats = ?, postcode = ?, bus = ? WHERE adresId = ?";

	public static boolean adresWijzigen(Adres adres) {
		
		if(adres.getAdresId()==0 )
			return false; 
		boolean success = false;
		PreparedStatement stmt = null;
		java.sql.Connection connection = null;
		
		try {
			connection = Connection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(ADRES_WIJZIGEN);

			stmt.setString(1, adres.getStraat());
			stmt.setString(2, adres.getWoonplaats());
			stmt.setInt(3, adres.getPostcode());
			stmt.setInt(4, adres.getBus());
			stmt.setInt(5, adres.getAdresId());

			stmt.executeUpdate(); 
			connection.commit(); 
			success=true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return success;
	}
}

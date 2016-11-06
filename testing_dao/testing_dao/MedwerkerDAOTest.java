package testing_dao;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import dao.Connection;
import dao.MedewerkerDAO;
import source.Medewerker;

public class MedwerkerDAOTest {
	
	private final String LOGIN_VERWIJDEREN_OP_USERNAME="";
	private final String MEDEWERKER_VERWIJDEREN_OP_VOOR_EN_ACHTERNAAM="";
	private Medewerker medewerker=null;
	@Before
	public void Initialization(){
		 
		PreparedStatement stmt = null;
		java.sql.Connection connection = null;
		ResultSet resultSet = null;
		 //MedewerkerDAO.me
			connection = Connection.getDBConnection();
			try {
				connection.setAutoCommit(false);
			
			stmt = connection.prepareStatement(MEDEWERKER_VERWIJDEREN_OP_VOOR_EN_ACHTERNAAM);
			stmt.setString(1, medewerker.getVoornaam()); 
			stmt.setString(1, medewerker.getAchternaam()); 
			stmt.executeUpdate(); 

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
		
	}
	
	@Test
	public void medewerkerToevoegenTest() { 
	}
	@Test
	public void medewerkerWijzigenTest() { 
	}
	@Test
	public void medewerkerZoekenOpMedewerkerIdTest() { 
	}
	 

}

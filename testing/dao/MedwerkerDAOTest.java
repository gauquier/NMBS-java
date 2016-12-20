package dao;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import dao.Connection;
import dao.MedewerkerDAO;
import source.Adres;
import source.Login;
import source.Medewerker;
import source.Rol;

public class MedwerkerDAOTest {

//	public void testPersoonDieGeenMedewerkerIs() {
//	}
//
//	public void testOnbestaandeRol() {
//	}
//
//	public void testGetLoginId() {
//	}
//
//	public void testGetLoginIDMetOnbestaandeLogin() {
//	}
//
//	public void testVerkeerdWW() {
//	}
//
//	public void testGetLogin() {
//	}
//
//	public void testGetLoginMetOnbestaandeId() {
//	}
//
//	public void testGetLoginMetUserDieMetVerkeerdLoginInfoIsGekopeld() {
//	}
//
//	public void testCheckUsername() {
//	}
//
//	public void testCheckUsernameMetOnbestaandeUsername() {
//	}
//
//	public void testGetRoll() {
//	}
//
//	public void testGetRollMetOnbestaandeRoll() {
//	}
//
//	public void testGetLoginIdMetUsername() {
//	}
//
//	public void testGetLoginIdMetOnbestaandeUsername() {
//	}
//
//	public void testGetWachtwoord() {
//	}
//
//	public void testGetWachtwoordMetOnbestaandeUsername() {
//	}
//
//	public void testUpdadeWachtwoord() {
//	}
//
//	public void testUpdateWachtwoordWhere() {
//	}
//
//	public void testGetActief() {
//	}
//
//	public void testGetActiefMetOnbestaandeid() {
//	}
//
//	public void loginToevoegen() {
//	}
//
//	public void medewerkerToevoegen() {
//	}
//
//	public void persoonToevoegen() {
//	}
//
//	public Rol rolToevoegen(Rol rol) {
//
//		return null;
//	}
//
//	public Adres adresToevoegen(Adres adres) {
//		String AdresToevoegenQuery = "INSERT INTO Adres (straat, huisnr, woonplaats, postcode, bus) VALUES(?,?,?,?,?)";
//		String AdresZoekenQuery = "SELECT adresId FROM Adres WHERE straat = ?";
//		executeQuery(AdresToevoegenQuery, true, adres.getStraat(), adres.getHuisnr(), adres.getWoonplaats(),
//				adres.getPostcode(), adres.getBus());
//		Map<Integer, Object[]> map = executeQuery(AdresZoekenQuery, false, adres.getStraat());
//		adres.setAdresId((int) map.get(0)[0]);
//		return adres;
//	}
//
//	public Rol rolOphalen(String rol) {
//		return null;
//	}
//
//	public Rol rolToevoegen(String rolNaam) {
//
//		return null;
//	}
//
//	private Map<Integer, Object[]> executeQuery(String query, boolean update, Object... kolomWaarden) {
//		Map<Integer, Object[]> map = new HashMap<Integer, Object[]>();
//		java.sql.Connection connection = null;
//		PreparedStatement stmt = null;
//		ResultSet resultSet = null;
//		try {
//			connection = Connection.getDBConnection();
//			connection.setAutoCommit(false);
//			stmt = connection.prepareStatement(query);
//			for (int i = 0; i < kolomWaarden.length; i++) {
//				stmt.setObject(i + 1, kolomWaarden[i]);
//			}
//			if (update) {
//				stmt.executeUpdate();
//				connection.commit();
//			} else {
//				resultSet = stmt.executeQuery();
//				ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
//				int columnCount = resultSetMetaData.getColumnCount();
//				resultSet.last();
//				int size = resultSet.getRow();
//				resultSet.beforeFirst();
//				int counter = 0;
//
//				if (size != 0) {
//					while (resultSet.next()) {
//						Object[] kolommen = new Object[columnCount];
//						for (int i = 1; i <= columnCount; i++) {
//							kolommen[i - 1] = resultSet.getObject(i);
//						}
//						map.put(counter, kolommen);
//						counter++;
//					}
//					;
//
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (resultSet != null)
//					resultSet.close();
//				if (stmt != null)
//					stmt.close();
//				if (connection != null)
//					connection.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return map;
//	}
//	private Login loginOphalen(Login login) {
//		String loginOphalen = "SELECT loginId FROM Login WHERE username LIKE '%LoginDAOTest%'";
//		Map<Integer, Object[]> map = executeQuery(loginOphalen, false);
//		login.setLoginId((int) map.get(0)[0]);
//		return login;
//	}
//	
//	private final String LOGIN_VERWIJDEREN_OP_USERNAME="";
//	private final String MEDEWERKER_VERWIJDEREN_OP_VOOR_EN_ACHTERNAAM="";
//	private Medewerker medewerker=null;
//	@Before
//	public void Initialization(){
//		 
//		PreparedStatement stmt = null;
//		java.sql.Connection connection = null;
//		ResultSet resultSet = null;
//		 //MedewerkerDAO.me
//			connection = Connection.getDBConnection();
//			try {
//				connection.setAutoCommit(false);
//			
//			stmt = connection.prepareStatement(MEDEWERKER_VERWIJDEREN_OP_VOOR_EN_ACHTERNAAM);
//			stmt.setString(1, medewerker.getVoornaam()); 
//			stmt.setString(1, medewerker.getAchternaam()); 
//			stmt.executeUpdate(); 
//
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					if (stmt != null)
//						stmt.close();
//					if (connection != null)
//						connection.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		
//	}
//	
//	@Test
//	public void medewerkerToevoegenTest() { 
//	}
//	@Test
//	public void medewerkerWijzigenTest() { 
//	}
//	@Test
//	public void medewerkerZoekenOpMedewerkerIdTest() { 
//	}
//	 

}

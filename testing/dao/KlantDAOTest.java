package dao;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import source.Adres;
import source.Klant;
import source.Persoon;

public class KlantDAOTest {
	private Adres adres;
	private Persoon persoon;
	private String info;
	@Before
	public void initialize() {
		adres = new Adres("teststraatnaamKlantDAOTest", 170, "testwoonplaatsKlantDAOTest", 1070, "6");
		persoon = new Persoon("voornaamKlantDAOTest", "achternaamKlantDAOTest", "emailKlantDAOTest", adres);
		info = "infoKlantDAOTest";
	}

	@After
	public void terminate() {
		java.sql.Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = Connection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement("DELETE FROM Klant WHERE info LIKE ?");
			stmt.setString(1, "%KlantDAOTest%");
			stmt.executeUpdate();
			connection.commit();
			stmt.close();
			stmt = connection.prepareStatement("DELETE FROM Persoon WHERE voornaam LIKE ?");
			stmt.setString(1, "%KlantDAOTest%");
			stmt.executeUpdate();
			connection.commit();
			stmt.close();
			stmt = connection.prepareStatement("DELETE FROM Adres WHERE straat LIKE ?");
			stmt.setString(1, "%KlantDAOTest%");
			stmt.executeUpdate();
			connection.commit();

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
	public void testAddKlant() {
		KlantDAO.addKlant(persoon, adres, info);
		assertEquals(info, getKlant(persoon, adres, info));
	}

	@Test
	public void testGetAlleKlanten() {
		assertEquals(getAlleKlanten(), KlantDAO.getAllKlanten().size());
	}

	@Test
	public void testRemoveKlant() {
		Klant nieuwKlant= addKlant();
		KlantDAO.removeKlant(nieuwKlant.getKlantId());
		assertEquals("", getKlant(persoon, adres, info));
	}
	@Test
	public void testBijwerkenKlant() {
		fail("");
	 	}
	private Klant addKlant() {
		Klant nieuweKant;
		String AdresToevoegenQuery="INSERT INTO Adres "
				+ "(straat, huisnr, woonplaats, postcode, bus) "
				+ "VALUES(?,?,?,?,?)";
		executeQuery(AdresToevoegenQuery, true,
				adres.getStraat(),
				adres.getHuisnr(),
				adres.getWoonplaats(),
				adres.getPostcode(),
				adres.getBus()
				); 
		String AdresZoekenQuery = "SELECT adresId "
				+ "FROM Adres "
				+ "WHERE "
				+ "straat = ? AND huisnr = ? AND woonplaats = ? AND postcode = ? AND bus = ?";
		
		Map<Integer, Object[]> map=executeQuery(AdresZoekenQuery, false,
				adres.getStraat(),
				adres.getHuisnr(),
				adres.getWoonplaats(),
				adres.getPostcode(),
				adres.getBus()
				);
		Object []o=map.get(0);
		System.out.println(o[0].getClass().getSimpleName());
		int id=(int)o[0];
		persoon.getAdres().setAdresId(id);
		String persoonToevoegenQuery = "INSERT INTO Persoon "
				+ "(adresId, voornaam, achternaam, email) "
				+ "VALUES(?,?,?,?)";
		executeQuery(persoonToevoegenQuery, true,
				persoon.getAdres().getAdresId(),
				persoon.getVoornaam(),
				persoon.getAchternaam(),
				persoon.getEmail() 
				); 
		String persoonZoekenQuery = "SELECT persoonId "
				+ "FROM Persoon "
				+ "WHERE "
				+ "adresId = ? AND voornaam = ? AND achternaam = ? AND email = ?";
		map=executeQuery(persoonZoekenQuery, false,
				persoon.getAdres().getAdresId(),
				persoon.getVoornaam(),
				persoon.getAchternaam(),
				persoon.getEmail() 
				);
		persoon.setId((int)map.get(0)[0]);
		String klantToevoegenQuery = "INSERT INTO Klant "
				+ "(persoonId, info, actief) "
				+ "VALUES(?,?,?)";
		executeQuery(klantToevoegenQuery, true,
				persoon.getId(),
				info,
				0
				); 
		String klantZoekenQuery = "SELECT klantId "
				+ "FROM Klant "
				+ "WHERE "
				+ "persoonId = ? AND info = ? AND actief = ?";
		map=executeQuery(klantZoekenQuery, false,
				persoon.getId(),
				info,
				0
				); 
		nieuweKant=new Klant(persoon.getId(), 
				persoon.getVoornaam(), 
				persoon.getAchternaam(), 
				persoon.getEmail(), 
				adres, 
				info, 
				false) ;
		nieuweKant.setKlantId((int)map.get(0)[0]);
		nieuweKant.setInfo(info);
		nieuweKant.setActief(false);
		return nieuweKant;
	}

	private Map<Integer, Object[]> executeQuery(String query, boolean update, Object... kolomWaarden) {
		Map<Integer, Object[]> map = new HashMap<Integer, Object[]>();
		java.sql.Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		try {
			connection = Connection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(query);
			for (int i = 0; i < kolomWaarden.length; i++) {
				stmt.setObject(i+1, kolomWaarden[i]); 
			}
			if (update) {
				stmt.executeUpdate();
				connection.commit();
			} else { 
				resultSet = stmt.executeQuery(); 
				ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
				int columnCount=resultSetMetaData.getColumnCount();
				resultSet.last();
				int size = resultSet.getRow();
				resultSet.beforeFirst();
				int counter=0;
				 
				if (size!=0) {
					while(resultSet.next()){
						Object []kolommen= new Object[columnCount];
						for (int i = 1; i <= columnCount; i++) {
							kolommen[i-1]=resultSet.getObject(i);
						} 
						 map.put(counter, kolommen);
						counter++;
					}
;
					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	private int getAlleKlanten() {
		int aantal = 0;
		java.sql.Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		try {
			connection = Connection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement("SELECT COUNT(*) FROM Klant");

			resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				aantal = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return aantal;

	}

	private String getKlant(Persoon persoon, Adres adres, String info) {
		String infoDB = "";
		java.sql.Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		try {
			connection = Connection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement("SELECT info FROM Klant WHERE info LIKE '%KlantDAOTest%'");
			resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				infoDB = resultSet.getString(1); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return infoDB;

	}

}

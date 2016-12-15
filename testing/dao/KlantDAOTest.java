package dao;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import source.Adres;
import source.Klant;
import source.Persoon;
import source.VerkoopType;

public class KlantDAOTest {
	private Adres adres;
	private Persoon persoon;
	private String info;
	private Klant klant;

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
		ResultSet resultSet = null;
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

	}

	private void addKlant() {

	}

	private Map<Integer, Object> executeQuery(String query, boolean update, Object... kolomWaarden) {
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		java.sql.Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		try {
			connection = Connection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(query);
			if (update) {

			} else {

				resultSet = stmt.executeQuery();

				if (resultSet.next()) {
					int count = resultSet.getFetchSize();

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

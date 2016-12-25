package dao;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test; 

import dao.Connection;

import static dao.AdresDAO.*;
import source.Adres;

public class AdresDAOTest {
	private final String ADRES_VERWIJDEREN = "DELETE FROM Adres WHERE straat LIKE ?";
	private int nietBestaandeId;
	Adres testAdres;
	@Before
	public void initialize() {
		testAdres = new Adres("teststraatnaam", 170, "testwoonplaats", 1070, "6");
		nietBestaandeId=999999;
	}
	@After
	public void terminate() {
		java.sql.Connection connection = null; 
		PreparedStatement stmt = null; 
		try {
			connection = Connection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(ADRES_VERWIJDEREN);
			stmt.setString(1, "%" + testAdres.getStraat() + "%");
			stmt.executeUpdate(); 
			connection.commit();
			stmt.close();
			connection.close(); 
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
	public void testAdresToevoegen() {
		assertNotEquals(0, insertAdres(testAdres));
	}

	@Test(expected = Exception.class)
	public void testBestaandeAdresToevoegen() {
		insertAdres(testAdres);
		insertAdres(testAdres);
	}

	@Test
	public void testGetAdres(){ 
		assertEquals(testAdres, getAdres(insertAdres(testAdres))); 
	}
	@Test
	public void testGetAdresMetNietBestaandeAdresId() { 
		assertNull(getAdres(nietBestaandeId)); 
	}

	@Test
	public void testGetId() {
		int id =insertAdres(testAdres);
		testAdres.setAdresId(id);
		assertEquals(id, getId(testAdres));
	} 
	public void testGetIdMetNietBestaandeId() {
		 testAdres.setAdresId(nietBestaandeId);
		assertEquals(0,getId(testAdres));
	}
	

}

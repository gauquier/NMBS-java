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

import dao.LoginDao;
import source.Adres;
import source.Login;
import source.Medewerker;
import source.Persoon;
import source.Rol;

public class LoginDAOTest { 
	private Adres adres;
	private Rol rol;
	private Medewerker medewerker;
	private Login login;

	@Before
	public void initialize() { 
		login = new Login("testusernameLoginDAOTest", "testpasswordLoginDAOTest"); 
	}

	@After
	public void terminate() {
		String loginVerwijderen = "DELETE FROM Login WHERE username LIKE '%LoginDAOTest%'";
		executeQuery(loginVerwijderen, true);

	}

	@Test
	public void testOnbestaandeLoginToevoegen() {
		int id = LoginDao.addLogin(login);
		Login loginVanUitDB = loginOphalen(login);
		assertEquals(login, loginVanUitDB);
		assertEquals(loginVanUitDB.getLoginId(), id);
	}

	@Test(expected = Exception.class)
	public void testBestaandeLoginToevoegen() {
		LoginDao.addLogin(login);
		LoginDao.addLogin(login);
	}

	@Test
	public void testGetLoginId() {
		Login loginVanUitDB = loginToevoegen(login);
		int id = LoginDao.getLoginId(login);
		assertEquals(loginVanUitDB.getLoginId(), id);
	}

	@Test
	public void testGetLoginIdMetOnbestaandeLogin() {
		assertEquals(0, LoginDao.getLoginId(login));
	}

	// @Test
	// public void testVerkeerdWW() {
	// er bestaat zo een methode nietè
	// loginToevoegen(login);
	// login.setPassword("verkeerdWW");
	//
	// }
	@Test
	public void testGetLogin() {
		Login loginVanUitDB = loginToevoegen(login);
		assertEquals(loginVanUitDB, LoginDao.getLogin(loginVanUitDB.getLoginId()));
	}

	@Test
	public void testGetLoginMetOnbestaandeId() {
		int onBestaandeLoginId = 91119;
		assertNull(LoginDao.getLogin(onBestaandeLoginId));
	}

	@Test
	public void testCheckUsername() {
		Login loginVanUitDB = loginToevoegen(login);
		LoginDao loginDao = new LoginDao();
		assertEquals(loginVanUitDB.getLoginId(), loginDao.checkUsername(login.getUsername()));
	}

	@Test
	public void testCheckUsernameMetOnbestaandeUsername() {
		LoginDao loginDao = new LoginDao();
		assertEquals(-1, loginDao.checkUsername(login.getUsername()));
	}

	// ik weet niet wat de bedoeling is van dit methode
	// public void testGetRoll() {
	// }
	//
	// public void testGetRollMetOnbestaandeRoll() {
	// }
	@Test
	public void testGetLoginIdMetUsername() {
		Login loginVanUitDB = loginToevoegen(login);
		assertEquals(loginVanUitDB.getLoginId(), LoginDao.getLoginId(login.getUsername()));
		;
	}

	@Test
	public void testGetLoginIdMetOnbestaandeUsername() {
		assertEquals(0, LoginDao.getLoginId(login.getUsername()));
	}

	@Test
	public void testGetWachtwoord() {
		loginToevoegen(login);
		assertEquals(login.getPassword(), LoginDao.getWachtwoord(login.getUsername()));
		;
	}

	@Test
	public void testGetWachtwoordMetOnbestaandeUsername() {
		assertEquals(login.getUsername(), LoginDao.getWachtwoord(login.getUsername()));
	}

	@SuppressWarnings("static-access")
	@Test
	public void testUpdadeWachtwoord() {
		loginToevoegen(login);
		// wie de login en logindao heeft gemaakt heeft ervoor gezorgd
		// de volgende stapen moet door lopen om dit te testen
		Login loginTmp = new Login();
		loginTmp.setCurrentUser(login.getUsername());
		login.setPassword("testUpdadeWachtwoordLoginDAOTest");
		LoginDao.updateWachtwoord(login.getPassword());
		assertEquals(login.getPassword(), loginOphalen(login).getPassword());
	}

	@Test(expected = Exception.class)
	public void testUpdateWachtwoordVanOnbestaandeUsername() {
		LoginDao.updateWachtwoord(login.getPassword());
	}

	@Test
	public void testUpdateWachtwoordWhere() {
		Login loginVanUitDB = loginToevoegen(login);
		String ww = "testUpdateWachtwoordLoginDAOTest";
		LoginDao.updateWachtwoordWhere(loginVanUitDB.getLoginId(), ww);
		loginVanUitDB = loginOphalen(login);
		assertEquals(ww, loginVanUitDB.getPassword());
		;
	}

	@Test(expected = Exception.class)
	public void testUpdateWachtwoordWhereMetOnbestaandeId() {
		String ww = "testUpdadeWachtwoordLoginDAOTest";
		LoginDao.updateWachtwoordWhere(91119, ww);
	}

	@Test
	public void testGetActief() {
		adres = adresToevoegen(new Adres("teststraatnaamLoginDAOTest", 170, "testwoonplaatsLoginDAOTest", 1070, "6"));
		rol = rolToevoegen(new Rol(0, "testrolLoginDAOTest"));
		Persoon persoon=new Persoon(
				"testvoornaamLoginDAOTest", "testachternaamLoginDAOTest",
				"testemailLoginDAOTest", adres);
		medewerker=new Medewerker("testvoornaamLoginDAOTest", "testachternaamLoginDAOTest");
		medewerker.setEmail(persoon.getEmail());
		medewerker.setAdres(persoon.getAdres());
		medewerker.setRol(rol);
		medewerker.setLogin(loginToevoegen(login));
		medewerker.setActief(true);
		medewerker=medewerkerOpslaanInDB(medewerker); 
		assertEquals(1,LoginDao.getActief(medewerker.getLogin().getLoginId()));
		medewerkerVerwijderen(medewerker);
	}
	@Test
	public void testGetNietActiefLogin() {
		adres = adresToevoegen(new Adres("teststraatnaamLoginDAOTest", 170, "testwoonplaatsLoginDAOTest", 1070, "6"));
		rol = rolToevoegen(new Rol(0, "testrolLoginDAOTest")); 
		Persoon persoon=new Persoon(
				"testvoornaamLoginDAOTest", "testachternaamLoginDAOTest",
				"testemailLoginDAOTest", adres);
		medewerker=new Medewerker("testvoornaamLoginDAOTest", "testachternaamLoginDAOTest");
		medewerker.setEmail(persoon.getEmail());
		medewerker.setAdres(persoon.getAdres());
		medewerker.setRol(rol);
		medewerker.setLogin(loginToevoegen(login));
		medewerker.setActief(false);
		medewerker=medewerkerOpslaanInDB(medewerker); 
		assertEquals(0,LoginDao.getActief(medewerker.getLogin().getLoginId())); 
		medewerkerVerwijderen(medewerker);
		
	}
	@Test
	public void testGetActiefMetOnbestaandeId() {
		int onbestaandeId=91119;
		assertEquals(onbestaandeId,LoginDao.getActief(onbestaandeId));  
	}
	private void medewerkerVerwijderen(Medewerker medewerker){
		String verwijderMedewerker="DELETE FROM Medewerker WHERE persoonId= ?";
		String verwijderRol="DELETE FROM Rol WHERE rol LIKE '%LoginDAOTest%'";
		String verwijderPersoon="DELETE FROM Persoon WHERE voornaam LIKE '%LoginDAOTest%'";
		String verwijderAdres="DELETE FROM Adres WHERE straat LIKE '%LoginDAOTest%'";
		executeQuery(verwijderMedewerker, true, medewerker.getId());
		executeQuery(verwijderRol, true);
		executeQuery(verwijderPersoon, true);
		executeQuery(verwijderAdres, true); 
	}
	private Medewerker medewerkerOpslaanInDB(Medewerker medewerker) {
		String persoonToevoegenQuery = "INSERT INTO Persoon "
				+ "(adresId, voornaam, achternaam, email) "
				+ "VALUES(?,?,?,?)";
		String persoonZoekenQuery = "SELECT persoonId "
				+ "FROM Persoon "
				+ "WHERE voornaam = ?";

		String medewerkerToevoegenQuery = "INSERT INTO Medewerker "
				+ "(loginId, persoonId, rolId,Actief) "
				+ "VALUES(?,?,?,?)";
		String medewerkerZoekenQuery = "SELECT medewerkerId FROM Medewerker "
				+ "WHERE persoonId = ?"; 
		executeQuery(persoonToevoegenQuery, true,
				medewerker.getAdres().getAdresId(),
				medewerker.getVoornaam(),
				medewerker.getAchternaam(),
				medewerker.getEmail()
				);
		Map<Integer, Object[]>map=executeQuery(persoonZoekenQuery, false, 
				medewerker.getVoornaam()
				);
		medewerker.setId((int)map.get(0)[0]);
		executeQuery(medewerkerToevoegenQuery, true,
				medewerker.getLogin().getLoginId(),
				medewerker.getId(),
				medewerker.getRol().getRolId(),
				medewerker.isActief()?1:0
				);
		map=executeQuery(medewerkerZoekenQuery, false, 
				medewerker.getId()
				);
		medewerker.setMedewerkerId((int)map.get(0)[0]); 
		return medewerker;
	}
	public Login loginToevoegen(Login login) {
		String loginToevoen = "INSERT INTO Login (username, pass) " + "VALUES(?,?)";
		executeQuery(loginToevoen, true, login.getUsername(), login.getPassword());
		return loginOphalen(login);
	} 
	public Rol rolToevoegen(Rol rol) {
		String rolToevoegenQuery = "INSERT INTO Rol "
				+ "(rol) "
				+ "VALUES(?)";
		String rolZoekenQuery = "SELECT rolId FROM Rol "
				+ "WHERE rol = ?"; 
		executeQuery(rolToevoegenQuery, true,
				rol.getRol() 
				);
		Map<Integer, Object[]>map=executeQuery(rolZoekenQuery, false, 
				rol.getRol()
				);
		rol.setRolId((int)map.get(0)[0]);  
		return rol;
	}

	public Adres adresToevoegen(Adres adres) {
		String AdresToevoegenQuery = "INSERT INTO Adres (straat, huisnr, woonplaats, postcode, bus) VALUES(?,?,?,?,?)";
		String AdresZoekenQuery = "SELECT adresId FROM Adres WHERE straat = ?";
		executeQuery(AdresToevoegenQuery, true, adres.getStraat(), adres.getHuisnr(), adres.getWoonplaats(),
				adres.getPostcode(), adres.getBus());
		Map<Integer, Object[]> map = executeQuery(AdresZoekenQuery, false, adres.getStraat());
		adres.setAdresId((int) map.get(0)[0]);
		return adres;
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
				stmt.setObject(i + 1, kolomWaarden[i]);
			}
			if (update) {
				stmt.executeUpdate();
				connection.commit();
			} else {
				resultSet = stmt.executeQuery();
				ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
				int columnCount = resultSetMetaData.getColumnCount();
				resultSet.last();
				int size = resultSet.getRow();
				resultSet.beforeFirst();
				int counter = 0;

				if (size != 0) {
					while (resultSet.next()) {
						Object[] kolommen = new Object[columnCount];
						for (int i = 1; i <= columnCount; i++) {
							kolommen[i - 1] = resultSet.getObject(i);
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

	private Login loginOphalen(Login login) {
		String loginOphalen = "SELECT loginId FROM Login WHERE username LIKE '%LoginDAOTest%'";
		Map<Integer, Object[]> map = executeQuery(loginOphalen, false);
		login.setLoginId((int) map.get(0)[0]);
		return login;
	} 
}

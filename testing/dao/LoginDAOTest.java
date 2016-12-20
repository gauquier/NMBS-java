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

	private Persoon persoon;

	private String username, password, fakeUsername, fakePassword;
	private int userId, fakeUserID, rollId, fakeRollId;
	private Adres adres;
	private Rol rol;
	private Medewerker medewerker;
	private Login login;

	@Before
	public void initialize() {
		// adres = adresToevoegen(new Adres("teststraatnaamLoginDAOTest", 170,
		// "testwoonplaatsLoginDAOTest", 1070, "6"));
		// rol= rolToevoegen(new Rol(0, "testrolLoginDAOTest"));
		// this is not needed to test login
		// but useful for medewerkerdao test
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
		int onBestaandeLoginId=91119;
		assertNull(LoginDao.getLogin(onBestaandeLoginId)); 
	} 
	@Test
	public void testCheckUsername() {
		Login loginVanUitDB = loginToevoegen(login);
		LoginDao loginDao= new LoginDao();
		assertEquals(loginVanUitDB.getLoginId(), loginDao.checkUsername(login.getUsername())); 
	}
	@Test
	public void testCheckUsernameMetOnbestaandeUsername() {
		LoginDao loginDao= new LoginDao();
		assertEquals(-1, loginDao.checkUsername(login.getUsername())); 
	}
//	ik weet niet wat de bedoeling is van dit methode
//	public void testGetRoll() {
//	}
//
//	public void testGetRollMetOnbestaandeRoll() {
//	}
	@Test
	public void testGetLoginIdMetUsername() {
		Login loginVanUitDB = loginToevoegen(login);
		assertEquals(loginVanUitDB.getLoginId(), LoginDao.getLoginId(login.getUsername())); ;
	}
	@Test
	public void testGetLoginIdMetOnbestaandeUsername() {
		assertEquals(0, LoginDao.getLoginId(login.getUsername())); 
	}

	public void testGetWachtwoord() {
	}

	public void testGetWachtwoordMetOnbestaandeUsername() {
	}

	public void testUpdadeWachtwoord() {
	}

	public void testUpdateWachtwoordWhere() {
	}

	public void testGetActief() {
	}

	public void testGetActiefMetOnbestaandeid() {
	}

	public Login loginToevoegen(Login login) {
		String loginToevoen = "INSERT INTO Login (username, pass) " + "VALUES(?,?)";
		executeQuery(loginToevoen, true, login.getUsername(), login.getPassword());
		return loginOphalen(login);
	}

	public void medewerkerToevoegen() {
	}

	public void persoonToevoegen() {
	}

	public Rol rolToevoegen(Rol rol) {

		return null;
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

	public Rol rolOphalen(String rol) {
		return null;
	}

	public Rol rolToevoegen(String rolNaam) {

		return null;
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
	// public void testGetUserName(){}
	// login is totaal anders in klassendiagram
	// en deze testen zijn niet meer van toepasing
	// -Aime

	// @SuppressWarnings("unused")
	// private int userId, fakeUserID, rollId, fakeRollId;
	// private String username, password, fakeUsername, fakePassword;
	//
	// @Before
	// public void Init() {
	// // de volgende is de setup van/voor 1 user, namelijk de test user die al
	// // bestaan in DB
	// userId = 1;
	// fakeUserID = 999;
	// rollId = 1;
	// fakeRollId = 999;
	//
	// username = "test";
	// password = "test";
	// fakeUsername = "fakeusername";
	// fakePassword = "fakepassword";
	//
	// }
	// // ----------------------------------- getUsername Tests
	//
	// @Test
	// public void getUsernameUsingExistingUserId() {
	// // fail test
	// // assertEquals(username, LoginDao.getUsername(fakeUserID));
	// // success test
	// assertEquals(username, LoginDao.getUsername(userId));
	// }
	//
	// @Test
	// public void getUsernameUsingNonExistentUserId() {
	// // fail test
	// // assertEquals(username, LoginDao.getUsername(userId));
	// // success test
	// assertEquals(null, LoginDao.getUsername(fakeUserID));
	// }
	//
	// @Test
	// public void getUsernameUsingExistingUsername() {
	// // fail test
	// // assertEquals(username, LoginDao.getUserName(fakeusername));
	// // success test
	// assertEquals(username, LoginDao.getUserName(username));
	// }
	//
	// @Test
	// public void getUsernameUsingNonExistentUsername() {
	// // fail test
	// // assertEquals(null, LoginDao.getUserName(username));
	// // success test
	// assertEquals(null, LoginDao.getUserName(fakeUsername));
	// }
	//
	// // ----------------------------------- getPassword Tests
	//
	// @Test
	// public void getPasswordUsingExistingUserId() {
	// // fail test
	// // assertEquals(password, LoginDao.getPassword(fakeUserID));
	// // success test
	// assertEquals(password, LoginDao.getPassword(userId));
	// }
	//
	// @Test
	// public void getPasswordUsingNonExistentUserId() {
	// // fail test
	// // assertEquals(null, LoginDao.getPassword(userId));
	// // success test
	// assertEquals(null, LoginDao.getPassword(fakeUserID));
	// }
	//
	// // ----------------------------------- getRoll Tests
	// @Test
	// public void getRollUsingExistingUsername() {
	// // fail test
	// // assertEquals(rollId, LoginDao.getRoll(fakeUsername));
	// // success test
	// assertEquals(rollId, LoginDao.getRoll(username));
	// }
	//
	// @Test
	// public void getRollUsingNonExistentUsername() {
	// // fail test
	// // assertEquals(null, LoginDao.getRoll(username));
	// // success test
	// assertEquals(0, LoginDao.getRoll(fakeUsername));
	// }
	//
	// // ----------------------------------- getUserId Tests
	// @Test
	// public void getUserIdUsingExistingUsernameAndCorrectPassword() {
	// // fail tests
	// // assertEquals(userId, LoginDao.userId(fakeUsername, fakePassword));
	// // assertEquals(userId, LoginDao.userId(username, fakePassword));
	// // assertEquals(userId, LoginDao.userId(fakeUsername, password));
	// // success test
	// assertEquals(userId, LoginDao.userId(username, password));
	// }
	//
	// @Test
	// public void getUserIdUsingNonExistentUsernameAndWrongPassword() {
	// // fail test
	// // assertEquals(0, LoginDao.userId(username, password));
	// // success test
	// assertEquals(0, LoginDao.userId(fakeUsername, fakePassword));
	// }
	//
	// @Test
	// public void getUserIdUsingExistingUsernameAndWrongPassword() {
	// // fail test
	// // assertEquals(null, LoginDao.userId(username, password));
	// // success test
	// assertEquals(0, LoginDao.userId(username, fakePassword));
	// }
	//
	// @Test
	// public void getUserIdUsingNonExistentUsernameAndCorrectPassword() {
	// // fail test
	// // assertEquals(0, LoginDao.userId(username, password));
	// // success test
	// assertEquals(0, LoginDao.userId(fakeUsername, password));
	// }
	//
	// // ----------------------------------- getWachtwoord Tests
	// @Test
	// public void getWachtwoordUsingExistingUsername() {
	// // fail test
	// // assertEquals(password, LoginDao.getWachtwoord(fakeUsername));
	// // success test
	// assertEquals(password, LoginDao.getWachtwoord(username));
	// }
	//
	// @Test
	// public void getWachtwoordNonExistentUsername() {
	// // fail test
	// // assertEquals(null, LoginDao.getWachtwoord(username));
	// // success test
	// assertEquals(null, LoginDao.getWachtwoord(fakeUsername));
	// }

}

package dao;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.Connection;
import dao.MedewerkerDAO;
import source.Adres;
import source.Login;
import source.Medewerker;
import source.Persoon;
import source.Rol;

public class MedewerkerDAOTest {
	private Login login;
	private Persoon medewerker;
	private Rol rol;
	private Adres adres;

	@Before
	public void initialize() {
		login = new Login("testusernameMedewerkerDAOTest", "testpasswordMedewerkerDAOTest");
		adres = new Adres("teststraatnaamMedewerkerDAOTest", 170, "testwoonplaatsMedewerkerDAOTest", 1070, "6");
		medewerker = new Medewerker(0, "testvoornaamMedewerkerDAOTest", "testachternaamMedewerkerDAOTest",
				"testemailMedewerkerDAOTest", adres);
		rol = new Rol(0, "testrolMedewerkerDAOTest");

	}

	@After
	public void terminate() {
		medewerkerVerwijderen((Medewerker) persoonOphalen(medewerker));
	}

	@Test
	public void testAddMedewerkerIndividueleDataMembers() {
		MedewerkerDAO.addMedewerker(login, medewerker, rolToevoegen(rol), adres);
		assertEquals(login.getUsername(), loginOphalen(login).getUsername());
		assertEquals(rol.getRol(), rolOphalen(rol).getRol());
		assertEquals(adres.getStraat(), adresOphalen(adres).getStraat());
		assertEquals(medewerker.getVoornaam(), persoonOphalen(medewerker).getVoornaam());

	}

	@Test
	public void testAddMedewerker() {
		((Medewerker) medewerker).setLogin(login);
		((Medewerker) medewerker).setRol(rol);
		MedewerkerDAO.addMedewerker((Medewerker) medewerker);
		assertEquals(medewerker.getId(), medewerkerOphalen(medewerker).getId());
	}

	@Test(expected = Exception.class)
	public void testBestaandeMedewerkerToevoegen() {
		((Medewerker) medewerker).setLogin(login);
		((Medewerker) medewerker).setRol(rol);
		MedewerkerDAO.addMedewerker((Medewerker) medewerker);
		MedewerkerDAO.addMedewerker((Medewerker) medewerker);
	}

	@Test
	public void testGetMedewerkerId() {
		login = loginToevoegen(login);
		adres = adresToevoegen(adres);
		rol = rolToevoegen(rol);
		medewerker.setAdres(adres);
		medewerker = persoonToevoegen(medewerker);
		((Medewerker) medewerker).setLogin(login);
		((Medewerker) medewerker).setRol(rol);
		((Medewerker) medewerker).setActief(true);
		medewerker = medewerkerOpslaanInDB((Medewerker) medewerker);
		assertEquals(((Medewerker) medewerker).getMedewerkerId(),
				MedewerkerDAO.getMedewerkerId((Medewerker) medewerker));
	}

	@Test
	public void testGetMedewerkerIdMetOnbestaandeMedewerker() {
		((Medewerker) medewerker).setLogin(login);
		((Medewerker) medewerker).setRol(rol);
		((Medewerker) medewerker).setActief(true);
		assertEquals(0, MedewerkerDAO.getMedewerkerId((Medewerker) medewerker));
	}

	@Test
	public void testgetMedewerker() {
		login = loginToevoegen(login);
		adres = adresToevoegen(adres);
		rol = rolToevoegen(rol);
		medewerker.setAdres(adres);
		medewerker = persoonToevoegen(medewerker);
		((Medewerker) medewerker).setLogin(login);
		((Medewerker) medewerker).setRol(rol);
		((Medewerker) medewerker).setActief(true);
		medewerker = medewerkerOpslaanInDB((Medewerker) medewerker);
		assertEquals(((Medewerker) medewerker),
				MedewerkerDAO.getMedewerker(((Medewerker) medewerker).getMedewerkerId()));

	}

	@Test
	public void testgetMedewerkerMetOnbestaandeMedewerker() {
		medewerker.setAdres(adres);
		((Medewerker) medewerker).setLogin(login);
		((Medewerker) medewerker).setRol(rol);
		((Medewerker) medewerker).setActief(true);
		assertNull(MedewerkerDAO.getMedewerker(((Medewerker) medewerker).getMedewerkerId()));
	}

	@Test
	public void testgetMedewerkerByPersoonId() {
		login = loginToevoegen(login);
		adres = adresToevoegen(adres);
		rol = rolToevoegen(rol);
		medewerker.setAdres(adres);
		medewerker = persoonToevoegen(medewerker);
		((Medewerker) medewerker).setLogin(login);
		((Medewerker) medewerker).setRol(rol);
		((Medewerker) medewerker).setActief(true);
		medewerker = medewerkerOpslaanInDB((Medewerker) medewerker);
		assertEquals(((Medewerker) medewerker), MedewerkerDAO.getMedewerkerByPersoonId(medewerker.getId()));
	}

	@Test
	public void testgetMedewerkerByPersoonIdMetOnbestaandePersoonId() {
		int onbestaandeId = 91119;
		assertNull(MedewerkerDAO.getMedewerker(onbestaandeId));
	}

	@Test
	public void testgetMedewerkerByLogin() {
		login = loginToevoegen(login);
		adres = adresToevoegen(adres);
		rol = rolToevoegen(rol);
		medewerker.setAdres(adres);
		medewerker = persoonToevoegen(medewerker);
		((Medewerker) medewerker).setLogin(login);
		((Medewerker) medewerker).setRol(rol);
		((Medewerker) medewerker).setActief(true);
		medewerker = medewerkerOpslaanInDB((Medewerker) medewerker);
		assertEquals(((Medewerker) medewerker), MedewerkerDAO.getMedewerkerByLogin(login.getLoginId()));
	}

	@Test
	public void testgetMedewerkerByLoginMetOnbestaandeLoginId() {
		int onbestaandeLoginId = 91119;
		assertNull(MedewerkerDAO.getMedewerkerByLogin(onbestaandeLoginId));
	}

	@Test
	public void testgetAllMedewerkers() {
		assertEquals(countMedewerkers(), MedewerkerDAO.getAllMedewerkers().size());
	}

	@Test
	public void testgetAllMedewerkersFromSearch() {
		login = loginToevoegen(login);
		adres = adresToevoegen(adres);
		rol = rolToevoegen(rol);
		medewerker.setAdres(adres);
		medewerker = persoonToevoegen(medewerker);
		((Medewerker) medewerker).setLogin(login);
		((Medewerker) medewerker).setRol(rol);
		((Medewerker) medewerker).setActief(true);
		medewerker = medewerkerOpslaanInDB((Medewerker) medewerker);
		try{
			assertEquals(1, MedewerkerDAO.getAllMedewerkersFromSearch("MedewerkerDAOTest").size());
		}catch(NullPointerException e){
			fail("MedewerkerDAO.getAllMedewerkersFromSearch return NULL => NullPointerException");
		} 
	}

	@Test
	public void testremoveMedewerker() {
		login = loginToevoegen(login);
		adres = adresToevoegen(adres);
		rol = rolToevoegen(rol);
		medewerker.setAdres(adres);
		medewerker = persoonToevoegen(medewerker);
		((Medewerker) medewerker).setLogin(login);
		((Medewerker) medewerker).setRol(rol);
		((Medewerker) medewerker).setActief(true);
		medewerker = medewerkerOpslaanInDB((Medewerker) medewerker);
		MedewerkerDAO.removeMedewerker(((Medewerker) medewerker).getMedewerkerId());
		assertEquals(0, isMedewerkerActief((Medewerker) medewerker));
	}

	@Test
	public void testbijwerkenMedewerker() {
		login = loginToevoegen(login);
		adres = adresToevoegen(adres);
		rol = rolToevoegen(rol);
		medewerker.setAdres(adres);
		medewerker = persoonToevoegen(medewerker);
		((Medewerker) medewerker).setLogin(login);
		((Medewerker) medewerker).setRol(rol);
		((Medewerker) medewerker).setActief(true);
		medewerker = medewerkerOpslaanInDB((Medewerker) medewerker);

	}

	private int isMedewerkerActief(Medewerker medewerker) {
		String isMedewerkerActiefQuery = "SELECT Actief FROM Medewerker WHERE medewerkerId = ?";
		Map<Integer, Object[]> map = executeQuery(isMedewerkerActiefQuery, false, medewerker.getMedewerkerId());
		if (map.size() > 0)
			return (boolean) map.get(0)[0]?1:0;

		return 1;
	}

	private int countMedewerkers() {
		String countMedewerkersQuery = "SELECT * FROM Medewerker WHERE Actief = 1";
		Map<Integer, Object[]> map = executeQuery(countMedewerkersQuery, false);
		if (map.size() > 0)
			return map.size();
		return 0;
	}

	private Persoon medewerkerOphalen(Persoon medewerker) {
		String medewerkerZoekenQuery = "SELECT persoonId " + "FROM Medewerker " + "WHERE persoonId = ?";
		Map<Integer, Object[]> map = executeQuery(medewerkerZoekenQuery, false, medewerker.getId());
		if (map.size() > 0)
			medewerker.setId((int) map.get(0)[0]);

		return medewerker;
	}

	private Rol rolOphalen(Rol rol) {
		String rolZoekenQuery = "SELECT rolId FROM Rol " + "WHERE rol = ?";
		Map<Integer, Object[]> map = executeQuery(rolZoekenQuery, false, rol.getRol());
		if (map.size() > 0)
			rol.setRolId((int) map.get(0)[0]);

		return rol;
	}

	private Persoon persoonOphalen(Persoon medewerker) {
		String persoonZoekenQuery = "SELECT persoonId " + "FROM Persoon " + "WHERE voornaam = ?";
		Map<Integer, Object[]> map = executeQuery(persoonZoekenQuery, false, medewerker.getVoornaam());
		if (map.size() > 0)
			medewerker.setId((int) map.get(0)[0]);
		return medewerker;
	}

	private Persoon persoonToevoegen(Persoon persoon) {
		String persoonToevoegenQuery = "INSERT INTO Persoon " + "(adresId, voornaam, achternaam, email) "
				+ "VALUES(?,?,?,?)";
		executeQuery(persoonToevoegenQuery, true, persoon.getAdres().getAdresId(), persoon.getVoornaam(),
				persoon.getAchternaam(), persoon.getEmail());
		return persoonOphalen(persoon);
	}

	private void medewerkerVerwijderen(Medewerker medewerker) {
		String verwijderMedewerker = "DELETE FROM Medewerker WHERE persoonId= ?";
		String verwijderRol = "DELETE FROM Rol WHERE rol LIKE '%MedewerkerDAOTest%'";
		String verwijderPersoon = "DELETE FROM Persoon WHERE voornaam LIKE '%MedewerkerDAOTest%'";
		String verwijderAdres = "DELETE FROM Adres WHERE straat LIKE '%MedewerkerDAOTest%'";
		String verwijderLogin = "DELETE FROM Login WHERE username LIKE '%MedewerkerDAOTest%'";
		executeQuery(verwijderMedewerker, true, medewerker.getId());
		executeQuery(verwijderRol, true);
		executeQuery(verwijderPersoon, true);
		executeQuery(verwijderAdres, true);
		executeQuery(verwijderLogin, true);
	}

	private Login loginToevoegen(Login login) {
		String loginToevoen = "INSERT INTO Login (username, pass) " + "VALUES(?,?)";
		executeQuery(loginToevoen, true, login.getUsername(), login.getPassword());
		return loginOphalen(login);
	}

	private Login loginOphalen(Login login) {
		String loginOphalen = "SELECT loginId FROM Login WHERE username LIKE '%MedewerkerDAOTest%'";
		Map<Integer, Object[]> map = executeQuery(loginOphalen, false);
		login.setLoginId((int) map.get(0)[0]);
		return login;
	}

	public Rol rolToevoegen(Rol rol) {
		String rolToevoegenQuery = "INSERT INTO Rol " + "(rol) " + "VALUES(?)";

		executeQuery(rolToevoegenQuery, true, rol.getRol());
		return rolOphalen(rol);
	}

	public Adres adresOphalen(Adres adres) {
		String adresZoekenQuery = "SELECT adresId FROM Adres WHERE straat = ?";
		Map<Integer, Object[]> map = executeQuery(adresZoekenQuery, false, adres.getStraat());
		adres.setAdresId((int) map.get(0)[0]);
		return adres;
	}

	public Adres adresToevoegen(Adres adres) {
		String adresToevoegenQuery = "INSERT INTO Adres (straat, huisnr, woonplaats, postcode, bus) VALUES(?,?,?,?,?)";
		executeQuery(adresToevoegenQuery, true, adres.getStraat(), adres.getHuisnr(), adres.getWoonplaats(),
				adres.getPostcode(), adres.getBus());
		return adresOphalen(adres);
	}

	private Medewerker medewerkerOpslaanInDB(Medewerker medewerker) {

		String persoonToevoegenQuery = "INSERT INTO Persoon " + "(adresId, voornaam, achternaam, email) "
				+ "VALUES(?,?,?,?)";
		String persoonZoekenQuery = "SELECT persoonId " + "FROM Persoon " + "WHERE voornaam = ?";

		String medewerkerToevoegenQuery = "INSERT INTO Medewerker " + "(loginId, persoonId, rolId,Actief) "
				+ "VALUES(?,?,?,?)";
		String medewerkerZoekenQuery = "SELECT medewerkerId FROM Medewerker " + "WHERE persoonId = ?";
		executeQuery(persoonToevoegenQuery, true, medewerker.getAdres().getAdresId(), medewerker.getVoornaam(),
				medewerker.getAchternaam(), medewerker.getEmail());
		Map<Integer, Object[]> map = executeQuery(persoonZoekenQuery, false, medewerker.getVoornaam());
		medewerker.setId((int) map.get(0)[0]);
		executeQuery(medewerkerToevoegenQuery, true, medewerker.getLogin().getLoginId(), medewerker.getId(),
				medewerker.getRol().getRolId(), medewerker.isActief() ? 1 : 0);
		map = executeQuery(medewerkerZoekenQuery, false, medewerker.getId());
		medewerker.setMedewerkerId((int) map.get(0)[0]);
		return medewerker;
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

	// public void testPersoonDieGeenMedewerkerIs() {
	// }
	//
	// public void testOnbestaandeRol() {
	// }
	//
	// public void testGetLoginId() {
	// }
	//
	// public void testGetLoginIDMetOnbestaandeLogin() {
	// }
	//
	// public void testVerkeerdWW() {
	// }
	//
	// public void testGetLogin() {
	// }
	//
	// public void testGetLoginMetOnbestaandeId() {
	// }
	//
	// public void testGetLoginMetUserDieMetVerkeerdLoginInfoIsGekopeld() {
	// }
	//
	// public void testCheckUsername() {
	// }
	//
	// public void testCheckUsernameMetOnbestaandeUsername() {
	// }
	//
	// public void testGetRoll() {
	// }
	//
	// public void testGetRollMetOnbestaandeRoll() {
	// }
	//
	// public void testGetLoginIdMetUsername() {
	// }
	//
	// public void testGetLoginIdMetOnbestaandeUsername() {
	// }
	//
	// public void testGetWachtwoord() {
	// }
	//
	// public void testGetWachtwoordMetOnbestaandeUsername() {
	// }
	//
	// public void testUpdadeWachtwoord() {
	// }
	//
	// public void testUpdateWachtwoordWhere() {
	// }
	//
	// public void testGetActief() {
	// }
	//
	// public void testGetActiefMetOnbestaandeid() {
	// }
	//
	// public void loginToevoegen() {
	// }
	//
	// public void medewerkerToevoegen() {
	// }
	//
	// public void persoonToevoegen() {
	// }
	//
	// public Rol rolToevoegen(Rol rol) {
	//
	// return null;
	// }
	//
	// public Adres adresToevoegen(Adres adres) {
	// String AdresToevoegenQuery = "INSERT INTO Adres (straat, huisnr,
	// woonplaats, postcode, bus) VALUES(?,?,?,?,?)";
	// String AdresZoekenQuery = "SELECT adresId FROM Adres WHERE straat = ?";
	// executeQuery(AdresToevoegenQuery, true, adres.getStraat(),
	// adres.getHuisnr(), adres.getWoonplaats(),
	// adres.getPostcode(), adres.getBus());
	// Map<Integer, Object[]> map = executeQuery(AdresZoekenQuery, false,
	// adres.getStraat());
	// adres.setAdresId((int) map.get(0)[0]);
	// return adres;
	// }
	//
	// public Rol rolOphalen(String rol) {
	// return null;
	// }
	//
	// public Rol rolToevoegen(String rolNaam) {
	//
	// return null;
	// }
	//
	//
	// private Login loginOphalen(Login login) {
	// String loginOphalen = "SELECT loginId FROM Login WHERE username LIKE
	// '%LoginDAOTest%'";
	// Map<Integer, Object[]> map = executeQuery(loginOphalen, false);
	// login.setLoginId((int) map.get(0)[0]);
	// return login;
	// }
	//
	// private final String LOGIN_VERWIJDEREN_OP_USERNAME="";
	// private final String MEDEWERKER_VERWIJDEREN_OP_VOOR_EN_ACHTERNAAM="";
	// private medewerker medewerker=null;
	// @Before
	// public void Initialization(){
	//
	// PreparedStatement stmt = null;
	// java.sql.Connection connection = null;
	// ResultSet resultSet = null;
	// //MedewerkerDAO.me
	// connection = Connection.getDBConnection();
	// try {
	// connection.setAutoCommit(false);
	//
	// stmt =
	// connection.prepareStatement(MEDEWERKER_VERWIJDEREN_OP_VOOR_EN_ACHTERNAAM);
	// stmt.setString(1, medewerker.getVoornaam());
	// stmt.setString(1, medewerker.getAchternaam());
	// stmt.executeUpdate();
	//
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// try {
	// if (stmt != null)
	// stmt.close();
	// if (connection != null)
	// connection.close();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// }
	//
	// @Test
	// public void medewerkerToevoegenTest() {
	// }
	// @Test
	// public void medewerkerWijzigenTest() {
	// }
	// @Test
	// public void medewerkerZoekenOpMedewerkerIdTest() {
	// }
	//

}

package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import source.Klant;
import source.Medewerker;
import source.Persoon;

public class MedewerkerDAO {
	private static final String MEDEWERKER_ZOEKEN_OP_NAAM_EN_ACHTERNAAM = "SELECT DISTINCT Persoon.persoonId, Persoon.voornaam, Persoon.achternaam FROM Medewerker JOIN Persoon ON Medewerker.persoonId= Persoon.persoonId WHERE Persoon.voornaam = ? AND Persoon.achternaam = ? ";
	private static final String PERSOON_TOEVOEGEN = "INSERT INTO Persoon (voornaam, achternaam) VALUES ( ? , ? )";
	private static final String ROL_ZOEKEN_OP_NAAM = "SELECT rollId, Rol FROM Rol WHERE Rol = ?";
	private static final String LOGIN_ZOEKEN_OP_USERNAME = "SELECT loginId, username FROM Login WHERE username = ?";
	private static final String LOGIN_TOEVOEGEN = "INSERT INTO Login (username, pass, email) VALUES ( ? , ? , ?)";
	private static final String MEDEWERKER_TOEVOEGEN = "INSERT INTO Medewerker (loginId, persoonId, rolId, actief) VALUES ( ? , ? , ? , ? )";

	private static final String MEDEWERKER_ZOEKEN_OP_MEDEWERKERID = "SELECT medewerkerId,loginId, persoonId, rolId, actief WHERE medewerkerId = ?";

	private static final String MEDEWERKER_INFO_WIJZIGEN = "UPDATE Medewerker SET actief = ? WHERE medewerkerId = ?";
	private static final String MEDEWERKER_ZOEKEN_OP_NAAM = "!undercontruction (eerst afsprekken met team hoe dit werkt";
	private static final String MEDEWERKER_ZOEKEN_OP_PERSOONID = "!undercontruction (eerst afsprekken met team hoe dit werkt";

	/**
	 * Medewerker Toevoegen Als Medewerker al bestaat in databank return null
	 * return Medewerker die een Medewerker id heeft als Medewerker toevoegen
	 * met success is volbracht
	 * 
	 * @param medewerker
	 *            - nieuw medewerker die moet toegevoegd worden
	 * @return een <b>medewerker</b> object die een id heeft van zowel
	 *         Persoon.persoonId en Medewerker.medewerkerId en alle info die
	 *         bijhoren <br>
	 *         <br>
	 *         <b>Werking: </b><br>
	 *         Medewerker wordt niet toegevoegd tenzij alle stapen in order zijn
	 * 
	 *         <ol>
	 *         <li><b>stap 1</b><br>
	 *         checken of login geldig is zo niet falt deze methode niets word
	 *         toegevoegd aan DB waarom? omdat volgens onze DB Login required is
	 *         bij toevoegen van medewerker</li>
	 *         <li><b>stap 2</b><br>
	 *         checken of Rol van medewerker bestaat. zo niet dan wordt falt
	 *         deze methode en null wordt return</li>
	 *         <li><b>stap 3</b><br>
	 *         checken of de medewerker all bestaat. wordt gecontroleerd op
	 *         voornaam en achternaam combinatie als die al int DB staan dan
	 *         wordt null return</li>
	 *         <li><b>stap 4</b><br>
	 *         medewerker toevoegen aan Persoon tabel</li>
	 *         <li><b>stap 5</b><br>
	 *         vragen aan DB id van deze nieuw persoon om die id te bruiken bij
	 *         medewerker toevoegen</li>
	 *         <li><b>stap 6</b><br>
	 *         login gegevens toevoegen aan Login tabel</li>
	 *         <li><b>stap 7</b><br>
	 *         vragen aan Login tabel de id van deze nieuwe login om die te
	 *         gebruiken bij medewerker toevoegen</li>
	 *         <li><b>stap 8</b><br>
	 *         medewerker toevoegen aan tabel medewerken omdat ik nu alle
	 *         informatie dat ik nodig heb in order zijn</li>
	 *         <li><b>stap 9</b><br>
	 *         return medewerker object met alles ingevuld</li>
	 *         </ol>
	 */
	public static Medewerker medewerkerToevoegen(Medewerker medewerker) {
		// ik heb dit in stappen gedaan

		if (medewerker == null) {
			return null;
		}
		PreparedStatement stmt = null;
		java.sql.Connection connection = null;
		ResultSet resultSet = null;
		try {
			connection = Connection.getDBConnection();
			connection.setAutoCommit(false);

			stmt = connection.prepareStatement(LOGIN_ZOEKEN_OP_USERNAME);
			stmt.setString(1, medewerker.getLogin().getUsername());
			resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				resultSet.close();
				stmt.close();
				connection.close();
				return null;
			}
			stmt = connection.prepareStatement(ROL_ZOEKEN_OP_NAAM);
			stmt.setString(1, medewerker.getRol().getRol());
			resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				medewerker.getRol().setRolId(resultSet.getInt(1));
				medewerker.getRol().setRol(resultSet.getString(2));
			} else {
				// rol is required in DB. dus geveven Rol niet bestaat
				// falt deze methode
				resultSet.close();
				stmt.close();
				connection.close();
				return null;
			}

			stmt = connection.prepareStatement(MEDEWERKER_ZOEKEN_OP_NAAM_EN_ACHTERNAAM);
			stmt.setString(1, medewerker.getVoornaam());
			stmt.setString(2, medewerker.getAchternaam());
			resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				resultSet.close();
				stmt.close();
				connection.close();
				return null;
			}
			// alle controles zijn gedaan en hier is methode klaar om te beginen
			// met toevoegen van medewerker
			stmt = connection.prepareStatement(PERSOON_TOEVOEGEN);
			stmt.setString(1, medewerker.getVoornaam());
			stmt.setString(2, medewerker.getAchternaam());
			stmt.executeQuery();
			connection.commit();

			stmt = connection.prepareStatement(MEDEWERKER_ZOEKEN_OP_NAAM_EN_ACHTERNAAM);
			stmt.setString(1, medewerker.getVoornaam());
			stmt.setString(2, medewerker.getAchternaam());
			resultSet = stmt.executeQuery();
			resultSet.next();
			medewerker.setPersoonId(resultSet.getInt(1));

			stmt = connection.prepareStatement(LOGIN_TOEVOEGEN);
			stmt.setString(1, medewerker.getLogin().getUsername());
			stmt.setString(2, medewerker.getLogin().getPassword());
			stmt.setString(3, medewerker.getLogin().getEmail());
			resultSet = stmt.executeQuery();
			connection.commit();

			stmt = connection.prepareStatement(LOGIN_ZOEKEN_OP_USERNAME);
			stmt.setString(1, medewerker.getLogin().getUsername());
			resultSet = stmt.executeQuery();
			resultSet.next();
			medewerker.getLogin().setLoginId(resultSet.getInt(1));

			// eindelijk medewerker toevoegen an medewerker tabel zelf

			stmt = connection.prepareStatement(MEDEWERKER_TOEVOEGEN);
			stmt.setInt(1, medewerker.getLogin().getLoginId());
			stmt.setInt(2, medewerker.getPersoonId());
			stmt.setInt(3, medewerker.getRol().getRolId());
			stmt.setInt(3, medewerker.isActief() ? 1 : 0);
			connection.commit();

			stmt = connection.prepareStatement(MEDEWERKER_ZOEKEN_OP_NAAM_EN_ACHTERNAAM);
			stmt.setString(1, medewerker.getVoornaam());
			stmt.setString(2, medewerker.getAchternaam());
			resultSet = stmt.executeQuery();
			resultSet.next();
			medewerker.setMedewerkerId(resultSet.getInt(1));

			resultSet.close();
			stmt.close();
			connection.close();
			return medewerker;

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
		return null;
	}

	/**
	 * Medewerker wijzigen
	 * 
	 * @param medewerker
	 *            de tewijzigen medewerker
	 * @return true als medewerker gegevens gewijzigd zijn
	 * @exception ExceptionMetBericht
	 *                medewerker gegevens niet juist bv. als een combinatie van
	 *                persoonId en medewerkerId en login(en zijn equals is niet
	 *                gelijk aan die van deze medewerker) <br>
	 *                <br>
	 *                <b>Werking:</b><br>
	 *                <ol>
	 *                <li><b>stap 1</b> checken of medewerker in DB staat</li>
	 *                <li><b>stap 2</b> checken of medewerker in DB = medewerker
	 *                die gegeven wordt als parameter. met behulp van
	 *                custom(aangepast) medewerker.equals</li>
	 *                <li><b>stap 3</b> persoon info. wijzigen mbv
	 *                PersoonDao.persoonWijzigen(medewerker);</li>
	 *                <li><b>stap 4</b> de rol van medewerker wijzigen. Als die
	 *                rolnaam die gegeven wordt niet in DB voorkomt wordt een
	 *                exception gegooid en verder wijzigingen stoppen daar maar
	 *                als de rol naam bestaat in DB wordt medewerker rol
	 *                gewijzigd</li>
	 *                <li><b>stap 5</b> de login van wedewerker wordt gewijzigd.
	 *                <ol>
	 *                <li>Als de login naam niet uniek is en dus bestaat al in
	 *                DB wordt er verwacht dat
	 *                LoginDao.loginWijzigen(medewerker.getLogin()) een
	 *                exception gooit.</li>
	 *                <li>Als de naam wel uniek is dan loopt medewerkerWijzigen
	 *                verder</li>
	 *                </ol>
	 *                </li>
	 *                <li><b>stap 6</b> Medewerker tabel wordt gewijzigd. De
	 *                kolom actief wordt gewijzigd</li>
	 *                </ol>
	 */
	public static boolean medewerkerWijzigen(Medewerker medewerker) throws Exception {
		// waarom gebruik ik zo veel thows Exception ?
		// omdat return van boolean niet veel informatie terug geeft
		// van wat er miss loopt

		boolean success = false;
		if (medewerker == null) {
			throw new Exception("gegeven medewerker parameter is null");
		}
		PreparedStatement stmt = null;
		java.sql.Connection connection = null;
		try {
			Medewerker dbMedewerker = MedewerkerDAO.medewerkerZoekenOpMedewerkerId(medewerker);
			if (dbMedewerker == medewerker) {
				connection = Connection.getDBConnection();
				connection.setAutoCommit(false);
				PersoonDao.persoonWijzigen(medewerker);
				// eerst zien of gegeven Rol bestaat of niet dan die wijzigen
				medewerker.setRol(RolDAO.zoekRolOpRolNaam(medewerker.getRol()));

				LoginDao.loginWijzigen(medewerker.getLogin());

				stmt = connection.prepareStatement(MEDEWERKER_INFO_WIJZIGEN);
				stmt.setInt(1, medewerker.isActief()?1:0);
				stmt.setInt(2, medewerker.getMedewerkerId());
				stmt.executeUpdate();
				connection.commit();
				success = true;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
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
/**
 * Zoekt medewerker op medewerker id
 * @param medewerkerId
 * @return Medewerker met alle informatie van deze medewerker ingevuld
 * @throws ExceptionmedewerkerZoekenOpMedewerkerId als:<br><br>
 * <ol>
 * <li>medewerkerId = 0</li> 
 * <li>medewerker wordt niet gevonden in DB</li> 
 * <li>een van de onderligende subklassen zoals LoginDAO heeft een exception gegooid</li> 
 * </ol>
 * <br>
 * <b>Werking:</b>
 * <ol>
 * <li>stap 1 zoeken in DB voor medewerker op medewerker Id. Niet persoonId</li> 
 * <li>stap 2 als medewerker wordt gevonden dan vul local medewerker object met medewerker tabel info
	 * <ol>
	 * <li>als medewerker niet gevonden dan wordt er een exception gegooid</li>  
	 * </ol> </li> 
 * <li>stap 3 alle andere medewerker gegevens worden ingevuld als volgt:
  * <ol>
	 * <li>Persoon gegevens dat medewerker class overerft van Persson worden ingevuld mbv PersoonDAO. 
	 * Het is de job van PersoonDAO om te zorgen dat alles goed ingevuld wordt van persoon</li>  
	 *  <li>
	 *  Login gegevens van medewerker worden goed ingevuld mbv LoginDAO
	 *  </li>
	 *  <li>Rol gegeven van medewerker worden goed ingevuld mbv. RolDAO</li> 
	 * </ol> </li>  
 * <li></li> 
 * <li></li> 
 * <li></li> 
 * <li></li> 
 * </ol> 
 * */
	public static Medewerker medewerkerZoekenOpMedewerkerId(int medewerkerId) throws Exception {
		if (medewerkerId == 0) {
			throw new Exception("gegeven medewerkerId parameter is 0");
		}
		Medewerker medewerker = new Medewerker();
		PreparedStatement stmt = null;
		java.sql.Connection connection = null;
		ResultSet resultSet = null;
		try {
			connection = Connection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(MEDEWERKER_ZOEKEN_OP_MEDEWERKERID);
			stmt.setInt(1, medewerkerId);
			resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				medewerker.setMedewerkerId(resultSet.getInt(1));
				medewerker.setPersoonId(resultSet.getInt(2));
				medewerker.getLogin().setLoginId(resultSet.getInt(3));
				medewerker.getRol().setRolId(resultSet.getInt(4));
				medewerker.setActief(resultSet.getInt(5) == 0 ? false : true);
				resultSet.close();
				stmt.close();
				connection.close();
				medewerker.setLogin(LoginDao.loginZoekenOpLoginId(medewerker.getLogin()));
				medewerker.setRol(RolDAO.zoekRolOpRolNaam(medewerker.getRol()));
				Persoon persoon = PersoonDao.zoekPersoonOpPersoonId(medewerker);

				medewerker.setAchternaam(persoon.getAchternaam());
				medewerker.setVoornaam(persoon.getVoornaam());
				medewerker.setAdresId(persoon.getAdresId());
				return medewerker;
			}
			throw new Exception("gegeven medewerkerId parameter is niet gevonden in DB");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
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
		return null;
	}
	/**
	 * Zoekt medewerker op medewerker id
	 * @param medewerker
	 * @return Medewerker met alle informatie van deze medewerker ingevuld
	 * @throws ExceptionmedewerkerZoekenOpMedewerkerId als:<br><br>
	 * <ol>
	 * <li>medewerker = null</li> 
	 * <li>medewerkerId = 0</li> 
	 * <li>medewerker wordt niet gevonden in DB</li> 
	 * <li>een van de onderligende subklassen zoals LoginDAO heeft een exception gegooid</li> 
	 * </ol>
	 * <br>
	 * <b>Werking:</b>
	 * <ol>
	 * <li>stap 1 zoeken in DB voor medewerker op medewerker Id. Niet persoonId</li> 
	 * <li>stap 2 als medewerker wordt gevonden dan vul local medewerker object met medewerker tabel info
		 * <ol>
		 * <li>als medewerker niet gevonden dan wordt er een exception gegooid</li>  
		 * </ol> </li> 
	 * <li>stap 3 alle andere medewerker gegevens worden ingevuld als volgt:
	  * <ol>
		 * <li>Persoon gegevens dat medewerker class overerft van Persson worden ingevuld mbv PersoonDAO. 
		 * Het is de job van PersoonDAO om te zorgen dat alles goed ingevuld wordt van persoon</li>  
		 *  <li>
		 *  Login gegevens van medewerker worden goed ingevuld mbv LoginDAO
		 *  </li>
		 *  <li>Rol gegeven van medewerker worden goed ingevuld mbv. RolDAO</li> 
		 * </ol> </li>  
	 * <li></li> 
	 * <li></li> 
	 * <li></li> 
	 * <li></li> 
	 * </ol> 
	 * */
	public static Medewerker medewerkerZoekenOpMedewerkerId(Medewerker medewerker) throws Exception {
		if (medewerker == null) {
			throw new Exception("gegeven medewerker parameter is null");
		}
		try {
			medewerkerZoekenOpMedewerkerId(medewerker.getMedewerkerId());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return null;
	}
}

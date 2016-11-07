package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import source.*;

public class GebruikerBeheerDAO {

	private static final String KLANT_WIJZIGEN = "UPDATE Klant SET persoonId = ?, info = ?, email = ?, nieuws = ? WHERE persoonId = ?";
	private static final String KLANT_ZOEKEN_OP_ID = "SELECT persoonId, info, email, nieuws WHERE persoonId = ?";
	private static final String KLANT_ZOEKEN_OP_VOORNAAM = "SELECT DISTINCT Persoon.persoonId, Persoon.voornaam, Persoon.achternaam,Persoon.adresId,Klant.email,Klant.info,Klant.nieuws FROM Klant INNER JOIN Persoon ON Klant.persoonId=Persoon.persoonId WHERE Persoon.voornaam LIKE ? ";
	private static final String KLANT_ZOEKEN_OP_ACHTERNAAM = "SELECT DISTINCT Persoon.persoonId, Persoon.voornaam, Persoon.achternaam,Persoon.adresId,Klant.email,Klant.info,Klant.nieuws FROM Klant INNER JOIN Persoon ON Klant.persoonId=Persoon.persoonId WHERE Persoon.achternaam LIKE ? ";
	private static final String KLANT_ZOEKEN_OP_EMAIL = "SELECT DISTINCT Persoon.persoonId, Persoon.voornaam, Persoon.achternaam,Persoon.adresId,Klant.email,Klant.info,Klant.nieuws FROM Klant INNER JOIN Persoon ON Klant.persoonId=Persoon.persoonId WHERE Klant.email LIKE ? ";
	
	private static java.sql.Connection connection;
	private static Statement command;
	private static ResultSet data;
	private static PreparedStatement stmt = null;
	
	public static boolean addGebruiker(Login login, Persoon persoon, int rollId, Adres adres){
		int loginId =  0, persoonId = 0;
		boolean opslagGelukt= true;
	    try {
	        connection = Connection.getDBConnection();        
	        loginId = LoginDao.addLogin(login);
	        persoonId = PersoonDAO.addPersoon(persoon, adres);

	        stmt = connection.prepareStatement("INSERT INTO Medewerker (loginId, persoonId, rolId, actief) VALUES(?,?,?,?);");
	        stmt.setInt(1, loginId);
	        stmt.setInt(2, persoonId);
	        stmt.setInt(3, 1); // rollid staat nu vast moet nog aangepast worden
	        stmt.setInt(4,  1 );
	        stmt.executeUpdate();
	    }catch (SQLException e){
	        e.printStackTrace();
	        opslagGelukt=false;
	    }catch(Exception e) {//Handle errors for Class.forName
	        e.printStackTrace();
	        opslagGelukt=false;
	    }finally{
	    	try{
	    		if (data!=null){data.close();}
	            if(connection!=null)connection.close();
	        }catch(SQLException se2){
	            se2.printStackTrace();
	        }
	    }    
	    return opslagGelukt;
	}
	
	public static List<Klant> zoekKlantenOpEmail(String email) {
		if (email == null) {
			return null;
		}
		if (email == "" || email.trim().isEmpty()) {
			return null;
		}
		PreparedStatement stmt = null;
		java.sql.Connection connection = null;
		ResultSet resultSet = null;
		try {
			connection = Connection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(KLANT_ZOEKEN_OP_EMAIL);
			stmt.setString(1, "%" + email + "%");
			resultSet = stmt.executeQuery();
			ArrayList<Klant> klanten = new ArrayList<>();
			while (resultSet.next()) {
				klanten.add(new Klant(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getInt(4), resultSet.getString(5), resultSet.getString(6),
						resultSet.getInt(7) == 0 ? false : true));
			}
			resultSet.close();
			stmt.close();
			connection.close();
			if (klanten.isEmpty())
				return null;

			return klanten;
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

	public static List<Klant> zoekKlantenOpAchternaam(String achternaam) {
		if (achternaam == null) {
			return null;
		}
		if (achternaam == "" || achternaam.trim().isEmpty()) {
			return null;
		}
		PreparedStatement stmt = null;
		java.sql.Connection connection = null;
		ResultSet resultSet = null;
		try {
			connection = Connection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(KLANT_ZOEKEN_OP_ACHTERNAAM);
			stmt.setString(1, "%" + achternaam + "%");
			resultSet = stmt.executeQuery();
			ArrayList<Klant> klanten = new ArrayList<>();
			while (resultSet.next()) {
				klanten.add(new Klant(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getInt(4), resultSet.getString(5), resultSet.getString(6),
						resultSet.getInt(7) == 0 ? false : true));
			}
			resultSet.close();
			stmt.close();
			connection.close();
			if (klanten.isEmpty())
				return null;

			return klanten;
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

	public static List<Klant> zoekKlantenOpVoornaam(String voornaam) {
		if (voornaam == null) {
			return null;
		}
		if (voornaam == "" || voornaam.trim().isEmpty()) {
			return null;
		}
		PreparedStatement stmt = null;
		java.sql.Connection connection = null;
		ResultSet resultSet = null;
		try {
			connection = Connection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(KLANT_ZOEKEN_OP_VOORNAAM);
			stmt.setString(1, "%" + voornaam + "%");
			resultSet = stmt.executeQuery();
			ArrayList<Klant> klanten = new ArrayList<>();
			while (resultSet.next()) {
				klanten.add(new Klant(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getInt(4), resultSet.getString(5), resultSet.getString(6),
						resultSet.getInt(7) == 0 ? false : true));
			}
			resultSet.close();
			stmt.close();
			connection.close();
			if (klanten.isEmpty())
				return null;

			return klanten;
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

	public static Klant zoekKlantenOpID(int persoonId) {
		if (persoonId == 0) {
			return null;
		}
		Klant klant = (Klant) zoekPersoonOpId(persoonId);
		if (klant == null) {
			return null;
		}
		PreparedStatement stmt = null;
		java.sql.Connection connection = null;
		ResultSet resultSet = null;
		try {
			connection = Connection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(KLANT_ZOEKEN_OP_ID);
			stmt.setInt(1, persoonId);
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				klant.setInfo(resultSet.getString(2));
				klant.setEmail(resultSet.getString(3));
				klant.setNieuws(resultSet.getInt(4) == 0 ? false : true);
				resultSet.close();
				stmt.close();
				connection.close();
				return klant;
			}

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

	public static boolean klantGegevensWijzigen(Klant klant) {
		if (klant.getPersoonId() == 0)
			return false;
		if (!persoonGegevensWijzigen(klant)) {// als iets fout is in persoon
												// wijzigen dan stopt het hier
			return false;
		}
		boolean success = false;
		PreparedStatement stmt = null;
		java.sql.Connection connection = null;
		try {
			connection = Connection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(KLANT_WIJZIGEN);
			stmt.setInt(1, klant.getPersoonId());
			stmt.setString(2, klant.getInfo());
			stmt.setString(3, klant.getEmail());
			stmt.setInt(4, klant.getNieuws() ? 1 : 0);
			stmt.setInt(5, klant.getPersoonId());
			stmt.executeUpdate();
			connection.commit();
			success = true;
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

	private static final String PERSOON_WIJZIGEN = "UPDATE Persoon SET adresId = ?, voornaam = ?, achternaam = ? WHERE persoonId = ?";
	private static final String PERSOON_ZOEKEN_OP_ID = "SELECT persoonId, voornaam, achternaam, adresId FROM Persoon WHERE persoonId = ?";
	private static final String PERSOON_ZOEKEN_OP_ADRES = "SELECT persoonId, voornaam, achternaam, adresId FROM Persoon WHERE adresId = ?";
	private static final String PERSOON_ZOEKEN_OP_VOORNAAM = "SELECT persoonId, voornaam, achternaam, adresId FROM Persoon WHERE voornaam LIKE ?";
	private static final String PERSOON_ZOEKEN_OP_ACHTERNAAM = "SELECT persoonId, voornaam, achternaam, adresId FROM Persoon WHERE achternaam LIKE ?";

	public static List<Persoon> zoekPersonenOpAchternaam(String achternaam) {
		if (achternaam == null) {
			return null;
		}
		if (achternaam == "" || achternaam.trim().isEmpty()) {
			return null;
		}
		PreparedStatement stmt = null;
		java.sql.Connection connection = null;
		ResultSet resultSet = null;
		try {
			connection = Connection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(PERSOON_ZOEKEN_OP_ACHTERNAAM);
			stmt.setString(1, "%" + achternaam + "%");
			resultSet = stmt.executeQuery();
			ArrayList<Persoon> personen = new ArrayList<>();
			while (resultSet.next()) {
				personen.add(new Persoon(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getInt(4)));
			}
			resultSet.close();
			stmt.close();
			connection.close();
			if (personen.isEmpty())
				return null;

			return personen;
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

	public static List<Persoon> zoekPersonenOpVoornaam(String voornaam) {
		if (voornaam == null) {
			return null;
		}
		if (voornaam == "" || voornaam.trim().isEmpty()) {
			return null;
		}
		PreparedStatement stmt = null;
		java.sql.Connection connection = null;
		ResultSet resultSet = null;
		try {
			connection = Connection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(PERSOON_ZOEKEN_OP_VOORNAAM);
			stmt.setString(1, "%" + voornaam + "%");
			resultSet = stmt.executeQuery();
			ArrayList<Persoon> personen = new ArrayList<>();
			while (resultSet.next()) {
				personen.add(new Persoon(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getInt(4)));
			}
			resultSet.close();
			stmt.close();
			connection.close();
			if (personen.isEmpty())
				return null;

			return personen;
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

	public static List<Persoon> zoekPersonenOpAdres(int adresId) {
		if (adresId == 0) {
			return null;
		}
		PreparedStatement stmt = null;
		java.sql.Connection connection = null;
		ResultSet resultSet = null;
		try {
			connection = Connection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(PERSOON_ZOEKEN_OP_ADRES);
			stmt.setInt(1, adresId);
			resultSet = stmt.executeQuery();
			ArrayList<Persoon> personen = new ArrayList<>();
			while (resultSet.next()) {
				personen.add(new Persoon(adresId, resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4)));
			}
			resultSet.close();
			stmt.close();
			connection.close();
			if (personen.isEmpty())
				return null;

			return personen;
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

	public static Persoon zoekPersoonOpId(int persoonId) {
		if (persoonId == 0) {
			return null;
		}
		PreparedStatement stmt = null;
		java.sql.Connection connection = null;
		ResultSet resultSet = null;
		try {
			connection = Connection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(PERSOON_ZOEKEN_OP_ID);
			stmt.setInt(1, persoonId);
			resultSet = stmt.executeQuery();
			Persoon persoon = null;
			while (resultSet.next()) {
				persoon = new Persoon(persoonId, resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
				resultSet.close();
				stmt.close();
				connection.close();
				return persoon;
			}

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

	public static boolean persoonGegevensWijzigen(Persoon persoon) {
		if (persoon.getPersoonId() == 0)
			return false;
		boolean success = false;
		PreparedStatement stmt = null;
		java.sql.Connection connection = null;
		try {
			connection = Connection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(PERSOON_WIJZIGEN);
			stmt.setInt(1, persoon.getAdresId());
			stmt.setString(2, persoon.getVoornaam());
			stmt.setString(3, persoon.getAchternaam());
			stmt.executeUpdate();
			connection.commit();
			success = true;
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

	private static final String ADRES_WIJZIGEN = "UPDATE Adres SET straat = ?, woonplaats = ?, postcode = ?, bus = ? WHERE adresId = ?";
	private static final String ADRES_ZOEKEN_OP_ID = "SELECT * FROM Adres WHERE adresId = ?";
	private static final String ADRES_ZOEKEN_OP_POSTCODE = "SELECT * FROM Adres WHERE postcode = ?";
	private static final String ADRES_ZOEKEN_OP_STRAAT = "SELECT * FROM Adres WHERE straat LIKE ?";
	private static final String ADRES_ZOEKEN_OP_WOONPLAATS = "SELECT * FROM Adres WHERE woonplaats LIKE ?";

	/**
	 * Een bestande adress wijzigen. Hier is het belangrijk dat er al een
	 * adresId bestaat in Database
	 * 
	 * @param adres
	 * @return true als wijzing goed is vollbracht, false als wijzing goed is
	 *         niet vollbracht
	 */
	public static boolean adresWijzigen(Adres adres) {

		if (adres.getAdresId() == 0)
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
			success = true;
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

	/**
	 * Een Adres zoeken op adresId return null als id niet voorkomt in databank
	 * tabel. Return Adres als adresId voorkomt in databank tabel. In het geval
	 * dat er meer dan een id in databank tabel bestaat, bv als DB adresId niet
	 * uniek is dat return deze methode de eerste Adres van resultaat
	 * 
	 * @param adresId
	 * @return Adres
	 */
	public static Adres zoekAdresOpId(int adresId) {

		if (adresId == 0) {
			return null;
		}
		PreparedStatement stmt = null;
		java.sql.Connection connection = null;
		ResultSet resultSet = null;

		try {
			connection = Connection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(ADRES_ZOEKEN_OP_ID);
			stmt.setInt(1, adresId);
			resultSet = stmt.executeQuery();
			Adres adres = null;
			while (resultSet.next()) {
				adres = new Adres(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getString(4), resultSet.getInt(5), resultSet.getInt(6) );
				resultSet.close();
				stmt.close();
				connection.close();
				return adres;
			}

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
	 * Een Adres zoeken op straat naam. Return null als straat naam niet
	 * voorkomt in databank tabel. Return List<Adres> als straat naam voorkomt
	 * in databank tabel. Deze methode gaat er van uit dat er maar een deel van
	 * de straat naam wordt meegegeven
	 * 
	 * @param straat
	 *            - Straat naam
	 * @return Een List<Adres> van adressen dat overeenkomen met de straat naam
	 *         bv A ingeven als straat geeft als resultaat een List<Adres> met
	 *         alle straten die beginnen met A
	 */
	public static List<Adres> zoekAdressenOpStraat(String straat) {
		if (straat == null) {
			return null;
		}
		if (straat == "" || straat.trim().isEmpty()) {
			return null;
		}
		PreparedStatement stmt = null;
		java.sql.Connection connection = null;
		ResultSet resultSet = null;

		try {
			connection = Connection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(ADRES_ZOEKEN_OP_STRAAT);
			stmt.setString(1, "%" + straat + "%");
			resultSet = stmt.executeQuery();
			ArrayList<Adres> adressen = new ArrayList<>();
			while (resultSet.next()) {
				adressen.add(new Adres(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getString(4), resultSet.getInt(5), resultSet.getInt(6) ));
			}
			resultSet.close();
			stmt.close();
			connection.close();
			if (adressen.isEmpty())
				return null;

			return adressen;
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
	 * Een Adres zoeken op woonplaats naam. Return null als woonplaats naam niet
	 * voorkomt in databank tabel. Return List<Adres> als woonplaats naam
	 * voorkomt in databank tabel. Deze methode gaat er van uit dat er maar een
	 * deel van de woonplaats naam wordt meegegeven bv A ingeven als woonplaats
	 * geeft als resultaat een List<Adres> met alle woonplaatsen die beginnen
	 * met A
	 * 
	 * @param woonplaats
	 *            - woonplaats naam
	 * @return Een List<Adres> van adressen dat overeenkomen met de woonplaats
	 *         naam
	 */
	public static List<Adres> zoekAdressenOpWoonPlaats(String woonplaats) {
		if (woonplaats == null) {
			return null;
		}
		if (woonplaats == "" || woonplaats.trim().isEmpty()) {
			return null;
		}
		PreparedStatement stmt = null;
		java.sql.Connection connection = null;
		ResultSet resultSet = null;

		try {
			connection = Connection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(ADRES_ZOEKEN_OP_WOONPLAATS);
			stmt.setString(1, "%" + woonplaats + "%");
			resultSet = stmt.executeQuery();
			ArrayList<Adres> adressen = new ArrayList<>();
			while (resultSet.next()) {
				adressen.add(new Adres(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getString(4), resultSet.getInt(5), resultSet.getInt(6)));
			}
			resultSet.close();
			stmt.close();
			connection.close();
			if (adressen.isEmpty())
				return null;

			return adressen;
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
	 * Een Adres zoeken op postcode. Return null als postcode niet voorkomt in
	 * databank tabel. Return List<Adres> als postcode voorkomt in databank
	 * tabel.
	 * 
	 * @param woonplaats
	 *            - woonplaats naam
	 * @return Een List<Adres> van adressen dat overeenkomen met de woonplaats
	 *         naam
	 */
	public static List<Adres> zoekAdressenOpPostcode(int postcode) {
		if (postcode <= 0) {
			return null;
		}
		PreparedStatement stmt = null;
		java.sql.Connection connection = null;
		ResultSet resultSet = null;
		try {
			connection = Connection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(ADRES_ZOEKEN_OP_POSTCODE);
			stmt.setInt(1, postcode);
			resultSet = stmt.executeQuery();
			ArrayList<Adres> adressen = new ArrayList<>();
			while (resultSet.next()) {
				adressen.add(new Adres(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getString(4), resultSet.getInt(5), resultSet.getInt(6)));
			}
			resultSet.close();
			stmt.close();
			connection.close();
			if (adressen.isEmpty())
				return null;

			return adressen;
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

}

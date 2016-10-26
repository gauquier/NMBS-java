package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.geom.AreaOp.AddOp;

import source.*;

public class GebruikerBeheerDAO {

	// gebruiker bewerken sql
	private static final String ADRES_WIJZIGEN = "UPDATE Adres SET straat = ?, woonplaats = ?, postcode = ?, bus = ? WHERE adresId = ?";
	private static final String ADRES_ZOEKEN_OP_ID = "SELECT * FROM Adres WHERE adresId = ?";
	private static final String ADRES_ZOEKEN_OP_POSTCODE = "SELECT * FROM Adres WHERE postcode = ?";
	private static final String ADRES_ZOEKEN_OP_STRAAT = "SELECT * FROM Adres WHERE straat LIKE '%?%'";
	private static final String ADRES_ZOEKEN_OP_WOONPLAATS = "SELECT * FROM Adres WHERE woonplaats LIKE '%?%'";

	/**
	 * Een bestande adress wijzigen hier is het belangrijk dat er al een adresId
	 * bestaat in Database
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
	 * return Adres als id voorkomt in databank. In het geval dat er meer dan
	 * een id in databank bestaat, bv als DB adresId niet uniek is dat return
	 * deze methode de eerste Adres van resultaat
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
			while (resultSet.next()) {
				return new Adres(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getInt(4), resultSet.getInt(5));
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
	 * Een Adres zoeken op adres return null als straat naam niet voorkomt in
	 * databank return List<Adres> als straat naam voorkomt in databank. Deze
	 * methode gaat er van uit dat er maar een deel van de straat naam wordt
	 * meegegeven
	 * 
	 * @param straat
	 *            - Straat naam
	 * @return Een lijst van adressen dat overeenkomen met de straat naam
	 */
	public static List<Adres> zoekAdressenOpStraat(String straat) {
		straat = straat.trim();
		if (straat == null || straat == "" || straat.isEmpty()) {
			return null;
		}
		PreparedStatement stmt = null;
		java.sql.Connection connection = null;
		ResultSet resultSet = null;

		try {
			connection = Connection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(ADRES_ZOEKEN_OP_ID);
			stmt.setString(1, straat);
			resultSet = stmt.executeQuery();
			ArrayList<Adres> adressen = new ArrayList<>();
			while (resultSet.next()) {
				adressen.add(new Adres(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getInt(4), resultSet.getInt(5)));
			}
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

	public void zoekAdressenOpWoonPlaats() {
	}

	public void zoekAdressenOppostCode() {
	}
}

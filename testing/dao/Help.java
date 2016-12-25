package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import source.Adres;
import source.Persoon;

public class Help {
	public static Persoon persoonToevoegen(Persoon persoon) {
		persoon.setAdres(adresToevoegen(persoon.getAdres()));
		String persoonToevoegenQuery = "INSERT INTO Persoon " + "(adresId, voornaam, achternaam, email) "
				+ "VALUES(?,?,?,?)";
		executeQuery(persoonToevoegenQuery, true, persoon.getAdres().getAdresId(), persoon.getVoornaam(),
				persoon.getAchternaam(), persoon.getEmail());
		return persoonOphalen(persoon);
	}

	public static Persoon persoonOphalen(Persoon persoon) {
		String persoonZoekenQuery = "SELECT persoonId " + "FROM Persoon " + "WHERE voornaam = ?";
		Map<Integer, Object[]> map = executeQuery(persoonZoekenQuery, false, persoon.getVoornaam());
		if (map.size() > 0)
			persoon.setId((int) map.get(0)[0]);
		return persoon;
	}
	public static Adres adresOphalen(Adres adres) {
		String adresZoekenQuery = "SELECT adresId FROM Adres WHERE straat = ?";
		Map<Integer, Object[]> map = executeQuery(adresZoekenQuery, false, adres.getStraat());
		adres.setAdresId((int) map.get(0)[0]);
		return adres;
	}

	public  static Adres adresToevoegen(Adres adres) {
		String adresToevoegenQuery = "INSERT INTO Adres (straat, huisnr, woonplaats, postcode, bus) VALUES(?,?,?,?,?)";
		executeQuery(adresToevoegenQuery, true, adres.getStraat(), adres.getHuisnr(), adres.getWoonplaats(),
				adres.getPostcode(), adres.getBus());
		return adresOphalen(adres);
	}
public static Map<Integer, Object[]> executeQuery(String query, boolean update, Object... kolomWaarden) {
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
}

package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import source.Adres;
import source.Login;
import source.Medewerker;
import source.Persoon;
import source.Prijs;
import source.Rol;
import source.Station;
import source.Ticket;

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

	public static Adres adresToevoegen(Adres adres) {
		String adresToevoegenQuery = "INSERT INTO Adres (straat, huisnr, woonplaats, postcode, bus) VALUES(?,?,?,?,?)";
		executeQuery(adresToevoegenQuery, true, adres.getStraat(), adres.getHuisnr(), adres.getWoonplaats(),
				adres.getPostcode(), adres.getBus());
		return adresOphalen(adres);
	}

	public static Prijs prijsToevoegen(Prijs prijs) {
		String prijsToevoegen = "INSERT INTO Prijs " + "(verkoopType, prijs ) " + "VALUES(?,?)";
		executeQuery(prijsToevoegen, true, prijs.getVerkoopType(), prijs.getPrijs());

		return prijsOphalen(prijs);
	}

	public static Prijs prijsOphalen(Prijs prijs) {
		String prijsZoekenQuery = "SELECT prijsId, verkoopType, prijs FROM Prijs WHERE verkoopType = ?";
		Map<Integer, Object[]> map = executeQuery(prijsZoekenQuery, false, prijs.getVerkoopType());
		if (map.size() > 0) {
			prijs.setPrijsId((int) map.get(0)[0]);
			prijs.setVerkoopType((String) map.get(0)[1]);
			prijs.setPrijs((double) map.get(0)[2]);
			return prijs;
		}
		return null;
	}

	public static int countPrijzen() {
		String countPrijzen = "SELECT prijsId FROM Prijs";
		Map<Integer, Object[]> map = executeQuery(countPrijzen, false);
		return map.size();
	}

	public static Rol rolOphalen(Rol rol) {
		String rolZoekenQuery = "SELECT rolId FROM Rol " + "WHERE rol = ?";
		Map<Integer, Object[]> map = executeQuery(rolZoekenQuery, false, rol.getRol());
		if (map.size() > 0)
			rol.setRolId((int) map.get(0)[0]);
		return rol;
	}

	public static Rol rolToevoegen(Rol rol) {
		String rolToevoegenQuery = "INSERT INTO Rol (rol) VALUES(?)";
		executeQuery(rolToevoegenQuery, true, rol.getRol());
		return rolOphalen(rol);
	}

	public static Station stationToevoegen(Station station) {
		String stationToevoegen = "INSERT INTO Station (naam) VALUES(?)";
		executeQuery(stationToevoegen, true, station.getNaam());
		return stationOphalen(station);
	}

	public static Station stationOphalen(Station station) {
		String stationZoekenQuery = "SELECT stationId FROM Station WHERE naam = ?";
		Map<Integer, Object[]> map = executeQuery(stationZoekenQuery, false, station.getNaam());
		if (map.size() > 0)
			station.setStationID((int) map.get(0)[0]);
		return station;
	}

	public static Medewerker medewerkerToevoegen(Medewerker medewerker) {

		medewerker = (Medewerker) persoonToevoegen(medewerker);
		medewerker.setLogin(loginToevoegen(medewerker.getLogin()));
		medewerker.setRol(rolToevoegen(medewerker.getRol()));
		String medewerkerToevoegenQuery = "INSERT INTO Medewerker " + "(loginId, persoonId, rolId,Actief) "
				+ "VALUES(?,?,?,?)";
		executeQuery(medewerkerToevoegenQuery, true, medewerker.getLogin().getLoginId(), medewerker.getId(),
				medewerker.getRol().getRolId(), medewerker.isActief() ? 1 : 0);
		return medewerkerOphalen(medewerker);
	}

	public static Medewerker medewerkerOphalen(Medewerker medewerker) {
		String medewerkerZoekenQuery = "SELECT medewerkerId FROM Medewerker " + "WHERE persoonId = ?";
		Map<Integer, Object[]> map = executeQuery(medewerkerZoekenQuery, false, medewerker.getId());
		medewerker.setMedewerkerId((int) map.get(0)[0]);
		return medewerker;
	}

	public static Login loginToevoegen(Login login) {
		String loginToevoen = "INSERT INTO Login (username, pass) " + "VALUES(?,?)";
		executeQuery(loginToevoen, true, login.getUsername(), login.getPassword());
		return loginOphalen(login);
	}

	public static Login loginOphalen(Login login) {
		String loginOphalen = "SELECT loginId FROM Login WHERE username LIKE '%" + login.getUsername() + "%'";
		Map<Integer, Object[]> map = executeQuery(loginOphalen, false);
		login.setLoginId((int) map.get(0)[0]);
		return login;
	}
	public static Ticket ticketToevoegen(Ticket ticket){
		String ticketToevoegen="INSERT INTO Ticket (medewerkerId , depZone , arrZone , verkoopStation , prijs , verkoopType , korting , klasse , aantal , verkoopDatum , heenDatum , terugDatum) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
		executeQuery(ticketToevoegen, true,
				ticket.getMedewerkerId(),
				ticket.getDepZone(),
				ticket.getArrZone(),
				ticket.getVerkoopStation(),
				ticket.getPrijs(),
				ticket.getVerkoop().toString(),
				ticket.getKorting(),
				ticket.getKlasse(),
				ticket.getAantal(),
				date.format(ticket.getVerkoopDatum()),
				date.format(ticket.getHeenDatum()),
				date.format(ticket.getTerugDatum()) 
				);
		return ticketOphalen(ticket);
	}
	public static Ticket ticketOphalen(Ticket ticket){
		String ticketOphalen="SELECT  ticketId FROM  Ticket WHERE depZone LIKE '%"+ticket.getDepZone()+"%'";
		Map<Integer, Object[]> map =executeQuery(ticketOphalen, false);
		ticket.setAankoopId((int) map.get(0)[0]); 
		return ticket;
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

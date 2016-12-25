package dao;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import source.Abonnement;
import source.Adres;
import source.Klant;
import source.Login;
import source.Medewerker;
import source.Periode;
import source.Persoon;
import source.Rol;
import source.VerkoopType;

public class PeriodeDAOTest {
	private String testClassName = "PeriodeDAOTest";
	private double korting;
	private double prijs;
	private String depZone;
	private String arrZone;

	private Adres adres;
	private String info;
	private boolean actief;
	private Persoon klant;
	private Abonnement abonnement;
	private Persoon medewerker;
	private Rol rol;
	private Login login;

	private String periodeStartDate, periodeEndDate, periodeVerkoopdatum;

	private Periode periode;

	@Before
	public void initialize() throws ParseException {

		adres = new Adres("straatnaam" + testClassName, 170, "woonplaats" + testClassName, 1070, "6");
		info = "info" + testClassName;
		actief = true;
		klant = new Klant(0, "voornaam" + testClassName, "achternaam" + testClassName, "email" + testClassName, adres,
				info, actief);

		korting = 5.00;
		prijs = 10.00;

		depZone = "depZone" + testClassName;
		arrZone = "depZone" + testClassName;

		abonnement = new Abonnement(korting, prijs, VerkoopType.STANDAARD, (Klant) klant, depZone, arrZone);

		login=new Login("username" + testClassName, "password" + testClassName);
		rol=new Rol(0, "rol" + testClassName);
		
		medewerker = new Medewerker(0, "voornaam" + testClassName, "achternaam" + testClassName,
				"email" + testClassName, adres);
		((Medewerker) medewerker).setLogin(login); 
		((Medewerker) medewerker).setRol(rol);
		((Medewerker) medewerker).setActief(true);

		periodeStartDate = "24-12-2999";
		periodeEndDate = "24-12-2999";
		periodeVerkoopdatum = "24-12-2999";
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		periode = new Periode(0, formatter.parse(periodeStartDate), formatter.parse(periodeEndDate),
				formatter.parse(periodeVerkoopdatum));
		periode.setAbonnement(abonnement);
		

	}

	@After
	public void terminate() {
		periodeVerwijderen();
	}

	@Test
	public void testaddPeriode() throws ParseException {
		abonnement = abonnementToevoegen(
				new Abonnement(korting, prijs, VerkoopType.STANDAARD, (Klant) klant, depZone, arrZone));
	 
		Medewerker medewerkerTmp = medewerkerToevoegen((Medewerker) medewerker);
		medewerker=medewerkerTmp;
		PeriodeDAO.addPeriode(periode, abonnement, medewerkerTmp.getMedewerkerId());
		Periode periodeTmp = periodeOphalen(periode);
		assertEquals(periode.getStartDate(), periodeTmp.getStartDate());

	} 
	@Test
	public void testUpdatePeriode() throws ParseException {
		Medewerker medewerkerTmp = medewerkerToevoegen((Medewerker) medewerker);
		medewerker=medewerkerTmp;
		periode.setMedewerkerId(medewerkerTmp.getMedewerkerId());
		periode=periodeToevoegen(periode);
		String nieuweEindDatum="30-12-2999";
		DateFormat date = new SimpleDateFormat("dd-MM-yyyy"); 
		periode.setEndDate(date.parse(nieuweEindDatum));
		PeriodeDAO.updatePeriode(periode, periode.getMedewerkerId());
		Periode periodeTmp = periodeOphalen(periode);
		assertEquals(periode.getEndDate(), periodeTmp.getEndDate());
	}
	@Test(expected=Exception.class)
	public void testUpdatePeriodeMetOnbestaandePeriode(){
		PeriodeDAO.updatePeriode(periode, periode.getMedewerkerId());
	} 
	@Test
	public void testGetPeriode() throws ParseException {
		Medewerker medewerkerTmp = medewerkerToevoegen((Medewerker) medewerker);
		medewerker=medewerkerTmp;
		periode.setMedewerkerId(medewerkerTmp.getMedewerkerId()); 
		periode=periodeToevoegen(periode);
		Periode periodeTmp = PeriodeDAO.getPeriode(periode.getAbonnement());
		assertEquals(periode.getStartDate(), periodeTmp.getStartDate()); 
	}
	@Test
	public void testGetPeriodeMetOnbestaandePeriodeAbonnement() throws ParseException {
		assertNull(PeriodeDAO.getPeriode(periode.getAbonnement()));
	}
	private Periode periodeToevoegen(Periode periode) throws ParseException{
		periode.setAbonnement(abonnementToevoegen(periode.getAbonnement()));
		DateFormat date = new SimpleDateFormat("dd-MM-yyyy"); 
		String periodeToevoegen = "INSERT INTO Periode (abonnementId, medewerkerId, startDate, endDate, verkoopdatum) " 
	+ "VALUES(?,?,?,?,?)";
		executeQuery(periodeToevoegen, true,
				periode.getAbonnement().getAbonnementId(),
				periode.getMedewerkerId(),
				date.format(periode.getStartDate()),
				date.format(periode.getEndDate()),
				date.format(periode.getVerkoopdatum())
				); 
		return periodeOphalen(periode);
	}
	private Periode periodeOphalen(Periode periode) throws ParseException {
		String periodeOphalen = "SELECT periodeId,startDate,endDate FROM Periode WHERE startDate = ?";
		DateFormat date = new SimpleDateFormat("dd-MM-yyyy"); 
		Map<Integer, Object[]> map = executeQuery(periodeOphalen, false,date.format(periode.getStartDate()));
		periode.setPeriodeId((int) map.get(0)[0]); 
		periode.setStartDate(date.parse((String)map.get(0)[1]));
		periode.setEndDate(date.parse((String)map.get(0)[2])); 
		return periode;
	}
	private Login loginToevoegen(Login login) {
		String loginToevoen = "INSERT INTO Login (username, pass) " + "VALUES(?,?)";
		executeQuery(loginToevoen, true, login.getUsername(), login.getPassword());
		return loginOphalen(login);
	}

	private Login loginOphalen(Login login) {
		String loginOphalen = "SELECT loginId FROM Login WHERE username LIKE '%"+testClassName+"%'";
		Map<Integer, Object[]> map = executeQuery(loginOphalen, false);
		login.setLoginId((int) map.get(0)[0]);
		return login;
	}

	private Rol rolToevoegen(Rol rol) {
		String rolToevoegenQuery = "INSERT INTO Rol " + "(rol) " + "VALUES(?)"; 
		executeQuery(rolToevoegenQuery, true, rol.getRol());
		return rolOphalen(rol);
	}

	private Rol rolOphalen(Rol rol) {
		String rolZoekenQuery = "SELECT rolId FROM Rol " + "WHERE rol = ?";
		Map<Integer, Object[]> map = executeQuery(rolZoekenQuery, false, rol.getRol());
		if (map.size() > 0)
			rol.setRolId((int) map.get(0)[0]);

		return rol;
	}

	private Medewerker medewerkerToevoegen(Medewerker medewerker) {
		
		medewerker=(Medewerker) persoonToevoegen(medewerker);
		medewerker.setLogin(loginToevoegen(medewerker.getLogin())); 
		medewerker.setRol(rolToevoegen(medewerker.getRol())); 
		String medewerkerToevoegenQuery = "INSERT INTO Medewerker " + "(loginId, persoonId, rolId,Actief) "
				+ "VALUES(?,?,?,?)"; 
		executeQuery(medewerkerToevoegenQuery, true, medewerker.getLogin().getLoginId(), medewerker.getId(),
				medewerker.getRol().getRolId(), medewerker.isActief() ? 1 : 0);
		return medewerkerOphalen(medewerker);
	}
	private Medewerker medewerkerOphalen(Medewerker medewerker){
		String medewerkerZoekenQuery = "SELECT medewerkerId FROM Medewerker " + "WHERE persoonId = ?";
		Map<Integer, Object[]>map = executeQuery(medewerkerZoekenQuery, false, medewerker.getId());
		medewerker.setMedewerkerId((int) map.get(0)[0]);
		return medewerker;
	}

	private Abonnement abonnementToevoegen(Abonnement abonnement) {
		abonnement.getKlant().setAdres(adresToevoegen(abonnement.getKlant().getAdres()));
		abonnement.setKlant((Klant) persoonToevoegen(abonnement.getKlant()));
		abonnement.setKlant(klantToevoegen(abonnement.getKlant()));
		String abonnementToevoegenQuery = "INSERT INTO Abonnement( klantId, depZone, arrZone, prijs, verkoopType, korting, actief) "
				+ "VALUES ( ?,?,?,?,?,?,? )";
		executeQuery(abonnementToevoegenQuery, true, abonnement.getKlant().getKlantId(), abonnement.getDepZone(),
				abonnement.getArrZone(), abonnement.getPrijs(), abonnement.getVerkoop().toString(),
				abonnement.getKorting(), abonnement.isActief() ? 1 : 0);
		return abonnementOphalen(abonnement);
	}

	private Abonnement abonnementOphalen(Abonnement abonnement) {
		String abonnementZoekenQuery = "SELECT  abonnementId  FROM Abonnement " + "WHERE " + "depZone = ?";
		Map<Integer, Object[]> map = executeQuery(abonnementZoekenQuery, false, abonnement.getDepZone());
		abonnement.setAbonnementId((int) map.get(0)[0]);
		return abonnement;
	}

	private Klant klantToevoegen(Klant klant) {
		String klantToevoegenQuery = "INSERT INTO Klant (persoonId, info, actief) VALUES(?,?,?)";
		executeQuery(klantToevoegenQuery, true, klant.getId(), klant.getInfo(), klant.isActief() ? 1 : 0);
		return klantOphalen(klant);
	}

	private Klant klantOphalen(Klant klant) {
		String klantZoekenQuery = "SELECT klantId FROM Klant WHERE info LIKE ? ";
		Map<Integer, Object[]> map = executeQuery(klantZoekenQuery, false, "%PeriodeDAOTest%");
		klant.setKlantId((int) map.get(0)[0]);
		return klant;
	}

	private Persoon persoonToevoegen(Persoon persoon) {
		persoon.setAdres(adresToevoegen(adres));
		String persoonToevoegenQuery = "INSERT INTO Persoon " + "(adresId, voornaam, achternaam, email) "
				+ "VALUES(?,?,?,?)";
		executeQuery(persoonToevoegenQuery, true, persoon.getAdres().getAdresId(), persoon.getVoornaam(),
				persoon.getAchternaam(), persoon.getEmail());
		return persoonOphalen(persoon);
	}

	private Persoon persoonOphalen(Persoon klant) {
		String persoonZoekenQuery = "SELECT persoonId " + "FROM Persoon " + "WHERE voornaam = ?";
		Map<Integer, Object[]> map = executeQuery(persoonZoekenQuery, false, klant.getVoornaam());
		if (map.size() > 0)
			klant.setId((int) map.get(0)[0]);
		return klant;
	}

	private Adres adresOphalen(Adres adres) {
		String adresZoekenQuery = "SELECT adresId FROM Adres WHERE straat = ?";
		Map<Integer, Object[]> map = executeQuery(adresZoekenQuery, false, adres.getStraat());
		adres.setAdresId((int) map.get(0)[0]);
		return adres;
	}

	private Adres adresToevoegen(Adres adres) {
		String adresToevoegenQuery = "INSERT INTO Adres (straat, huisnr, woonplaats, postcode, bus) VALUES(?,?,?,?,?)";
		executeQuery(adresToevoegenQuery, true, adres.getStraat(), adres.getHuisnr(), adres.getWoonplaats(),
				adres.getPostcode(), adres.getBus());
		return adresOphalen(adres);
	}

	private void periodeVerwijderen() {
		String periodeVerwijderen= "DELETE FROM Periode WHERE startDate LIKE '%"+periodeStartDate+"%'";
		
		
		String verwijderMedewerker = "DELETE FROM Medewerker WHERE persoonId= ?";
		
		String abonementVerwijderen= "DELETE FROM Abonnement WHERE depZone LIKE '%"+testClassName+"%'";
		String klantVerwijderen= "DELETE FROM Klant WHERE info LIKE '%"+testClassName+"%'";
		
		
		String verwijderPersoon = "DELETE FROM Persoon WHERE voornaam LIKE '%"+testClassName+"%'";
		
		
		String verwijderRol = "DELETE FROM Rol WHERE rol LIKE '%"+testClassName+"%'";
		
		String verwijderAdres = "DELETE FROM Adres WHERE straat LIKE '%"+testClassName+"%'";
		String verwijderLogin = "DELETE FROM Login WHERE username LIKE '%"+testClassName+"%'";
		
		executeQuery(periodeVerwijderen, true); 
		
		executeQuery(verwijderMedewerker, true, medewerker.getId());
		executeQuery(abonementVerwijderen, true); 
		executeQuery(klantVerwijderen, true); 
		
		executeQuery(verwijderPersoon, true);
		
		 
		executeQuery(verwijderRol, true); 
		executeQuery(verwijderAdres, true);
		executeQuery(verwijderLogin, true);
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

}

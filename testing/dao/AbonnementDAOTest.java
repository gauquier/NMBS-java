package dao;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import source.Abonnement;
import source.Adres;
import source.Klant;
import source.VerkoopType;

public class AbonnementDAOTest { 
	private Abonnement abonnement;
	 
	private double korting;
	private double prijs;

	private Klant bestaandeKlant;
	private Adres bestaandeKlantAdres;
	private String bestaandeDepZone;
	private String bestaandeArrZone;

	private Klant onbestaandeKlant;
	private Adres onbestaandeKlantAdres;
	private String onbestaandeDepZone;
	private String onbestaandeArrZone;

	@Before
	public void initialize() {
		korting = 5.00;
		prijs = 10.00;
		bestaandeDepZone = "Brussels-South/Brussels-Midi";
		bestaandeArrZone = "Brussels-North";
		onbestaandeDepZone = "brussel";
		onbestaandeArrZone = "brussel";
		bestaandeKlantAdres = AdresOpslaanInDB(new Adres("teststraatnaam", 170, "testwoonplaats", 1070, "6"));
		bestaandeKlant = KlantOpslaanInDB(new Klant(0, "testvoornaamAbonnement", "testachternaamAbonnement",
				"testemailAbonnement", bestaandeKlantAdres, "testinfoAbonnement", false));
		abonnement = new Abonnement(korting, prijs, VerkoopType.STANDAARD, bestaandeKlant, bestaandeDepZone,
				bestaandeArrZone);
	}

	@After
	public void terminate() {
		verwijderAbonnement(abonnement);
		verwijderKlant(bestaandeKlant);
		verwijderAdres(bestaandeKlantAdres);  
	}
	@Test
	public void testAddAbonnement() {
		AbonnementDAO.addAbonnement(abonnement);
		assertEquals(abonnement, abonnementOphalen(abonnement)); 
	} 
	private void verwijderAdres(Adres adres) {
		String adresVerwijderen= "DELETE FROM Adres "
				+ "WHERE straat = ? AND huisnr = ? AND woonplaats = ? AND postcode = ? AND bus = ?";
		java.sql.Connection connection = null;
		PreparedStatement stmt = null; 
		try {
			connection = Connection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(adresVerwijderen);
			
			stmt.setString(1, adres.getStraat());
			stmt.setString(3, adres.getWoonplaats());
			stmt.setString(5, adres.getBus());
			stmt.setInt(2, adres.getHuisnr());
			stmt.setInt(4, adres.getPostcode());

			stmt.executeUpdate();
			connection.commit(); 
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

	private void verwijderKlant(Klant klant) {
		String klantVerwijderen= "DELETE FROM Klant "
				+ "WHERE persoonId = ? AND info = ? AND actief = ?";

		java.sql.Connection connection = null;
		PreparedStatement stmt = null; 
		try {
			connection = Connection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(klantVerwijderen);
			
			stmt.setString(2, klant.getInfo());
			stmt.setInt(3, klant.isActief() ? 1 : 0);
			stmt.setInt(1, klant.getId());

			stmt.executeUpdate();
			connection.commit(); 
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
		verwijderPersoon(klant);
		
	}

	private void verwijderPersoon(Klant klant) {
		String persoonVerwijderen= "DELETE FROM Persoon "
				+ "WHERE adresId = ? AND voornaam = ? AND achternaam = ? AND email = ?";

		java.sql.Connection connection = null;
		PreparedStatement stmt = null; 
		try {
			connection = Connection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(persoonVerwijderen);

			stmt.setString(2, klant.getVoornaam());
			stmt.setString(3, klant.getAchternaam());
			stmt.setString(4, klant.getEmail());
			stmt.setInt(1, klant.getAdres().getAdresId());

			stmt.executeUpdate();
			connection.commit(); 
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

	private void verwijderAbonnement(Abonnement abonnement) {
		
		String abonementVerwijderen= "DELETE FROM Abonnement WHERE "
					+ "klantId = ? AND depZone = ? AND arrZone = ? AND prijs = ? AND verkoopType = ? AND korting = ? AND actief = ?";
		
		java.sql.Connection connection = null;
		PreparedStatement stmt = null; 
		try {
			connection = Connection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(abonementVerwijderen);
			
			stmt.setInt(1, abonnement.getKlant().getKlantId());
			stmt.setString(2, abonnement.getDepZone());
			stmt.setString(3, abonnement.getArrZone());
			stmt.setDouble(4, abonnement.getPrijs());
			stmt.setString(5, abonnement.getVerkoop().toString());
			stmt.setDouble(6, abonnement.getKorting());
			stmt.setInt(7, abonnement.isActief() ? 1 : 0); 

			stmt.executeUpdate();
			connection.commit(); 
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
	private Abonnement abonnementOphalen(Abonnement abonnement) {
		Abonnement dbAbonnement = null;
		String abonnementZoekenQuery = "SELECT  klantId , depZone , arrZone , prijs , verkoopType , korting , actief FROM  Abonnement "
				+ "WHERE "
				+ "klantId = ? AND depZone = ? AND arrZone = ? AND prijs = ? AND verkoopType = ? AND korting = ? AND actief = ?";
		java.sql.Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		try {
			connection = Connection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(abonnementZoekenQuery);

			stmt.setInt(1, abonnement.getKlant().getKlantId());
			stmt.setString(2, abonnement.getDepZone());
			stmt.setString(3, abonnement.getArrZone());
			stmt.setDouble(4, abonnement.getPrijs());
			stmt.setString(5, abonnement.getVerkoop().toString());
			stmt.setDouble(6, abonnement.getKorting());
			stmt.setInt(7, abonnement.isActief() ? 1 : 0);
			resultSet = stmt.executeQuery();
			if(resultSet.next()){
			dbAbonnement = abonnement;

			dbAbonnement.getKlant().setKlantId(resultSet.getInt(1));
			dbAbonnement.setDepZone(resultSet.getString(2));
			dbAbonnement.setArrZone(resultSet.getString(3));
			dbAbonnement.setPrijs(resultSet.getDouble(4));
			dbAbonnement.setVerkoop(VerkoopType.valueOf(resultSet.getString(5)));
			dbAbonnement.setKorting(resultSet.getDouble(6));
			dbAbonnement.setActief(resultSet.getInt(7) != 0 ? true : false);
			}else{ 
				dbAbonnement=null;
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
		return dbAbonnement;
	}

	private Adres AdresOpslaanInDB(Adres adres) {
		String AdresToevoegenQuery = "INSERT INTO Adres (straat, huisnr, woonplaats, postcode, bus) VALUES(?,?,?,?,?)";
		String AdresZoekenQuery = "SELECT adresId FROM Adres WHERE straat = ? AND huisnr = ? AND woonplaats = ? AND postcode = ? AND bus = ?";
		java.sql.Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		try {
			connection = Connection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(AdresToevoegenQuery);

			stmt.setString(1, adres.getStraat());
			stmt.setString(3, adres.getWoonplaats());
			stmt.setString(5, adres.getBus());
			stmt.setInt(2, adres.getHuisnr());
			stmt.setInt(4, adres.getPostcode());

			stmt.executeUpdate();
			connection.commit();
			stmt.close();

			stmt = connection.prepareStatement(AdresZoekenQuery);
			stmt.setString(1, adres.getStraat());
			stmt.setString(3, adres.getWoonplaats());
			stmt.setString(5, adres.getBus());
			stmt.setInt(2, adres.getHuisnr());
			stmt.setInt(4, adres.getPostcode());
			resultSet = stmt.executeQuery();
			resultSet.next();
			adres.setAdresId(resultSet.getInt(1));

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
		return adres;
	}

	private Klant KlantOpslaanInDB(Klant klant) {
		String persoonToevoegenQuery = "INSERT INTO Persoon (adresId, voornaam, achternaam, email) VALUES(?,?,?,?)";
		String persoonZoekenQuery = "SELECT persoonId FROM Persoon WHERE adresId = ? AND voornaam = ? AND achternaam = ? AND email = ?";

		String klantToevoegenQuery = "INSERT INTO Klant (persoonId, info, actief) VALUES(?,?,?)";
		String klantZoekenQuery = "SELECT klantId FROM Klant WHERE persoonId = ? AND info = ? AND actief = ?";
		java.sql.Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		try {
			connection = Connection.getDBConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(persoonToevoegenQuery);

			stmt.setString(2, klant.getVoornaam());
			stmt.setString(3, klant.getAchternaam());
			stmt.setString(4, klant.getEmail());
			stmt.setInt(1, klant.getAdres().getAdresId());

			stmt.executeUpdate();
			connection.commit();
			stmt.close();

			stmt = connection.prepareStatement(persoonZoekenQuery);
			stmt.setString(2, klant.getVoornaam());
			stmt.setString(3, klant.getAchternaam());
			stmt.setString(4, klant.getEmail());
			stmt.setInt(1, klant.getAdres().getAdresId());
			resultSet = stmt.executeQuery();
			resultSet.next();
			klant.setId(resultSet.getInt(1));
			resultSet.close();
			stmt.close();

			stmt = connection.prepareStatement(klantToevoegenQuery);
			stmt.setString(2, klant.getInfo());
			stmt.setInt(3, klant.isActief() ? 1 : 0);
			stmt.setInt(1, klant.getId());

			stmt.executeUpdate();
			connection.commit();
			stmt.close();

			stmt = connection.prepareStatement(klantZoekenQuery);
			stmt.setString(2, klant.getInfo());
			stmt.setInt(3, klant.isActief() ? 1 : 0);
			stmt.setInt(1, klant.getId());
			resultSet = stmt.executeQuery();
			resultSet.next();
			klant.setKlantId(resultSet.getInt(1));
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
		return klant;
	}


}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import source.Adres;
import source.Login;
import source.Medewerker;
import source.Persoon;
import source.Rol;

public class MedewerkerDAO {

	private static DBA dba = new DBA();

	private static java.sql.Connection connection;
	private static ResultSet data;
	private static PreparedStatement stmt = null;

	public static void addMedewerker(Login login, Persoon persoon, Rol rol, Adres adres) {
		int loginId = 0, persoonId = 0;

		loginId = LoginDao.addLogin(login);
		persoonId = PersoonDao.addPersoon(persoon);

		dba.createInsert("Medewerker");
		dba.addValue(loginId);
		dba.addValue(persoonId);
		dba.addValue(rol.getRolId());
		dba.addValue(1);
		dba.commit();
	}

	public static Medewerker getMedewerker(int id) {
		dba.createSelect("Medewerker");
		dba.addWhere("medewerkerId", id);
		ResultSet rs = dba.commit();
		try {
			if (rs.next()) {
				Persoon persoon = PersoonDao.getPersoon(rs.getInt(3));
				return new Medewerker(persoon.getId(), persoon.getVoornaam(), persoon.getAchternaam(),
						persoon.getEmail(), persoon.getAdres(), rs.getInt(1), RolDAO.getRol(rs.getInt(4)),
						LoginDao.getLogin(rs.getInt(2)), rs.getBoolean(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Medewerker getMedewerkerByPersoonId(int id) {
		dba.createSelect("Medewerker");
		dba.addWhere("persoonId", id);
		dba.addWhere("actief", 1);
		ResultSet rs = dba.commit();
		try {
			if (rs.next()) {
				Persoon persoon = PersoonDao.getPersoon(id);
				return new Medewerker(persoon.getId(), persoon.getVoornaam(), persoon.getAchternaam(),
						persoon.getEmail(), persoon.getAdres(), rs.getInt(1), RolDAO.getRol(rs.getInt(4)),
						LoginDao.getLogin(rs.getInt(2)), rs.getBoolean(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Medewerker getMedewerkerByLogin(int id) {
		dba.createSelect("Medewerker");
		dba.addWhere("loginId", id);
		ResultSet rs = dba.commit();
		try {
			if (rs.next()) {
				Persoon persoon = PersoonDao.getPersoon(rs.getInt(3));
				return new Medewerker(persoon.getId(), persoon.getVoornaam(), persoon.getAchternaam(),
						persoon.getEmail(), persoon.getAdres(), rs.getInt(1), RolDAO.getRol(rs.getInt(4)),
						LoginDao.getLogin(rs.getInt(2)), rs.getBoolean(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static int getMedewerkerIdByUsername(String username) {
		int loginId = 0;
		dba.createSelect("Login", "loginId");
		dba.addWhere("username", username);
		ResultSet rs = dba.commit();
		try {
			if (rs.next()) {
				loginId = rs.getInt(1);
			}

			rs.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		dba.createSelect("Medewerker", "medewerkerId");
		dba.addWhere("loginId", loginId);
		rs = dba.commit();
		try {
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public static ArrayList<Medewerker> getAllMedewerkers() {
		ArrayList<Medewerker> medewerkers = new ArrayList<Medewerker>();
		Statement stmt;
		ResultSet rs = null;
		String sql = "SELECT * FROM Medewerker m JOIN Login l ON m.loginId = l.loginId JOIN Persoon p ON m.persoonId = p.persoonId"
				+ " JOIN Rol r ON m.rolId = r.rolId JOIN Adres a ON p.adresId = a.adresId WHERE actief = true;";

		Connection conn = dao.Connection.getDBConnection();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Persoon persoon;
			while (rs.next()) {
				persoon = new Persoon(rs.getInt(9), rs.getString(11), rs.getString(12), rs.getString(13),
						new Adres(rs.getInt(16), rs.getString(17), rs.getInt(18), rs.getString(19), rs.getInt(20),
								rs.getString(21)));
				medewerkers.add(new Medewerker(persoon.getId(), persoon.getVoornaam(), persoon.getAchternaam(),
						persoon.getEmail(), persoon.getAdres(), rs.getInt(1), new Rol(rs.getInt(14), rs.getString(15)),
						new Login(rs.getInt(6), rs.getString(7), rs.getString(8)), rs.getBoolean(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 Collections.sort(medewerkers, new Comparator<Medewerker>() {

	            @Override
	            public int compare(Medewerker m1, Medewerker m2) {
	                if (m1.getVoornaam().toLowerCase().equals(m2.getVoornaam().toLowerCase())) {
	                    return 0;
	                }
	                return m1.getVoornaam().toLowerCase().compareTo(m2.getVoornaam().toLowerCase());
	            }
	        });
		
		return medewerkers;
		/*
		 * ArrayList<Medewerker> medewerkers = new ArrayList<Medewerker>();
		 * Persoon persoon = null; dba.createSelect("Medewerker");
		 * dba.addWhere("actief", true); ResultSet rs = dba.commit(); try {
		 * while (rs.next()) { persoon = PersoonDao.getPersoon(rs.getInt(3));
		 * medewerkers.add(new Medewerker(persoon.getId(),
		 * persoon.getVoornaam(), persoon.getAchternaam(), persoon.getEmail(),
		 * persoon.getAdres(), rs.getInt(1), RolDAO.getRol(rs.getInt(4)),
		 * LoginDao.getLogin(rs.getInt(2)), true)); }
		 * 
		 * Collections.sort(medewerkers, new Comparator<Medewerker>() {
		 * 
		 * @Override public int compare(Medewerker m1, Medewerker m2) { if
		 * (m1.getVoornaam().toLowerCase().equals(m2.getVoornaam().toLowerCase()
		 * )) { return 0; } return
		 * m1.getVoornaam().toLowerCase().compareTo(m2.getVoornaam().toLowerCase
		 * ()); } });
		 * 
		 * return medewerkers; } catch (SQLException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); } return null
		 */
	}

	public static ArrayList<Medewerker> getAllMedewerkersFromSearch(String search) {
		ArrayList<Medewerker> medewerkers = new ArrayList<Medewerker>();
		Persoon persoon = null;
		String search2 = "%" + search + "%";
		try {
			connection = dao.Connection.getDBConnection();
			stmt = connection.prepareStatement("SELECT * FROM Persoon where Voornaam LIKE ?");
			stmt.setString(1, search2);
			data = stmt.executeQuery();

			while (data.next()) {
				persoon = PersoonDao.getPersoon(data.getInt(1));
				Medewerker medewerker = MedewerkerDAO.getMedewerkerByPersoonId(persoon.getId());
				medewerkers.add(medewerker);
				System.out.println(medewerkers);
			}
			data.close();
			connection.close();
			return medewerkers;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			try {
				if (data != null) {
					data.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException se2) {
				se2.printStackTrace();
			}
		}
		return medewerkers;
	}

	public static void removeMedewerker(int id) {
		dba.createUpdate("Medewerker", "actief", 0);
		;
		dba.addWhere("medewerkerId", id);
		dba.commit();

	}

	public static void bijwerkenMedewerker(int medewerkerId, int persoonId, Persoon persoon, Rol rol, int adresId,
			Adres adres) {

		try {
			connection = dao.Connection.getDBConnection();

			stmt = connection
					.prepareStatement("UPDATE Persoon SET voornaam=?, achternaam=?, email=? WHERE persoonId=?");
			stmt.setString(1, persoon.getVoornaam());
			stmt.setString(2, persoon.getAchternaam());
			stmt.setString(3, persoon.getEmail());
			stmt.setInt(4, persoonId);
			stmt.executeUpdate();

			stmt = connection.prepareStatement(
					"UPDATE Adres SET straat=?, huisnr=?, woonplaats=?, postcode=?, bus=? WHERE adresId=?");
			stmt.setString(1, adres.getStraat());
			stmt.setInt(2, adres.getHuisnr());
			stmt.setString(3, adres.getWoonplaats());
			stmt.setInt(4, adres.getPostcode());
			stmt.setString(5, adres.getBus());
			stmt.setInt(6, adresId);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {// Handle errors for Class.forName
			e.printStackTrace();
		}

		dba.createUpdate("Medewerker", "rolId", rol.getRolId());
		;
		dba.addWhere("medewerkerId", medewerkerId);
		dba.commit();

	}
}
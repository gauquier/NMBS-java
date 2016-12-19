package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
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
	private static Statement command;
	private static ResultSet data;
	private static PreparedStatement stmt = null;
	
	public static void addMedewerker(Login login, Persoon persoon, Rol rol, Adres adres){
		int loginId =  0, persoonId = 0;
		
		loginId = LoginDao.addLogin(login);
        persoonId = PersoonDao.addPersoon(persoon);
        
		dba.createInsert("Medewerker");
		dba.addValue(loginId);
		dba.addValue(persoonId);
		dba.addValue(rol.getRolId());
		dba.addValue(1);
		dba.commit();
	}
	public static int addMedewerker(Medewerker medewerker){
		
		if(getMedewerkerId(medewerker) == 0){
			dba.createInsert("Medewerker");
			dba.addValue(LoginDao.addLogin(medewerker.getLogin()));
			dba.addValue(PersoonDao.addPersoon(new Persoon(0, medewerker.getVoornaam(), medewerker.getAchternaam(), medewerker.getEmail(), medewerker.getAdres())));
			dba.addValue(RolDAO.addRol(medewerker.getRol()));
			dba.addValue(true);
			dba.commit();
		}
		
		return getMedewerkerId(medewerker);
	}
	
	public static int getMedewerkerId(Medewerker medewerker){
		dba.createSelect("Medewerker", "medewerkerId");
		dba.addWhere("loginId", LoginDao.getLoginId(medewerker.getLogin()));
		dba.addWhere("persoonId", PersoonDao.getPersoonId(new Persoon(0, medewerker.getVoornaam(), medewerker.getAchternaam(), medewerker.getEmail(), medewerker.getAdres())));
		dba.addWhere("rolId", RolDAO.getRolId(medewerker.getRol()));
		dba.addWhere("actief", true);
		ResultSet rs = dba.commit();
		try {
			if(rs.next()){
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public static Medewerker getMedewerker(int id){
		dba.createSelect("Medewerker");
		dba.addWhere("medewerkerId", id);
		ResultSet rs = dba.commit();
		try {
			if(rs.next()){
				Persoon persoon = PersoonDao.getPersoon(rs.getInt(3));
				return new Medewerker(persoon.getId(), persoon.getVoornaam(), persoon.getAchternaam(), persoon.getEmail(), persoon.getAdres(),
						rs.getInt(1), RolDAO.getRol(rs.getInt(4)), LoginDao.getLogin(rs.getInt(2)), rs.getBoolean(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Medewerker getMedewerkerByPersoonId(int id){
		dba.createSelect("Medewerker");
		dba.addWhere("persoonId", id);
		ResultSet rs = dba.commit();
		try {
			if(rs.next()){
				Persoon persoon = PersoonDao.getPersoon(id);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static Medewerker getMedewerkerByLogin(int id){
		dba.createSelect("Medewerker");
		dba.addWhere("loginId", id);
		ResultSet rs = dba.commit();
		try {
			if(rs.next()){
				Persoon persoon = PersoonDao.getPersoon(rs.getInt(3));
				return new Medewerker(persoon.getId(), persoon.getVoornaam(), persoon.getAchternaam(), persoon.getEmail(), persoon.getAdres(),
						rs.getInt(1), RolDAO.getRol(rs.getInt(4)), LoginDao.getLogin(rs.getInt(2)), rs.getBoolean(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static int getMedewerkerIdByUsername(String username){
		int loginId = 0;
		dba.createSelect("Login", "loginId");
		dba.addWhere("username", username);
		ResultSet rs = dba.commit();
		try {
			if(rs.next())
				loginId = rs.getInt(1);
			
			rs.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		dba.createSelect("Medewerker", "medewerkerId");
		dba.addWhere("loginId", loginId);
		rs = dba.commit();
		try {
			if(rs.next()){
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public static ArrayList<Medewerker> getAllMedewerkers(){
		ArrayList<Medewerker> medewerkers = new ArrayList<Medewerker>();
		Persoon persoon = null;
		dba.createSelect("Medewerker");
		dba.addWhere("actief", true);
		ResultSet rs = dba.commit();
		try {
			while(rs.next()){
				persoon = PersoonDao.getPersoon(rs.getInt(3));
				medewerkers.add(new Medewerker(persoon.getId(), persoon.getVoornaam(), persoon.getAchternaam(), persoon.getEmail(), persoon.getAdres(),
						rs.getInt(1), RolDAO.getRol(rs.getInt(4)), LoginDao.getLogin(rs.getInt(2)), true));
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Medewerker> getAllMedewerkersFromSearch(String search){
		ArrayList<Medewerker> medewerkers = new ArrayList<Medewerker>();
		Persoon persoon = null;
		String search2 = "%"+search+"%";
		dba.createSelect("Persoon");
		dba.addWhereLike("Voornaam", search2);
		ResultSet rs2 = dba.commit();
		try {
			while(rs2.next()){
				persoon = PersoonDao.getPersoon(rs2.getInt(1));
				Medewerker medewerker = MedewerkerDAO.getMedewerkerByPersoonId(persoon.getId());
				medewerkers.add(new Medewerker(persoon.getId(), persoon.getVoornaam(), persoon.getAchternaam(), persoon.getEmail(), persoon.getAdres(),
						medewerker.getMedewerkerId() , medewerker.getRol(), medewerker.getLogin(), true));
				System.out.println(persoon);
			}
			return medewerkers;
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void removeMedewerker(int id){
		dba.createUpdate("Medewerker", "actief", 0);;
		dba.addWhere("medewerkerId", id);
		ResultSet rs = dba.commit();
		
	}
	
	public static void bijwerkenMedewerker(int medewerkerId, int persoonId, Persoon persoon, Rol rol, int adresId, Adres adres){ 
		
	    try {
	        connection = Connection.getDBConnection();    
	       
	        stmt = connection.prepareStatement("UPDATE Persoon SET voornaam=?, achternaam=?, email=? WHERE persoonId=?");
	        stmt.setString(1,persoon.getVoornaam());
	        stmt.setString(2, persoon.getAchternaam());
	        stmt.setString(3, persoon.getEmail());
	        stmt.setInt(4, persoonId);
	        stmt.executeUpdate();
	        
	        
	        stmt = connection.prepareStatement("UPDATE Adres SET straat=?, huisnr=?, woonplaats=?, postcode=?, bus=? WHERE adresId=?");
	        stmt.setString(1,adres.getStraat());
	        stmt.setInt(2, adres.getHuisnr());
	        stmt.setString(3, adres.getWoonplaats());
	        stmt.setInt(4, adres.getPostcode());
	        stmt.setString(5, adres.getBus());
	        stmt.setInt(6, adresId);
	        stmt.executeUpdate();
	       
	    
	    }catch (SQLException e){
	        e.printStackTrace();
	    }catch(Exception e) {//Handle errors for Class.forName
	        e.printStackTrace();
	    }
		
	    
	    dba.createUpdate("Medewerker", "rolId", rol.getRolId());;
		dba.addWhere("medewerkerId", medewerkerId);
		ResultSet rs = dba.commit();
	    
	    
	}
	
	
}
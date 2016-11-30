package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import source.Adres;
import source.Login;
import source.Medewerker;
import source.Persoon;
import source.Rol;

public class MedewerkerDAO {
	
	private static DBA dba = new DBA();
	
	
	//klopt niet echt slechts invoegen van login en persoon niet rol + adres is niet nodig zit reeds in persoon + beter om alleen Medewerker mee te vragen
	public static void addMedewerker(Login login, Persoon persoon, Rol rol, Adres adres){
		int loginId =  0, persoonId = 0;
		
		loginId = LoginDao.addLogin(login);
        persoonId = PersoonDao.addPersoon(persoon);
        
		dba.createInsert("Medewerker");
		dba.addValue(loginId);
		dba.addValue(persoonId);
		dba.addValue(rol.getRolId());
		dba.addValue(0);
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
			return medewerkers;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		ArrayList<Medewerker> medewerkers = getAllMedewerkers();
		for(int i = 0; i < medewerkers.size(); i++){
			System.out.println(medewerkers.get(i).toString());
		}
	}
}












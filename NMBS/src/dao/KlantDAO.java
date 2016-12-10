package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


import source.Adres;
import source.Klant;
import source.Login;
import source.Medewerker;
import source.Persoon;
import source.Rol;

public class KlantDAO {
	
	private static DBA dba = new DBA();
	
	private static java.sql.Connection connection;
	private static Statement command;
	private static ResultSet data;
	private static PreparedStatement stmt = null;

	public static void addKlant(Persoon persoon, Adres adres, String info){
		int persoonId = 0;
		
        persoonId = PersoonDao.addPersoon(persoon);
        
		dba.createInsert("Klant");
		dba.addValue(persoonId);
		dba.addValue(info);
		dba.addValue(1);
		dba.commit();
	}
	
	public static ArrayList<Klant> getAllKlanten(){
		ArrayList<Klant> klanten = new ArrayList<Klant>();
		Persoon persoon = null;
		dba.createSelect("Klant");
		dba.addWhere("actief", true);
		ResultSet rs = dba.commit();
		try {
			while(rs.next()){
				persoon = PersoonDao.getPersoon(rs.getInt(2));
				klanten.add(new Klant(persoon.getId(), persoon.getVoornaam(), persoon.getAchternaam(), persoon.getEmail(), persoon.getAdres(),rs.getInt(1),rs.getString(3) , true));
			}
			
			
			return klanten;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}

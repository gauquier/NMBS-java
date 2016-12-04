package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import source.Adres;
import source.Persoon;


public class PersoonDao {
	
	private static DBA dba = new DBA();
	private static AdresDAO adresDAO;
	
	public static int addPersoon(Persoon persoon, Adres adres){
			dba.createInsert("Persoon");
			dba.addValue(AdresDAO.insertAdres(adres));
			dba.addValue(persoon.getVoornaam());
			dba.addValue(persoon.getAchternaam());
			dba.addValue(persoon.getEmail());
			dba.commit();
		return getPersoonId(persoon, adres);
	}
	
	public static int getPersoonId(Persoon persoon, Adres adres){
		
		dba.createSelect("Persoon", "persoonId");
		dba.addWhere("adresId", adresDAO.getId(adres)); 
		dba.addWhere("voornaam", persoon.getVoornaam());
		dba.addWhere("achternaam", persoon.getAchternaam());
		dba.addWhere("email", persoon.getEmail());
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
	
	public static boolean persoonWijzigen(Persoon persoon) throws Exception{
		
		return false;
	}
	public static Persoon zoekPersoonOpPersoonId(int persoonId) {
		return null;
	}
	public static Persoon zoekPersoonOpPersoonId(Persoon medewerker) { 
		return null;
	}
	
}

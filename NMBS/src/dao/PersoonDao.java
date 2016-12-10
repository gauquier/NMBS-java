package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import source.Adres;
import source.Persoon;


public class PersoonDao {
	
	private static DBA dba = new DBA();
	
	public static int addPersoon(Persoon persoon){
			dba.createInsert("Persoon");
			dba.addValue(AdresDAO.insertAdres(persoon.getAdres()));
			dba.addValue(persoon.getVoornaam());
			dba.addValue(persoon.getAchternaam());
			dba.addValue(persoon.getEmail());
			dba.commit();
		return getPersoonId(persoon);
	}
	
	public static int getPersoonId(Persoon persoon){
		
		dba.createSelect("Persoon", "persoonId");
		dba.addWhere("adresId", AdresDAO.getId(persoon.getAdres())); 
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
	public static Persoon getPersoon(int id){
		Persoon persoon = null;
		dba.createSelect("Persoon");
		dba.addWhere("persoonId",id);
		ResultSet rs = dba.commit();
		try {
			if(rs.next()){
				return persoon = new Persoon(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5), AdresDAO.getAdres(rs.getInt(2)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public int checkEmail(String email){
		dba.createSelect("Persoon", "persoonId");
		dba.addWhere("email", email);
		ResultSet rs = dba.commit();
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
	
	public static boolean persoonWijzigen(Persoon persoon) throws Exception{
		
		return false;
	}
	public static Persoon zoekPersoonOpPersoonId(int persoonId) {
		return null;
	}
	public static Persoon zoekPersoonOpPersoonId(Persoon medewerker) { 
		return null;
	}
	public static Persoon persoonToevoegen(Persoon persoon) {
		return null;
	}
}

package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import source.Adres;
import source.Login;
import source.Medewerker;
import source.Persoon;

public class AdresDAO {

	private static DBA dba = new DBA();
	
	private static void insertAdres(String straat, int huisnr, String woonplaats, int postcode, int bus){//kan ook Adres adres
		dba.createInsert("Adres");
		dba.addValue(straat);
		dba.addValue(huisnr);
		dba.addValue(woonplaats);
		dba.addValue(postcode);
		dba.addValue(bus);
		dba.commit();
	}
	
	public static int insertAdres(Adres adres){
		dba.createInsert("Adres");
		dba.addValue(adres.getStraat());
		dba.addValue(adres.getHuisnr());
		dba.addValue(adres.getWoonplaats());
		dba.addValue(adres.getPostcode());
		dba.addValue(adres.getBus());
		dba.commit();
		
		return getId(adres);
	}

	public static Adres getAdres(int id){
		Adres adres = null;
		dba.createSelect("Adres");
		dba.addWhere("adresId", id);
		ResultSet rs = dba.commit();
		try {
			if(rs.next())
			{
				adres = new Adres(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getString(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adres;
	}
	public static int getId(Adres adres){
		dba.createSelect("Adres", "adresId");
		dba.addWhere("straat", adres.getStraat());
		dba.addWhere("huisnr", adres.getHuisnr());
		dba.addWhere("woonplaats", adres.getWoonplaats());
		dba.addWhere("postcode", adres.getPostcode());
		dba.addWhere("bus", adres.getBus());
		ResultSet rs = dba.commit();
		try {
			if(rs.next())
			{
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	
	
	
	

	
}

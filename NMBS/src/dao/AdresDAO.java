package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import source.Adres;

public class AdresDAO {

	private DBA dba = new DBA();
	
	private void insertAdres(String straat, int huisnr, String woonplaats, int postcode, int bus){//kan ook Adres adres
		dba.createInsert("Adres");
		dba.addValue(straat);
		dba.addValue(huisnr);
		dba.addValue(woonplaats);
		dba.addValue(postcode);
		dba.addValue(bus);
		dba.commit();
	}
	
	public int insertAdres(Adres adres){
		if(getId(adres) == 0)
		{
			insertAdres(adres.getStraat(), adres.getHuisnr(), adres.getWoonplaats(), adres.getPostcode(), adres.getBus());
		}
		return getId(adres);
	}

	public Adres getAdres(int id){
		Adres adres = null;
		dba.createSelect("Adres");
		dba.addWhere("adresId", id);
		ResultSet rs = dba.commit();
		try {
			if(rs.next())
			{
				adres = new Adres(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5),(char) rs.getInt(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adres;
	}
	public int getId(Adres adres){
		dba.createSelect("Test", "adresId");
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

	public void setNonActief(){
		//geen variabele actief in tabel Adres.
	}

}

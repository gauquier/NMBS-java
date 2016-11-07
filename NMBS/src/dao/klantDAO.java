package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import source.Klant;

public class klantDAO {

	private DBA dba = new DBA();
	
	public void insertKlant(){//kan ook Adres adres
		dba.createInsert("Klant");
		dba.addValue(straat);
		dba.addValue(huisnr);
		dba.addValue(woonplaats);
		dba.addValue(postcode);
		dba.addValue(bus);
		dba.commit();
	}
	
	public void insertKlant(Klant klant){
		
	}

	public Klant getKlant(int id){
		Klant klant = null;
		dba.createSelect("Klant");
		dba.addWhere("id", id);
		ResultSet rs = dba.commit();
		try {
			if(rs.next())
			{
				klant = new Klant
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return klant;
	}

	public void setNonActief(){
		//geen variabele actief in tabel Adres.
	}

}

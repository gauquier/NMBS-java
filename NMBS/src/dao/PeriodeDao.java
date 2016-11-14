package dao;


import java.sql.ResultSet;
import java.sql.SQLException;


import source.Periode;

//VERY MUCH WORK-IN-PROGRESS!!!! NIET GEBRUIKEN. DO NOT USE. -Boris
public class PeriodeDAO {


	private DBA dba = new DBA();
	
	public int insertPeriode(Periode periode){
		if(getId(periode) == 0){
			dba.createInsert("Periode");
			dba.addValue(adresDAO.insertAdres(persoon.getAdres()));
			dba.addValue(persoon.getVoornaam());
			dba.addValue(persoon.getAchternaam());
			dba.addValue(persoon.getEmail());
			dba.commit();
		}
		return getId(persoon);
	}
	
	public int getId(Periode periode){
		
		dba.createSelect("Periode", "periodeId");
		dba.addWhere("voornaam", periode.getStartDate());
		dba.addWhere("achternaam", periode.getStartDate());
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
	public Persoon getPersoon(int id){
		Persoon persoon = null;
		dba.createSelect("Persoon");
		dba.addWhere("persoonId", id);
		ResultSet rs = dba.commit();	
		try {
			if(rs.next()){
				persoon = new Persoon(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5), adresDAO.getAdres(rs.getInt(2)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return persoon;
	}


}
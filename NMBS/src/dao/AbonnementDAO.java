package dao;


import java.sql.ResultSet;
import java.sql.SQLException;


import source.Abonnement;


public class AbonnementDAO {


	private DBA dba = new DBA();
	private AdresDAO adresDAO;
	
	public int insertPersoon(Persoon persoon){
		if(getId(persoon) == 0){
			dba.createInsert("Persoon");
			dba.addValue(adresDAO.insertAdres(persoon.getAdres()));
			dba.addValue(persoon.getVoornaam());
			dba.addValue(persoon.getAchternaam());
			dba.addValue(persoon.getEmail());
			dba.commit();
		}
		return getId(persoon);
	}
	
	public int getId(Persoon persoon){
		
		dba.createSelect("Persoon", "persoonId");
		dba.addWhere("adresId", adresDAO.getId(persoon.getAdres()));
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
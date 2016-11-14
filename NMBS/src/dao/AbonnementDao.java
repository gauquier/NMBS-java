package dao;


import java.sql.ResultSet;
import java.sql.SQLException;


import source.Abonnement;

//WORK-IN-PROGRESS! NIET GEBRUIKEN. DO NOT USE. -Boris
public class AbonnementDAO {


	private DBA dba = new DBA();
	
	public int insertAbonnement(Abonnement abonnement){
		if(getId(abonnement) == 0){
			dba.createInsert("Abonnement");
			dba.addValue(abonnement.getKlantId());
			dba.addValue(abonnement.getDepZone());
            dba.addValue(abonnement.getArrZone());
            dba.addValue(abonnement.getPrijs());
            dba.addValue(abonnement.getSoort());
            dba.addValue(abonnement.getKorting());
            dba.addValue(abonnement.getActief());
			dba.commit();
		}
		return getId(persoon);
	}

	public int getId(Abonnement abonnement){
		
		dba.createSelect("Abonnement", "abonnementId");
		dba.addWhere("klantId", abonnement.getKlantId());//een Klant mag maar 1 Abonnement hebben
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

    //Abonnement(int aankoopId, int klantId, String depZone, String arrZone, double prijs, VerkoopType verkoop, double korting, boolean actief)

	public Abonnement getAbonnement(int id){
        Abonnement abonnement = null;
		dba.createSelect("Abonnement");
		dba.addWhere("abonnementId", id);
		ResultSet rs = dba.commit();	
		try {
			if(rs.next()){
                abonnement = new Abonnement(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getString(6), rs.getInt(7), rs.getBoolean(8)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return abonnement;
	}


}
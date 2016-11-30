package dao;

import source.Adres;
import source.Login;
import source.Medewerker;
import source.Persoon;
import source.Rol;

public class MedewerkerDAO {
	
	private static DBA dba = new DBA();
	
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
}
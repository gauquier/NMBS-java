package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import source.Adres;
import source.Login;
import source.Medewerker;
import source.Persoon;
import source.Rol;

public class MedewerkerDAO {
	
	private static DBA dba = new DBA();
	
	private static java.sql.Connection connection;
	private static Statement command;
	private static ResultSet data;
	private static PreparedStatement stmt = null;
	
	public static void addMedewerker(Login login, Persoon persoon, Rol rol, Adres adres){
		int loginId =  0, persoonId = 0;
		
		loginId = LoginDao.addLogin(login);
        persoonId = PersoonDao.addPersoon(persoon, adres);
        
		dba.createInsert("Medewerker");
		dba.addValue(loginId);
		dba.addValue(persoonId);
		dba.addValue(Medewerker.getRol().getRolId());
		dba.addValue(0);
		dba.commit();
	}
	
	
}
package handler;

import gui.*;
import dao.CheckIfConnected;
import dao.DBA;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.UIManager;

public class Controller extends LoginGui{
	
	public static AdminGui adminInterface;
	public static MedewerkerGui medewerkerInterface;
	public static OfflineGui offlineInterface;
	
	public static void main(String[] args) throws Exception {
		runProgram();
		/*DBA dba = new DBA();
		
		dba.createInsert("Test");
		dba.addValue("jonas");
		dba.addValue(47.88);
		dba.commit();
		
		dba.createSelect("Test");
		dba.addWhere("naam",
		 "jonas");
		ResultSet rs = dba.commit();
		if(rs.next())
			System.out.println(rs.getString(1) +" " + rs.getString(2) + " " + rs.getString(3));
		
		dba.createUpdate("Test", "getal", 17.44);
		dba.addWhere("naam", "jonas");
		dba.commit();
		
		dba.createSelect("Test");
		dba.addWhere("naam", "jonas");
		rs = dba.commit();
		if(rs.next())
			System.out.println(rs.getString(1) +" " + rs.getString(2) + " " + rs.getString(3));*/
    }
	public static void runProgram(){
		LoginGui.start();						
	}
}
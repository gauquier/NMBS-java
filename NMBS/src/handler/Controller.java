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
    }
	public static void runProgram(){
		LoginGui.start();						
	}
}
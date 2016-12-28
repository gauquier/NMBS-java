package handler;

import gui.AdminGui;
import gui.LoginGui;
import gui.MedewerkerGui;
import gui.OfflineGui;

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
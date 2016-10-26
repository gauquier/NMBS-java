package handler;

import gui.*;

public class Controller extends LoginGui{
	
	public static AdminGui adminInterface;
	
	public static void main(String[] args) throws Exception {
		runProgram();
    }
	public static void runProgram(){
		LoginGui.start();						
	}
}
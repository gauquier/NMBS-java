package dao;

import java.io.IOException;

public class CheckIfConnected {
	
	
	public static boolean checkIfConnected() {
		//Gebaseerd op http://stackoverflow.com/questions/9922543/why-does-inetaddress-isreachable-return-false-when-i-can-ping-the-ip-address
		Process p1;
		try {
			p1 = java.lang.Runtime.getRuntime().exec("ping -w 1 www.google.com");
			//"-w 1" to make it not unbearably slow on Linux (tested in Ubuntu 16.04.1) when connected to the Internet
			//and slightly faster on Windows, too
			int returnVal = p1.waitFor();
			boolean reachable = (returnVal==0);
			return reachable;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}

package dao;

import java.io.IOException;

public class CheckIfConnected {
	private static String os = System.getProperty("os.name").toLowerCase();
	
	public static boolean checkIfConnected() {
		
		//Gebaseerd op http://stackoverflow.com/questions/9922543/why-does-inetaddress-isreachable-return-false-when-i-can-ping-the-ip-address
		Process p1;
		try {
			if (os.substring(0, 7).equals("windows") || os.equals("linux"))
			{
				p1 = java.lang.Runtime.getRuntime().exec("ping -w 1 www.google.com");
				//"-w 1" to make it not unbearably slow on Linux (tested in Ubuntu 16.04.1) when connected to the Internet
				//and slightly faster on Windows, too
				System.out.println("windows or linux?: " + os);
			}
			else
			{
				if (os.substring(0, 3).equals("mac")) {
					p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");
					System.out.println("is this mac?: " + os);
				}
				else {
					p1 = java.lang.Runtime.getRuntime().exec("ping www.google.com");
					System.out.println("not windows, linux or linux?: " + os);
				}
			}
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
	
	 private static String OS = System.getProperty("os.name").toLowerCase();

	    public static void detectOS() {
	        if (isWindows()) {
	        	os = "windows";
	        } else if (isMac()) {
	        	os = "mac";
	        } else if (isUnix()) {
	        	os = "linux"; 
	        } 
	    }

	    private static boolean isWindows() {
	        return (OS.indexOf("win") >= 0);
	    }

	    private static boolean isMac() {
	        return (OS.indexOf("mac") >= 0);
	    }

	    private static boolean isUnix() {
	        return (OS.indexOf("nux") >= 0);
	    }
}

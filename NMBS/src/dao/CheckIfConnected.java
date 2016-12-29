package dao;

import java.io.IOException;

public class CheckIfConnected {
	private static String os = System.getProperty("os.name").toLowerCase();
	private static boolean windows = false;
	private static boolean linux = false;
	private static boolean mac = false;

	public static boolean checkIfConnected() {

		// Gebaseerd op
		// http://stackoverflow.com/questions/9922543/why-does-inetaddress-isreachable-return-false-when-i-can-ping-the-ip-address
		Process p1;
		try {
			// ik doe de check op deze manier om een out of bounds exception te
			// verkomen bij kortere namen (bv. "windows 10" vs "linux")
			if (os.length() > 6) {
				if (os.substring(0, 7).equals("windows")) {
					windows = true;
				}
			}
			if (os.length() > 5) {// voorkom out of bounds exception bij kortere
									// naam
				if (os.substring(0, 6).equals("linux")) {
					linux = true;
				}
			}
			if (os.length() > 2) {// voorkom out of bounds exception bij kortere
									// naam
				if (os.substring(0, 3).equals("mac")) {
					mac = true;
				}
			}
			if (windows || linux) {
				p1 = java.lang.Runtime.getRuntime().exec("ping -w 1 www.google.com");
				// "-w 1" to make it not unbearably slow on Linux (tested in
				// Ubuntu 16.04.1) when connected to the Internet
				// and slightly faster on Windows, too
			} else {
				if (mac) {
					p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");
				} else {
					p1 = java.lang.Runtime.getRuntime().exec("ping www.google.com");
				}
			}
			int returnVal = p1.waitFor();
			boolean reachable = (returnVal == 0);
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

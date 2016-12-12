package source;

import java.util.Date;;


public class VerlorenVoorwerp {

	private int verlorenVoorwerpId;
	private String beschrijving;
	private Date date;
	private boolean gevonden;

	public VerlorenVoorwerp(int verlorenVoorwerpId, String beschrijving,Date date, boolean gevonden) {
		this.verlorenVoorwerpId = verlorenVoorwerpId;
		this.beschrijving = beschrijving;
		this.date = date;
		this.gevonden = gevonden;	
	}
	

	public int getVerlorenVoorwerpId() {
		return verlorenVoorwerpId;
	}

	public void setVerlorenVoorwerpId(int verlorenVoorwerpId) {
		this.verlorenVoorwerpId = verlorenVoorwerpId;
	}

	public String getBeschrijving() {
		return beschrijving;
	}

	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean getGevonden() {
		return gevonden;
	}

	public void setGevonden(boolean gevonden) {
		this.gevonden = gevonden;
	}
	public static String padRight(String s, int n) {
	     return String.format("%1$-" + n + "s", s);  
	}

	@Override
	public String toString() {
		
		return date + "      " + beschrijving + padRight( "", 10) + gevonden; 
	}

}

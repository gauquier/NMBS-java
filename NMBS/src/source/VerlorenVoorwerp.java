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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beschrijving == null) ? 0 : beschrijving.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VerlorenVoorwerp other = (VerlorenVoorwerp) obj;
		if (beschrijving == null) {
			if (other.beschrijving != null)
				return false;
		} else if (!beschrijving.equals(other.beschrijving))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}

}

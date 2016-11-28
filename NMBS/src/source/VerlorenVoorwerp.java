package source;

import java.sql.Date;

public class VerlorenVoorwerp {

	private int verlorenVoorwerpId;
	private String beschrijving;
	private Date date;
	

	private boolean gevonden;

	public VerlorenVoorwerp(int 

verlorenVoorwerpId, String beschrijving, Date 

date, boolean gevonden) {
		super();
		this.verlorenVoorwerpId = 

verlorenVoorwerpId;
		this.beschrijving = 

beschrijving;
		this.date = date;
		this.gevonden = gevonden;
	}


	public int getVerlorenVoorwerpId() {
		return verlorenVoorwerpId;
	}

	public void setVerlorenVoorwerpId(int 

verlorenVoorwerpId) {
		this.verlorenVoorwerpId = 

verlorenVoorwerpId;
	}

	public String getBeschrijving() {
		return beschrijving;
	}

	public void setBeschrijving(String 

beschrijving) {
		this.beschrijving = 

beschrijving;
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

	public void setGevonden(boolean 

gevonden) {
		this.gevonden = gevonden;
	}
	
	@Override
	public String toString() {
		return "VerlorenVoorwerp [verlorenVoorwerpId=" + verlorenVoorwerpId + ", beschrijving=" + beschrijving
				+ ", date=" + 

date + ", gevonden=" + gevonden + "]";
	}

}

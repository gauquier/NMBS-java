package source;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VerlorenVoorwerp {

	private int verlorenVoorwerpId;
	private String beschrijving;
	private Date date;
	private boolean gevonden;

	public VerlorenVoorwerp(int verlorenVoorwerpId, String beschrijving, Date date, boolean gevonden) {
		this.verlorenVoorwerpId = verlorenVoorwerpId;
		this.beschrijving = beschrijving;
		this.date = date;
		this.gevonden = gevonden;
	}

	public int getVerlorenVoorwerpId() {
		return this.verlorenVoorwerpId;
	}

	public void setVerlorenVoorwerpId(int verlorenVoorwerpId) {
		this.verlorenVoorwerpId = verlorenVoorwerpId;
	}

	public String getBeschrijving() {
		return this.beschrijving;
	}

	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean getGevonden() {
		return this.gevonden;
	}

	public void setGevonden(boolean gevonden) {
		this.gevonden = gevonden;
	}

	@Override
	public String toString() {
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		return formatter.format(this.date) + "         " + this.beschrijving;
	}

}

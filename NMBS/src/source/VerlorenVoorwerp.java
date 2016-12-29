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

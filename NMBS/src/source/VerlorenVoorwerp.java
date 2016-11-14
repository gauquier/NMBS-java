package source;

import java.util.Calendar;

public class VerlorenVoorwerp {
	int id;
	Calendar datum;
	String beschrijving;
	boolean gevonden;
	
	public VerlorenVoorwerp(int id, Calendar datum, String beschrijving) {
		super();
		this.id = id;
		this.datum = datum;
		this.beschrijving = beschrijving;
		this.gevonden = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if(id > 0)
		this.id = id;
	}

	public Calendar getDatum() {
		return datum;
	}

	public void setDatum(Calendar datum) {
		this.datum = datum;
	}

	public String getBeschrijving() {
		return beschrijving;
	}

	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}

	public boolean isGevonden() {
		return gevonden;
	}

	public void setGevonden(boolean gevonden) {
		this.gevonden = gevonden;
	}

	@Override
	public String toString() {
		return "VerlorenVoorwerp [id=" + id + ", datum=" + datum + ", beschrijving=" + beschrijving + ", gevonden="
				+ gevonden + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beschrijving == null) ? 0 : beschrijving.hashCode());
		result = prime * result + ((datum == null) ? 0 : datum.hashCode());
		result = prime * result + (gevonden ? 1231 : 1237);
		result = prime * result + id;
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
		if (datum == null) {
			if (other.datum != null)
				return false;
		} else if (!datum.equals(other.datum))
			return false;
		if (gevonden != other.gevonden)
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}

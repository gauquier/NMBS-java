package api;

public class Vertrek {
	@Override
	public String toString() {
		return "Vertrek [station=" + station + ", vertraging=" + vertraging + ", richting=" + richting + ", tijd="
				+ tijd + ", datum=" + datum + ", trein=" + trein + ", perron=" + perron + ", canceled=" + canceled
				+ "]";
	}
	public String station;
	public int vertraging;
	public String richting;
	public String tijd;
	public String datum;
	public String trein;
	public int perron;
	public boolean canceled;
	public Vertrek() {
		super();
	}
	public void setStation(String station) {
		this.station = station;
	}
	public void setVertraging(int vertraging) {
		this.vertraging = vertraging;
	}
	public String getStation() {
		return station;
	}
	public int getVertraging() {
		return vertraging;
	}
	public String getRichting() {
		return richting;
	}
	public String getTijd() {
		return tijd;
	}
	public String getDatum() {
		return datum;
	}
	public String getTrein() {
		return trein;
	}
	public String getPerron() {
		if(perron > 0)
		{
		return Integer.toString(perron);
		}
		return "/";
	}
	public boolean isCanceled() {
		return canceled;
	}
	public void setRichting(String richting) {
		this.richting = richting;
	}
	public void setTijd(String tijd) {
		this.tijd = tijd;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public void setTrein(String trein) {
		this.trein = trein;
	}
	public void setPerron(int perron) {
		this.perron = perron;
	}
	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}
	
}

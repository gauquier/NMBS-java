package api;

public class Aankomst {
	public String station;
	public int vertraging;
	public String richting;
	public String tijd;
	public String datum;
	public String trein;
	public int perron;
	public boolean canceled;
	public Aankomst() {
		super();
	}
	public void setStation(String station) {
		this.station = station;
	}
	public void setVertraging(int vertraging) {
		this.vertraging = vertraging;
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
	@Override
	public String toString() {
		return "Aankomst [station=" + station + ", vertraging=" + vertraging + ", richting=" + richting + ", tijd="
				+ tijd + ", datum=" + datum + ", trein=" + trein + ", perron=" + perron + ", canceled=" + canceled
				+ "]";
	}
	
}

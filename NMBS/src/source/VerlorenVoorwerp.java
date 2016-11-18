package source;
import java.sql.Date;
import java.util.Calendar;
public class VerlorenVoorwerp {

	private int verlorenVoorwerpId;
	private Station station;
	private String beschrijving;
	private Date date;
	private boolean gevonden;
	private String stationString;
	
	
	public VerlorenVoorwerp(int verlorenVoorwerpId, Station station, String beschrijving, Date date,
			boolean gevonden) {
		super();
		this.verlorenVoorwerpId = verlorenVoorwerpId;
		this.station = station;
		this.beschrijving = beschrijving;
		this.date = date;
		this.gevonden = gevonden;
	}
	
	public VerlorenVoorwerp( String stationString, String beschrijving, Date date, boolean gevonden) {
		super();
		this.stationString = stationString;
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


	public Station getStation() {
		return station;
	}
	public void setStation(Station station) {
		this.station = station;
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
	
	
}

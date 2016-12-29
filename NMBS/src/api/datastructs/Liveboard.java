package api.datastructs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author pieterc
 */
public class Liveboard implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4518110581259345881L;
	private Date timeStamp;
	private Station station;
	private ArrayList<ArrivalDeparture> arrivalsAndDepartures;

	public Liveboard(Date timeStamp, Station station, ArrayList<ArrivalDeparture> arrivalsAndDepartures) {
		this.timeStamp = timeStamp;
		this.station = station;
		this.arrivalsAndDepartures = arrivalsAndDepartures;
	}

	public List<ArrivalDeparture> getArrivalsAndDepartures() {
		return this.arrivalsAndDepartures;
	}

	public Station getStation() {
		return this.station;
	}

	public Date getTimeStamp() {
		return this.timeStamp;
	}
}

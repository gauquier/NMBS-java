package api.datastructs;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author pieterc
 */

public class Connection implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6387671419985271150L;
	private TripNode arrival;
	private TripNode departure;
	private int duration;
	private List<Via> vias;

	public Connection(TripNode arrival, TripNode departure, int duration, List<Via> vias) {
		this.arrival = arrival;
		this.departure = departure;
		this.duration = duration;
		this.vias = vias;
	}

	public TripNode getArrival() {
		return this.arrival;
	}

	public TripNode getDeparture() {
		return this.departure;
	}

	public int getDuration() {
		return this.duration;
	}

	public List<Via> getVias() {
		return this.vias;
	}

	public boolean hasVias() {
		return this.vias != null;
	}
}

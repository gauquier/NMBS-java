package api.datastructs;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author pieterc
 */
public class TripNode implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1773224137967410143L;
	private Station station;
	private String platform;
	private Vehicle vehicle;
	private Date time;
	private int delay;

	public TripNode(Station station, String platform, Vehicle vehicle, Date time, int delay) {
		this.station = station;
		this.platform = platform;
		this.vehicle = vehicle;
		this.time = time;
		this.delay = delay;
	}

	public int getDelay() {
		return this.delay;
	}

	public String getPlatform() {
		return this.platform;
	}

	public Station getStation() {
		return this.station;
	}

	public Date getTime() {
		return this.time;
	}

	public Vehicle getVehicle() {
		return this.vehicle;
	}
}
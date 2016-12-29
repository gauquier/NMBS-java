package api.datastructs;

import java.io.Serializable;
import java.util.Date;

public class ArrivalDeparture implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1046757633719665371L;
	private Station station;
	private Vehicle vehicle;
	private Date date;
	private String platform;
	private int delay;
	private boolean left;

	public ArrivalDeparture(Station station, Vehicle vehicle, Date date, String platform, int delay, boolean left) {
		this.station = station;
		this.vehicle = vehicle;
		this.date = date;
		this.platform = platform;
		this.delay = delay;
		this.left = left;
	}

	public Date getDate() {
		return this.date;
	}

	public String getPlatform() {
		return this.platform;
	}

	public Station getStation() {
		return this.station;
	}

	public Vehicle getVehicle() {
		return this.vehicle;
	}

	public int getDelay() {
		return this.delay;
	}

	public boolean hasLeft() {
		return this.left;
	}

	public boolean shouldHaveLeftAt(Date atTime) {
		return (atTime.getTime() < (this.getDate().getTime() + (1000 * this.getDelay())));
	}
}
package api.datastructs;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author pieterc
 */
public class Stop implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3505628328412149605L;
	private Station station;
	private Date time;
	private int delay;

	public Stop(Station station, Date time, int delay) {
		this.station = station;
		this.time = time;
		this.delay = delay;
	}

	public int getDelay() {
		return this.delay;
	}

	public Station getStation() {
		return this.station;
	}

	public Date getTime() {
		return this.time;
	}
}

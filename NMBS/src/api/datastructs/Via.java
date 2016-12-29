package api.datastructs;

import java.io.Serializable;

/**
 *
 * @author pieterc
 */
public class Via implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2540118664759439435L;
	private ViaTripNode arrival;
	private ViaTripNode depart;
	private int timeBetween;
	private Vehicle vehicle;
	private Station station;

	public Via(ViaTripNode arrival, ViaTripNode depart, int timeBetween, Vehicle vehicle, Station station) {
		this.arrival = arrival;
		this.depart = depart;
		this.timeBetween = timeBetween;
		this.vehicle = vehicle;
		this.station = station;
	}

	public ViaTripNode getArrival() {
		return this.arrival;
	}

	public ViaTripNode getDepart() {
		return this.depart;
	}

	public Station getStation() {
		return this.station;
	}

	public int getTimeBetween() {
		return this.timeBetween;
	}

	public Vehicle getVehicle() {
		return this.vehicle;
	}
}
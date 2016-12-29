package source;

import java.util.ArrayList;

public class Station {
	private int stationID;
	private String naam;
	private static String currentStation;
	private ArrayList<VerlorenVoorwerp> verlorenVoorwerpen = new ArrayList<VerlorenVoorwerp>();

	public Station(int stationID, String naam, ArrayList<VerlorenVoorwerp> verlorenVoorwerpen) {
		this.stationID = stationID;
		this.naam = naam;
		this.verlorenVoorwerpen = verlorenVoorwerpen;
	}

	public Station(int stationID, String naam) {
		this.stationID = stationID;
		this.naam = naam;
	}

	public Station(String currentStation) {
		super();
		Station.currentStation = currentStation;
	}

	public void addVerlorenVoorwerp(VerlorenVoorwerp station) {
		this.verlorenVoorwerpen.add(station);
	}

	public void delVerlorenVoorwerp(VerlorenVoorwerp station) {
		this.verlorenVoorwerpen.remove(station);
	}

	public ArrayList<VerlorenVoorwerp> getVerlorenVoorwerpen() {
		return this.verlorenVoorwerpen;
	}

	public void setVerlorenVoorwerpen(ArrayList<VerlorenVoorwerp> verlorenVoorwerpen) {
		this.verlorenVoorwerpen = verlorenVoorwerpen;
	}

	public int getStationID() {
		return this.stationID;
	}

	public void setStationID(int stationID) {
		this.stationID = stationID;
	}

	public String getNaam() {
		return this.naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	/**
	 * @return the currentStation
	 */
	public static String getCurrentStation() {
		return currentStation;
	}

	/**
	 * @param currentStation
	 *            the currentStation to set
	 */
	public void setCurrentStation(String currentStation) {
		Station.currentStation = currentStation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.naam == null) ? 0 : this.naam.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Station other = (Station) obj;
		if (this.naam == null) {
			if (other.naam != null) {
				return false;
			}
		} else if (!this.naam.equals(other.naam)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return this.naam;

	}
}
package source;

import java.util.ArrayList;

public class Station {
	private int stationID;
	private String naam;
	private String zone;
	private int aantalLoketten;
	private int parkingplaatsen;
	private boolean wifi;
	private boolean gehandicapte;
	private ArrayList<VerlorenVoorwerp> verlorenVoorwerpen = new ArrayList<VerlorenVoorwerp>();
	
	public Station(int stationID, String naam, String zone, int aantalLoketten, int parkingplaatsen, boolean wifi,
			boolean gehandicapte, ArrayList<VerlorenVoorwerp> verlorenVoorwerpen) {
		super();
		this.stationID = stationID;
		this.naam = naam;
		this.zone = zone;
		this.aantalLoketten = aantalLoketten;
		this.parkingplaatsen = parkingplaatsen;
		this.wifi = wifi;
		this.gehandicapte = gehandicapte;
	}
	
	public Station(int stationID){
		this.stationID = stationID;
	}
	
	public void addVerlorenVoorwerp(VerlorenVoorwerp station){
		verlorenVoorwerpen.add(station);
	}
	
	public void delVerlorenVoorwerp(VerlorenVoorwerp station){
		verlorenVoorwerpen.remove(station);
	}
	
	public ArrayList<VerlorenVoorwerp> getVerlorenVoorwerpen() {
		return verlorenVoorwerpen;
	}

	public void setVerlorenVoorwerpen(ArrayList<VerlorenVoorwerp> verlorenVoorwerpen) {
		this.verlorenVoorwerpen = verlorenVoorwerpen;
	}

	public int getStationID() {
		return stationID;
	}
	
	public void setStationID(int stationID) {
		this.stationID = stationID;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public int getAantalLoketten() {
		return aantalLoketten;
	}
	public void setAantalLoketten(int aantalLoketten) {
		this.aantalLoketten = aantalLoketten;
	}
	public int getParkingplaatsen() {
		return parkingplaatsen;
	}
	public void setParkingplaatsen(int parkingplaatsen) {
		this.parkingplaatsen = parkingplaatsen;
	}

	public boolean getWifi() {
		return wifi;
	}
	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}
	public boolean getGehandicapte() {
		return gehandicapte;
	}
	public void setGehandicapte(boolean gehandicapte) {
		this.gehandicapte = gehandicapte;
	}
	
	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}
	
 

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Station other = (Station) obj;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Station [stationID=" + stationID + ", naam=" + naam + ", zone=" + zone + ", aantalLoketten="
				+ aantalLoketten + ", parkingplaatsen=" + parkingplaatsen + ", wifi=" + wifi + ", gehandicapte="
				+ gehandicapte + ", verlorenVoorwerpen=" + verlorenVoorwerpen + "]";
	}
	
}
package source;

import java.util.Vector;

public class Station {
	int id;
	String zone;
	int aantalLoketten;
	int parkingPlaatsen;
	boolean wifi;
	boolean gehandicapt;
	Vector<VerlorenVoorwerp> verlorenVoorwerpen;
	
	public Station(int id, String zone, int aantalLoketten, int parkingPlaatsen, boolean wifi, boolean gehandicapt) {
		super();
		this.id = id;
		this.zone = zone;
		this.aantalLoketten = aantalLoketten;
		this.parkingPlaatsen = parkingPlaatsen;
		this.wifi = wifi;
		this.gehandicapt = gehandicapt;
		verlorenVoorwerpen = new Vector<VerlorenVoorwerp>();
	}
	public Station(int id, String zone, int aantalLoketten, int parkingPlaatsen, boolean wifi, boolean gehandicapt, Vector<VerlorenVoorwerp> verlorenVoorwerpen) {
		super();
		this.id = id;
		this.zone = zone;
		this.aantalLoketten = aantalLoketten;
		this.parkingPlaatsen = parkingPlaatsen;
		this.wifi = wifi;
		this.gehandicapt = gehandicapt;
		this.verlorenVoorwerpen = verlorenVoorwerpen;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		if(id > 0)
		this.id = id;
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
		if(aantalLoketten >= 0)
		this.aantalLoketten = aantalLoketten;
	}
	public int getParkingPlaatsen() {
		return parkingPlaatsen;
	}
	public void setParkingPlaatsen(int parkingPlaatsen) {
		if(parkingPlaatsen >= 0)
		this.parkingPlaatsen = parkingPlaatsen;
	}
	public boolean isWifi() {
		return wifi;
	}
	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}
	public boolean isGehandicapt() {
		return gehandicapt;
	}
	public void setGehandicapt(boolean gehandicapt) {
		this.gehandicapt = gehandicapt;
	}
	
	public Vector<VerlorenVoorwerp> getVerlorenVoorwerpen() {
		return verlorenVoorwerpen;
	}
	public void setVerlorenVoorwerpen(Vector<VerlorenVoorwerp> verlorenVoorwerpen) {
		this.verlorenVoorwerpen = verlorenVoorwerpen;
	}
	public void addVerlorenVoorwerp(VerlorenVoorwerp vp){
		if(vp != null)
		verlorenVoorwerpen.add(vp);
	}
	public void removeVerlorenVoorwerp(VerlorenVoorwerp vp){
		if(vp != null && verlorenVoorwerpen.contains(vp)){
			verlorenVoorwerpen.remove(vp);
		}
	}
	@Override
	public String toString() {
		return "Station [id=" + id + ", zone=" + zone + ", aantalLoketten=" + aantalLoketten + ", parkingPlaatsen="
				+ parkingPlaatsen + ", wifi=" + wifi + ", gehandicapt=" + gehandicapt + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aantalLoketten;
		result = prime * result + (gehandicapt ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + parkingPlaatsen;
		result = prime * result + (wifi ? 1231 : 1237);
		result = prime * result + ((zone == null) ? 0 : zone.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Station other = (Station) obj;
		if (aantalLoketten != other.aantalLoketten)
			return false;
		if (gehandicapt != other.gehandicapt)
			return false;
		if (id != other.id)
			return false;
		if (parkingPlaatsen != other.parkingPlaatsen)
			return false;
		if (wifi != other.wifi)
			return false;
		if (zone == null) {
			if (other.zone != null)
				return false;
		} else if (!zone.equals(other.zone))
			return false;
		return true;
	}
	
	
}

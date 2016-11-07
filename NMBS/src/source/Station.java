package source;

public class Station {
	public int aantalLoketten;
	public int parkingplaatsen;
	public int wifi;
	public int gehandicapt;
	public Station(int aantalLoketten, int parkingplaatsen, int wifi, int gehandicapt) {
		super();
		this.aantalLoketten = aantalLoketten;
		this.parkingplaatsen = parkingplaatsen;
		this.wifi = wifi;
		this.gehandicapt = gehandicapt;
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
	public int getWifi() {
		return wifi;
	}
	public void setWifi(int wifi) {
		this.wifi = wifi;
	}
	public int getGehandicapt() {
		return gehandicapt;
	}
	public void setGehandicapt(int gehandicapt) {
		this.gehandicapt = gehandicapt;
	}
	@Override
	public String toString() {
		return "Station [aantalLoketten=" + aantalLoketten + ", parkingplaatsen=" + parkingplaatsen + ", wifi=" + wifi
				+ ", gehandicapt=" + gehandicapt + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aantalLoketten;
		result = prime * result + gehandicapt;
		result = prime * result + parkingplaatsen;
		result = prime * result + wifi;
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
		if (parkingplaatsen != other.parkingplaatsen)
			return false;
		if (wifi != other.wifi)
			return false;
		return true;
	}
	
	
}

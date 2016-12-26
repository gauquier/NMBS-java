package source;

public class Aankoop {
	
	protected int aankoopId;
	protected double korting;
	protected double prijs;
	protected VerkoopType verkoop;
	
	public Aankoop(double korting, double prijs, VerkoopType verkoop) {
		setKorting(korting);
		setPrijs(prijs);
		setVerkoop(verkoop);
	}
	
	public Aankoop(int aankoopId, double korting, double prijs, VerkoopType verkoop) {
		setAankoopId(aankoopId);
		setKorting(korting);
		setPrijs(prijs);
		setVerkoop(verkoop);
	}
	
	public int getAankoopId() {
		return aankoopId;
	}
	public void setAankoopId(int aankoopId) {
		this.aankoopId = aankoopId;
	}
	public double getKorting() {
		return korting;
	}
	public void setKorting(double korting) {
		this.korting = korting;
	}
	public double getPrijs() {
		return prijs;
	}
	public void setPrijs(double prijs) {
		this.prijs = prijs;
	}
	public VerkoopType getVerkoop() {
		return verkoop;
	}
	public void setVerkoop(VerkoopType verkoop) {
		this.verkoop = verkoop;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aankoopId;
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
		Aankoop other = (Aankoop) obj;
		if (aankoopId != other.aankoopId)
			return false;
		return true;
	}
	
}
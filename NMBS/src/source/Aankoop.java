package source;

public class Aankoop {

	private int aankoopId;
	private double korting;
	private double prijs;
	private VerkoopType verkoop;

	public Aankoop(double korting, double prijs, VerkoopType verkoop) {
		this.setKorting(korting);
		this.setPrijs(prijs);
		this.setVerkoop(verkoop);
	}

	public Aankoop(int aankoopId, double korting, double prijs, VerkoopType verkoop) {
		this.setAankoopId(aankoopId);
		this.setKorting(korting);
		this.setPrijs(prijs);
		this.setVerkoop(verkoop);
	}

	public int getAankoopId() {
		return this.aankoopId;
	}

	public void setAankoopId(int aankoopId) {
		this.aankoopId = aankoopId;
	}

	public double getKorting() {
		return this.korting;
	}

	public void setKorting(double korting) {
		this.korting = korting;
	}

	public double getPrijs() {
		return this.prijs;
	}

	public void setPrijs(double prijs) {
		this.prijs = prijs;
	}

	public VerkoopType getVerkoop() {
		return this.verkoop;
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
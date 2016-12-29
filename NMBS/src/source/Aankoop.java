package source;

public class Aankoop {
	
	private int aankoopId;
	private double korting;
	private double prijs;
	private VerkoopType verkoop;
	
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
}
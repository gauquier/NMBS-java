package source;

public class Aankoop {
	public enum VerkoopType {
		STANDAARD, GROEP, STUDENT, PLUS60
	}
	
	protected int aankoopId;
	protected double korting;
	protected double prijs;
	protected VerkoopType verkoop;
	
	//constructor zonder aankoopId maken?
	
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
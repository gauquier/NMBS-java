package source;

public class Prijs {
	private int prijsId;
	private String verkoopType;
	private double prijs;
	
	public Prijs(int prijsId, String verkoopType, double prijs) {
		super();
		setPrijsId(prijsId);
		setVerkoopType(verkoopType);
		setPrijs(prijs);
	}

	public int getPrijsId() {
		return prijsId;
	}

	public void setPrijsId(int prijsId) {
		this.prijsId = prijsId;
	}

	public String getVerkoopType() {
		return verkoopType;
	}

	public void setVerkoopType(String verkoopType) {
		this.verkoopType = verkoopType;
	}

	public double getPrijs() {
		return prijs;
	}

	public void setPrijs(double prijs) {
		this.prijs = prijs;
	}

	@Override
	public String toString() {
		return verkoopType + ": " + prijs + " EUR";
	}
	
	
}
package source;

public class Prijs {
	private int prijsId;
	private String verkoopType;
	private double prijs;

	public Prijs(int prijsId, String verkoopType, double prijs) {
		super();
		this.setPrijsId(prijsId);
		this.setVerkoopType(verkoopType);
		this.setPrijs(prijs);
	}

	public int getPrijsId() {
		return this.prijsId;
	}

	public void setPrijsId(int prijsId) {
		this.prijsId = prijsId;
	}

	public String getVerkoopType() {
		return this.verkoopType;
	}

	public void setVerkoopType(String verkoopType) {
		this.verkoopType = verkoopType;
	}

	public double getPrijs() {
		return this.prijs;
	}

	public void setPrijs(double prijs) {
		this.prijs = prijs;
	}

	@Override
	public String toString() {
		return this.verkoopType + ": " + this.prijs + " EUR";
	}

}
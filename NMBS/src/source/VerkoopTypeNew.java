package source;

public class VerkoopTypeNew {
	private int verkooptypeId;
	private String verkoopType;
	private double prijs;
	
	public VerkoopTypeNew(int verkooptypeId, String verkoopType, double prijs) {
		super();
		setVerkooptypeId(verkooptypeId);
		setVerkoopType(verkoopType);
		setPrijs(prijs);
	}

	public int getVerkooptypeId() {
		return verkooptypeId;
	}

	public void setVerkooptypeId(int verkooptypeId) {
		this.verkooptypeId = verkooptypeId;
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
}
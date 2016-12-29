package source;

public class Klant extends Persoon {

	private int klantId;
	private String info;
	private boolean actief;

	public int getKlantId() {
		return this.klantId;
	}

	public void setKlantId(int klantId) {
		this.klantId = klantId;
	}

	public Klant(int persoonId, String voornaam, String achternaam, String email, Adres adres, int klantId, String info,
			boolean actief) {
		super(persoonId, voornaam, achternaam, email, adres);
		this.klantId = klantId;
		this.info = info;
		this.actief = actief;

	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (this.actief ? 1231 : 1237);
		result = prime * result + ((this.info == null) ? 0 : this.info.hashCode());
		result = prime * result + this.klantId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Klant other = (Klant) obj;
		if (this.actief != other.actief) {
			return false;
		}
		if (this.info == null) {
			if (other.info != null) {
				return false;
			}
		} else if (!this.info.equals(other.info)) {
			return false;
		}
		if (this.klantId != other.klantId) {
			return false;
		}
		return true;
	}

	public boolean isActief() {
		return this.actief;
	}

	public void setActief(boolean actief) {
		this.actief = actief;
	}

	@Override
	public String toString() {
		return this.getVoornaam() + " " + this.getAchternaam();
	}

}
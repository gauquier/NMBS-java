package source;

public class Medewerker extends Persoon{
	Rol rol;
	boolean actief;
	
	public Medewerker(int id, String voornaam, String achternaam, String email, Adres adres, Rol rol) {
		super(id, voornaam, achternaam, email, adres);
		this.rol = rol;
		this.actief = true;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public boolean isActief() {
		return actief;
	}

	public void setActief(boolean actief) {
		this.actief = actief;
	}

	@Override
	public String toString() {
		return "Medewerker [rol=" + rol + ", actief=" + actief + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (actief ? 1231 : 1237);
		result = prime * result + ((rol == null) ? 0 : rol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medewerker other = (Medewerker) obj;
		if (actief != other.actief)
			return false;
		if (rol == null) {
			if (other.rol != null)
				return false;
		} else if (!rol.equals(other.rol))
			return false;
		return true;
	}
	
	
	
}

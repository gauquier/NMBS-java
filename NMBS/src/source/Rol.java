package source;

public class Rol {
	int id;
	String naam;
	
	public Rol(int id, String naam) {
		super();
		this.id = id;
		this.naam = naam;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		if(id > 0)
		this.id = id;
	}
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	@Override
	public String toString() {
		return "Rol [id=" + id + ", naam=" + naam + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
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
		Rol other = (Rol) obj;
		if (id != other.id)
			return false;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		return true;
	}
	
	
}

package source;

public class Rol {
	private int rollId;
	private String Rol;
	public int getRollId() {
		return rollId;
	}
	public void setRollId(int rollId) {
		this.rollId = rollId;
	}
	public String getRol() {
		return Rol;
	}
	public void setRol(String rol) {
		Rol = rol;
	}
	public Rol(int rollId, String rol) {
		super();
		this.rollId = rollId;
		Rol = rol;
	}
	public Rol(int rollId) {
		super();
		this.rollId = rollId;
	}
	
	
	
}

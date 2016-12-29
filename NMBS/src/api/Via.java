package api;

public class Via {
	 @Override
	public String toString() {
		return "Via [vertrek=" + vertrek + ", aankomst=" + aankomst + "]";
	}
	public Vertrek vertrek;
	public Aankomst aankomst;
	public Via(Vertrek vertrek, Aankomst aankomst) {
		super();
		this.vertrek = vertrek;
		this.aankomst = aankomst;
	}
	
}

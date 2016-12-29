package api;

import java.util.ArrayList;

public class Trip {
	private int id;
@Override
	public String toString() {
		return id+1 + "["+vertrek.toString()+", " + aankomst.toString() + "]";
	}
public Vertrek vertrek;
public Aankomst aankomst;
public ArrayList<Via> viaList = new ArrayList<Via>();
public Trip(int id,Vertrek vertrek, Aankomst aankomst) {
	super();
	this.id = id;
	this.vertrek = vertrek;
	this.aankomst = aankomst;
}
public void addVia(Via via)
{
	viaList.add(via);
}
}

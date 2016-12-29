package api.datastructs;

import java.io.Serializable;

/**
 *
 * @author pieterc
 */
public class Station implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3631477534712658791L;
	private String name;
	private String id;
	private Location location;

	public Station(String name, String id, Location location) {
		this.id = id;
		this.name = name.trim();
		this.location = location;
	}

	public Station(String name) {
		this.name = name.trim();
	}

	public Location getLocation() {
		return this.location;
	}

	public String getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
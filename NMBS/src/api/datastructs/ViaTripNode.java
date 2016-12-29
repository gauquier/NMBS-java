package api.datastructs;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author pieterc
 */
public class ViaTripNode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4558061951637327103L;
	private String platform;
	private Date time;

	public ViaTripNode(String platform, Date time) {
		this.platform = platform;
		this.time = time;
	}

	public String getPlatform() {
		return this.platform;
	}

	public Date getTime() {
		return this.time;
	}
}
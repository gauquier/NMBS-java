package source;

public class Adres {

	private int adresId;
	private String straat;
	private String woonplaats;
	private int postcode;
	private int bus;
	private int huisNr;

	public Adres() {
		// default constructor
	}

	/**
	 * @param straat
	 *            straat naam en nummer bv Nijverheidskaai 170
	 * @param woonplaats
	 *            bv Anderlecht
	 * @param postcode
	 *            bv 1070
	 * @param bus
	 *            bus nummer vb 32
	 */
	public Adres(String straat, int huisNr, String woonplaats, int postcode, int bus) {
		super();
		this.straat = straat;
		this.woonplaats = woonplaats;
		this.postcode = postcode;
		this.bus = bus;
		this.huisNr = huisNr;
	}

	/**
	 * @param adresId
	 *            id van het adres
	 * @param straat
	 *            straat naam en nummer bv Nijverheidskaai 170
	 * @param woonplaats
	 *            bv Anderlecht
	 * @param postcode
	 *            bv 1070
	 * @param bus
	 *            bus nummer vb 32
	 */
	public Adres(int adresId, String straat, int huisNr, String woonplaats, int postcode, int bus) {
		super();
		this.adresId = adresId;
		this.straat = straat;
		this.woonplaats = woonplaats;
		this.postcode = postcode;
		this.bus = bus;
		this.huisNr = huisNr;
	}

	/**
	 * @return adresId id van het adres
	 */
	public int getAdresId() {
		return adresId;
	}

	/**
	 * @param adresId
	 *            id van het adres
	 */
	public void setAdresId(int adresId) {
		this.adresId = adresId;
	}

	/**
	 * @return straat straat naam en nummer bv Nijverheidskaai 170
	 */
	public String getStraat() {
		return straat;
	}

	/**
	 * @param straat
	 *            straat naam en nummer bv Nijverheidskaai 170
	 */
	public void setStraat(String straat) {
		this.straat = straat;
	}

	/**
	 * @return woonplaats bv Anderlecht
	 */
	public String getWoonplaats() {
		return woonplaats;
	}

	/**
	 * @param woonplaats
	 *            bv Anderlecht
	 */
	public void setWoonplaats(String woonplaats) {
		this.woonplaats = woonplaats;
	}

	/**
	 * @return postcode
	 */
	public int getPostcode() {
		return postcode;
	}

	/**
	 * @param postcode
	 */
	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}

	/**
	 * @return bus nummer van bus
	 */
	public int getBus() {
		return bus;
	}

	/**
	 * @param bus
	 *            nummer van bus
	 */
	public void setBus(int bus) {
		this.bus = bus;
	}

	public int getHuisNr() {
		return huisNr;
	}

	public void setHuisNr(int huisNr) {
		this.huisNr = huisNr;
	}
}

package api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import api.datastructs.Connection;
import api.datastructs.Liveboard;
import api.datastructs.Station;
import api.datastructs.Vehicle;
import api.datastructs.VehicleInformation;
import api.dom.Parser;

public class IRail {
	private static final String DEFAULT_LANGUAGE = "nl";
	private static final String WRAPPER_SUFFIX = "IRailForJ";
	private static final int NO_MAX_RESULTS = 0;

	private String language;
	private int maxResults;
	private String providerURL;

	public IRail(String providerURL) {
		this.providerURL = providerURL;
		this.language = DEFAULT_LANGUAGE;
		this.maxResults = NO_MAX_RESULTS;
	}

	public IRail(String providerURL, String language) {
		this.providerURL = providerURL;
		this.language = language;
		this.maxResults = NO_MAX_RESULTS;
	}

	public IRail(String providerURL, int maxResults) {
		this.providerURL = providerURL;
		this.language = DEFAULT_LANGUAGE;
		this.maxResults = maxResults;
	}

	public IRail(String providerURL, String language, int maxResults) {
		this.providerURL = providerURL;
		this.language = language;
		this.maxResults = maxResults;
	}

	public IRail(String providerURL, String language, int maxResults, String agentName) {
		this.providerURL = providerURL;
		this.language = language;
		this.maxResults = maxResults;
		this.setAgent(agentName);
	}

	/*
	 * -------------------------------------------------------------------------
	 * --------------------
	 */

	public synchronized VehicleInformation getVehicleInformation(Vehicle vehicle) throws Exception {
		URLFactory urlFactory = new URLFactory(this.providerURL, "vehicle/");
		urlFactory.addQuery("id", vehicle.getId());
		urlFactory.addQuery("lang", this.language);
		return Parser.parseVehicle(urlFactory.getURL());
	}

	public synchronized Liveboard getLiveboard(Station station) throws Exception {
		URLFactory urlFactory = new URLFactory(this.providerURL, "liveboard/");
		urlFactory.addQuery("station", station.getName());
		urlFactory.addQuery("lang", this.language);
		return Parser.parseLiveboard(urlFactory.getURL());
	}

	public synchronized ArrayList<Connection> getConnections(String from, String to) throws Exception {
		return this.getConnections(from, to, null, false);
	}

	public synchronized ArrayList<Connection> getConnections(String from, String to, Date dateTime) throws Exception {
		return this.getConnections(from, to, dateTime, false);
	}

	public synchronized ArrayList<Connection> getConnections(String from, String to, Date dateTime,
			boolean wantArrivalTime) throws Exception {
		URLFactory urlFactory = new URLFactory(this.providerURL, "connections/");
		urlFactory.addQuery("from", from);
		urlFactory.addQuery("to", to);

		if (this.maxResults > 0) {
			urlFactory.addQuery("results", String.valueOf(this.maxResults));
		}

		if (dateTime != null) {
			DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
			DateFormat timeFormat = new SimpleDateFormat("HHmm");
			urlFactory.addQuery("date", dateFormat.format(dateTime));
			urlFactory.addQuery("time", timeFormat.format(dateTime));
		}

		if (wantArrivalTime) {
			urlFactory.addQuery("timeSel", "arr");
		}

		urlFactory.addQuery("lang", this.language);

		return (ArrayList<Connection>) Parser.parseConnections(urlFactory.getURL());
	}

	public synchronized ArrayList<Station> getStations() throws Exception {
		URLFactory urlFactory = new URLFactory(this.providerURL, "stations/");
		urlFactory.addQuery("lang", this.language);

		return (ArrayList<Station>) Parser.parseStations(urlFactory.getURL());
	}

	/*
	 * -------------------------------------------------------------------------
	 * -------------
	 */

	public String getLanguage() {
		return this.language;
	}

	public IRail setLanguage(String language) {
		this.language = language;
		return this;
	}

	public int getMaxResults() {
		return this.maxResults;
	}

	public IRail setMaxResults(int maxResults) {
		this.maxResults = maxResults;
		return this;
	}

	/**
	 *
	 * @param agentName
	 *            is the parameter that is used to identify yourself in the
	 *            IRail api. Set it to null if you want to remain anonymous. Set
	 *            it to your application name if you want us to see it's you
	 * @return
	 */
	public final IRail setAgent(String agentName) {
		System.setProperty("http.agent", agentName != null ? agentName + " " + WRAPPER_SUFFIX : WRAPPER_SUFFIX);
		return this;
	}
}
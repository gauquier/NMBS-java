package api;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import source.*;
import source.Station;
public abstract class Parser {
	public static int getNoDeps(String input )
	{
		StringBuilder builder = new StringBuilder();
    	builder.append(input);
    	builder.delete(0,builder.indexOf("</station>")+10);
        String noDepartures = builder.substring(1,builder.indexOf(">"));
        builder.delete(0, builder.indexOf(">")+1);
	    int deps = Integer.parseInt(noDepartures.substring(noDepartures.indexOf('"')+1, noDepartures.length()-1));
		return deps;
	}
	public static int getNoTrips(String input )
	{
		StringBuilder builder = new StringBuilder();
    	builder.append(input);
    	int trips = 0;
    	int lastIndex = 0;
    	while(lastIndex != -1){

    	    lastIndex = builder.indexOf("</connection>" ,lastIndex);

    	    if(lastIndex != -1){
    	        trips ++;
    	        lastIndex += 10;
    	    }
    	}
    	return trips;
	}
	public static int getNoVias(String input )
	{
		StringBuilder builder = new StringBuilder();
    	builder.append(input);
    	int via = 0;
    	int lastIndex = 0;
    	while(lastIndex != -1){

    	    lastIndex = builder.indexOf("</via>" ,lastIndex);

    	    if(lastIndex != -1){
    	        via++;
    	        lastIndex += 10;
    	    }
    	}
    	return via;
	}
	public static BillBoard getBill(String input, int pl)
	{
		StringBuilder builder = new StringBuilder();
    	builder.append(input);
    	StringBuilder departure = new StringBuilder();
    	departure.append("<departure id=\"");
    	departure.append(pl);
    	departure.append("\"");
    	builder.delete(0, builder.indexOf(departure.toString()));
    	builder.delete(builder.indexOf("</departure>")+12,builder.length());
    	int delay = Parser.getDelay(builder.toString());
    	boolean canceled = Boolean.parseBoolean(Parser.getState(builder.toString()));
    	String direction = Parser.getBillStation(builder.toString());
    	String date = Parser.getDate(builder.toString());
    	String time = Parser.getTime(builder.toString());;
    	String train = Parser.getVehicle(builder.toString());
    	int platform = Parser.getPlatform(builder.toString());
		BillBoard billboard = new BillBoard(pl, delay, canceled, direction, date, time, train, platform);
		return billboard;
	}
	public static String getBillStation(String input)
	{
		StringBuilder builder = new StringBuilder();
    	builder.append(input);
    	builder.delete(0, builder.indexOf("<station>")+9);
    	builder.delete(builder.indexOf("</station>"),builder.length());
		return builder.toString();
	}
	public static int getNoBill(String input)
	{
		StringBuilder builder = new StringBuilder();
    	builder.append(input);
    	int deps = 0;
    	builder.delete(0, builder.indexOf("<departures number=")+20);
    	builder.delete(builder.indexOf(">")-1, builder.length());
    	deps = Integer.parseInt(builder.toString());
    	return deps;
	}
	public static String GetVia(String input, int viaNo)
	{
		StringBuilder builder = new StringBuilder();
		StringBuilder viaId = new StringBuilder();
		viaId.append("<via id=\"");
		viaId.append(Integer.toString(viaNo));
		viaId.append("\">");
    	builder.append(input); 	
    	builder.delete(0,builder.indexOf(viaId.toString()));
    	builder.delete(0,builder.indexOf("<via"));
    	builder.delete(builder.indexOf("</via>"), builder.length());
    	builder.delete(0, viaId.length());
		return builder.toString();
	}
	public static String getDirection(String input)
	{
	 StringBuilder station = new StringBuilder();
		station.append(input);
		station.delete(0, station.indexOf("<direction>"));
		station.delete(0, station.indexOf(">")+1);
		station.delete(station.indexOf("</direction>"), station.length());
		return station.toString();
	}
	public static String getTimeBetween(String input)
	{
	 StringBuilder time = new StringBuilder();
	 time.append(input);
	 time.delete(0, time.indexOf("<timebeteween>")+14);
	 time.delete(time.indexOf("</timebeteween>"), time.length());
		return time.toString();
	}
	 public static String getStation(String input)
		{
		 StringBuilder station = new StringBuilder();
			station.append(input);
			station.delete(0, station.indexOf("standardname="));
			station.delete(0, station.indexOf(">")+1);
			station.delete(station.indexOf("</station>"), station.length());
			return station.toString();
		}
	 public static String getTime(String input)
		{
		 StringBuilder time = new StringBuilder();
		 time.append(input);
		 time.delete(0, time.indexOf("time formatted=\"")+27);
		 time.delete(time.indexOf("\""), time.length());
			return time.toString();
		}
	 public static String getDate(String input)
		{
		 StringBuilder date = new StringBuilder();
		 date.append(input);
		 date.delete(0, date.indexOf("time formatted=\"")+16);
		 date.delete(date.indexOf("\"")-9, date.length());
			return date.toString();
		}
	 public static String getVehicle(String input)
		{
		 StringBuilder train = new StringBuilder();
		 train.append(input);
		 train.delete(0, train.indexOf("<vehicle"));
		 train.delete(0, train.indexOf("BE.NMBS.")+8);
		 train.delete(train.indexOf("</vehicle>"), train.length());
		 if(train.indexOf("S") != -1)
		 {
				return "Stoptrein: " + train.toString();
		 }
		 if(train.indexOf("IC") != -1)
		 {
				return "Inter City: " + train.toString();
		 }
		 if(train.indexOf("L") != -1)
		 {
				return "Locale Trein: " + train.toString();
		 }
		 if(train.indexOf("P") != -1)
		 {
				return "Piekuur Trein: " + train.toString();
		 }
			return train.toString();
		}
	 public static int getPlatform(String input)
	{
		 StringBuilder platform = new StringBuilder();
		 platform.append(input);
		 platform.delete(0, platform.indexOf("<platform normal=")+18);
		 platform.delete(0, platform.indexOf(">")+1);
		 try
		 {
			 platform.delete((platform.indexOf("</platform>")), platform.length());
			 return Integer.parseInt(platform.toString());
		 }
		 catch (Exception e1)
		 {
			 System.out.println("geen platform");
			 return -1;
		 }
	}
	 public static int getDuration(String input)
		{
			 StringBuilder duration = new StringBuilder();
			 duration.append(input);
			 duration.delete(0, duration.indexOf("<duration>")+10);
			 try
			 {
				 duration.delete((duration.indexOf("</duration>")), duration.length());
				 int duur = Integer.parseInt(duration.toString());
				 duur = duur/60;
				 return duur;
			 }
			 catch (Exception e1)
			 {
				 System.out.println("geen platform");
				 return -1;
			 }
		}
	 public static String getState(String input)
		{
		 StringBuilder state = new StringBuilder();
		 state.append(input);
		 state.delete(0, state.indexOf("canceled=")+10);
		 state.delete(state.indexOf("\""), state.length());
			return state.toString();
		}
	 public static int getDelay(String input)
		{
		 
		 StringBuilder delay = new StringBuilder();
		 delay.append(input);
		 delay.delete(0, delay.indexOf("delay=\"")+7);
		 delay.delete(delay.indexOf("\""), delay.length());
		 int vertraging = Integer.parseInt(delay.toString());
		 vertraging = vertraging/60;
			return vertraging;
		}
	public static String GetTrip(String input, int tripNo)
	{
		StringBuilder builder = new StringBuilder();
		StringBuilder connectionId = new StringBuilder();
		connectionId.append("<connection id=\"");
		connectionId.append(Integer.toString(tripNo));
		connectionId.append("\">");
    	builder.append(input); 	
    	builder.delete(0,builder.indexOf(connectionId.toString()));
    	builder.delete(0,builder.indexOf("<connection"));
    	builder.delete(builder.indexOf("</connection>"), builder.length());
    	builder.delete(0, connectionId.length());
		return builder.toString();
	}

	public static List<Station> parseStations() throws Exception { 
		Document doc = getDocument(new URL("https://api.irail.be/stations/"));
		ArrayList<Station> stations = new ArrayList<Station>();
		Element rootNode = doc.getDocumentElement();
		NodeList stationNodes = rootNode.getChildNodes();
		if (rootNode.getNodeName().equals("error")) {
			throw new Exception("error:" + rootNode.getFirstChild().getNodeValue());
		}
		for (int i = 0; i < stationNodes.getLength(); i++) {
			Node stationNode = stationNodes.item(i);
			Station s = readStation(stationNode);
			stations.add(s);
		}
		return stations;
	}
	private static Station readStation(Node n) {
		NamedNodeMap m = n.getAttributes(); 
		return new Station(
				0,
				n.getFirstChild().getNodeValue(), 
				new ArrayList<>());
	} 
	private static Document getDocument(URL url) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		DocumentBuilder db = dbf.newDocumentBuilder();
		return db.parse(url.openConnection().getInputStream());
	}
	
}

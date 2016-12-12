package source.api.aangepast;

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

import api.datastructs.Location;
import source.Station;
import source.VerlorenVoorwerp;


public class Parser {
	private static Document getDocument(URL url) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		DocumentBuilder db = dbf.newDocumentBuilder();
		return db.parse(url.openConnection().getInputStream());
	}

	private static Station readStation(Node n) {
		NamedNodeMap m = n.getAttributes(); 
		return new Station(
				0,
				n.getFirstChild().getNodeValue(), 
				new ArrayList<>());
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
}
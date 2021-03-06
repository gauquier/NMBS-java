package api;


import java.util.ArrayList;

import api.DataLoader;
import api.URLHelper;
import dao.*;
import source.*;

public class TripLoader {
	
	 public static ArrayList<Trip> getTrips(String departure, String arrival, String datum) throws Exception
	   {
		 	String URLRequest = URLHelper.URLMaker("https://api.irail.be/connections/?from=", departure, arrival, datum, false);
		 	System.out.println(URLRequest);
			if(DataLoader.checkURL(URLRequest)== true)
			{
				 String input = DataLoader.getHTML(URLRequest);
				 return tripCatcher(input);
			}
			else
			{
				System.out.println("er is een fout opgetreden");
				
			}
			return null;
	   }
	 public static ArrayList<Trip> getTrips(String departure, String arrival, String datum, String tijd, boolean sel) throws Exception
	   {
		 	String URLRequest = URLHelper.URLMaker("https://api.irail.be/connections/?from=", departure, arrival, datum, tijd, sel);
		 	System.out.println(URLRequest);
		 	if(DataLoader.checkURL(URLRequest)== true)
			{
				 String input = DataLoader.getHTML(URLRequest);
				 return tripCatcher(input);
			}
			else
			{
				System.out.println("er is een fout opgetreden");
				
			}
			return null;
	   }
	
	public static ArrayList<Trip> tripCatcher(String input)
		{
		
		 int noTrips = Parser.getNoTrips(input);
		 ArrayList<Trip> tripList = new ArrayList<Trip>();
		for(int i = 0; i < noTrips; i++)
		{
			String reis = Parser.GetTrip(input,i);					
			StringBuilder builder = new StringBuilder();
			builder.append(reis);
			builder.delete(builder.indexOf("</departure>"), builder.length());

			Vertrek vertrek = new Vertrek();
			vertrek.setStation(Parser.getStation(builder.toString()));
			vertrek.setVertraging(Parser.getDelay(builder.toString()));
			vertrek.setRichting(Parser.getDirection(builder.toString()));
			vertrek.setDatum(Parser.getDate(builder.toString()));
			vertrek.setTijd(Parser.getTime(builder.toString()));
			vertrek.setTrein(Parser.getVehicle(builder.toString()));
			vertrek.setPerron(Parser.getPlatform(builder.toString()));
			vertrek.setCanceled(Boolean.parseBoolean(Parser.getState(builder.toString())));

			builder.append(reis);
			builder.delete(0, builder.indexOf("<arrival"));
			builder.delete(builder.indexOf("</arrival>"), builder.length());
			Aankomst aankomst = new Aankomst();
			aankomst.setStation(Parser.getStation(builder.toString()));
			aankomst.setVertraging(Parser.getDelay(builder.toString()));
			aankomst.setRichting(Parser.getDirection(builder.toString()));
			aankomst.setDatum(Parser.getDate(builder.toString()));
			aankomst.setTijd(Parser.getTime(builder.toString()));
			aankomst.setTrein(Parser.getVehicle(builder.toString()));
			aankomst.setPerron(Parser.getPlatform(builder.toString()));
			aankomst.setCanceled(Boolean.parseBoolean(Parser.getState(reis.toString())));

			Trip trip = new Trip(i, vertrek, aankomst, Parser.getDuration(reis.toString()));

			builder.append(reis);
			if(builder.indexOf("<vias")!= -1)
			{
				builder.delete(0, builder.indexOf("<vias")+6);
				builder.delete(0, builder.indexOf(">")+1);
				builder.delete(builder.indexOf("</vias>"), builder.length());

				int noVias = Parser.getNoVias(builder.toString());
				for(int j = 0; j < noVias; j++)
				{
					StringBuilder via = new StringBuilder();
					String overstap = Parser.GetVia(builder.toString(), j);

					via.append(overstap);
					via.delete(0, via.indexOf("<departure"));
					via.delete(via.indexOf("</departure>"), via.length());
					
					Vertrek viaVertrek = new Vertrek();
					viaVertrek.setVertraging(Parser.getDelay(via.toString()));
					viaVertrek.setTijd(Parser.getTime(via.toString()));
					viaVertrek.setPerron(Parser.getPlatform(via.toString()));
					viaVertrek.setCanceled(Boolean.parseBoolean(Parser.getState(via.toString())));
					
					
					via.append(overstap);
					via.delete(0, via.indexOf("<arrival"));
					via.delete(via.indexOf("</arrival>"), via.length());
					
					Aankomst viaAankomst = new Aankomst();
					viaAankomst.setVertraging(Parser.getDelay(via.toString()));
					viaAankomst.setTijd(Parser.getTime(via.toString()));
					viaAankomst.setPerron(Parser.getPlatform(via.toString()));
					viaAankomst.setCanceled(Boolean.parseBoolean(Parser.getState(via.toString())));

					via.append(overstap);
					via.delete(0, via.indexOf("</arrival>"));
					viaAankomst.setStation(Parser.getStation(via.toString()));
					viaVertrek.setTrein(Parser.getStation(via.toString()));
					viaVertrek.setRichting(Parser.getDirection(via.toString()));

					Via stepOff = new Via(viaVertrek, viaAankomst);
					trip.addVia(stepOff);
				}
				
			}
			tripList.add(trip);
		}
		return tripList;
		}

}

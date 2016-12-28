package iRailExamples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class IRailTest {
	public static String getHTML(String urlToRead) throws Exception {
	      StringBuilder result = new StringBuilder();
	      URL url = new URL(urlToRead);
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setRequestMethod("GET");
	      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	      String line;
	      while ((line = rd.readLine()) != null) {
	         result.append(line);
	      }
	      rd.close();
	      return result.toString();
	   }

	private static String URLMaker(String base, String departure, String arrival ){
        StringBuilder builder = new StringBuilder();
        builder.append(base);
        builder.append(STRFormatter(departure));
        builder.append("&to=");
        builder.append(STRFormatter(arrival));
        builder.append("&lang=nl");
        return builder.toString();
}
	private static String URLMaker(String base, String request){
        StringBuilder builder = new StringBuilder();
        builder.append(base);
        builder.append(STRFormatter(request));
        builder.append("&fast=true");
        return builder.toString();
        //"https://api.irail.be/liveboard/?station=LIEDEKERKE&fast=true"
}
	private static String STRFormatter(String input)
	{
		StringBuilder builder = new StringBuilder();
        builder.append(input);
		for(int i = 0; i< input.length();i++)
		{
			if(input.charAt(i)==' '||input.charAt(i)=='-' )
			{
				 builder.deleteCharAt(i);
				builder.insert(i,"%20");
			}
		}
		return builder.toString();
	}
	
	   public static void main(String[] args) throws Exception
	   {
		 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		 int keuze = 2; 
		 switch(keuze)
		 {
		 	case 1://tussen twee stations
			    System.out.print("Please enter departure station : ");
			    String departure = null;
			    try {
			        departure = reader.readLine();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
			    System.out.println("You entered : " + departure);
			    String arrival = null;
			    System.out.print("Please enter arrival station : ");
			    try {
			        arrival = reader.readLine();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
			    System.out.println("You entered : " + arrival);
		 		System.out.println(getHTML(URLMaker("https://api.irail.be/connections/?from=", departure, arrival)));
		 		break;
		 	case 2://doorkomsten van ��n station
		 		System.out.print("Please enter station : ");
			    String request = null;
			    try {
			        request = reader.readLine();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
			    System.out.println("You entered : " + request);
		 		System.out.println(getHTML(URLMaker("https://api.irail.be/liveboard/?station=",request)));
		 		break;
		 	default:
		 		break;
		 };
	    
	   }
}

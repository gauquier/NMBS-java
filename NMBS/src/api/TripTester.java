package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import api.DataLoader;
import api.URLHelper;

public class TripTester {
	public static void main(String[] args) throws Exception
	{
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
 	String URLRequest;
 	System.out.print("Please enter departure station : ");
	String departure = null;
	try
	{
	    departure = reader.readLine();
	}
	catch (IOException e)
	{
		e.printStackTrace();
	}
	System.out.println("You entered : " + departure);
	String arrival = null;
	System.out.print("Please enter arrival station : ");
	try
	{
		arrival = reader.readLine();
	}
	catch (IOException e)
	{
		e.printStackTrace();
	}
	System.out.println("You entered : " + arrival);
	TripLoader.getTrips(departure, arrival);
	}
	
}

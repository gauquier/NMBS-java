package api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LiveBoardLoader {
	public static void main(String[] args) throws Exception
	{
		 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		 String URLRequest;
		 System.out.print("Please enter station : ");
		 String request = null;
		 try
		 {
			 request = reader.readLine();
		 }
		 catch (IOException e)
		 {
			 e.printStackTrace();
		 }
		 System.out.println("You entered : " + request);
		 URLRequest = URLHelper.URLMaker("https://api.irail.be/liveboard/?station=",request);
	    if(DataLoader.checkURL(URLRequest)== true)
	    {
	    	String XML = DataLoader.getHTML(URLRequest);
	        System.out.println(Parser.getNoDeps(XML));
	    }
		else
		{
		  	System.out.println("er is een fout opgetreden");
		}
		
   }
}
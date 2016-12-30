package api;

import java.util.ArrayList;

public class BillBoardLoader {
	 public static ArrayList<BillBoard> getDepartures(String station) throws Exception
	   {
		 	String URLRequest = URLHelper.URLMaker("https://api.irail.be/liveboard/?station=", station);
		 	
			if(DataLoader.checkURL(URLRequest)== true)
			{
				ArrayList<BillBoard> bills	= new ArrayList<BillBoard>();
				String input = DataLoader.getHTML(URLRequest);
				int bill = Parser.getNoBill(input);
				 for (int i = 0; i < bill; i++)
				{
					// Parser.getBill(input, i).toString();
					 bills.add(Parser.getBill(input, i));
				}
				 return bills;
			}
			else
			{
				System.out.println("er is een fout opgetreden");
				
			}
			return null;
	   }
	 public static ArrayList<BillBoard> getDepartures(String station, String datum, String tijd) throws Exception
	   {
		 	String URLRequest = URLHelper.URLMaker("https://api.irail.be/liveboard/?station=", station, datum,tijd);
		 	
			if(DataLoader.checkURL(URLRequest)== true)
			{
				ArrayList<BillBoard> bills	= new ArrayList<BillBoard>();
				String input = DataLoader.getHTML(URLRequest);
				int bill = Parser.getNoBill(input);
				 for (int i = 0; i < bill; i++)
				{
					// Parser.getBill(input, i).toString();
					 bills.add(Parser.getBill(input, i));
				}
				 return bills;
			}
			else
			{
				System.out.println("er is een fout opgetreden");
				
			}
			return null;
	   }
}

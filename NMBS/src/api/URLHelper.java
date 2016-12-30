package api;

import java.util.Date;

public abstract class URLHelper {
	public static String URLMaker(String base, String departure, String arrival, String datum, boolean sel ){
        StringBuilder builder = new StringBuilder();
        builder.append(base);
        builder.append(STRFormatter(departure));
        builder.append("&to=");
        builder.append(STRFormatter(arrival));
        builder.append("&lang=nl");
        builder.append("&date=");
        builder.append(datum);
        return builder.toString();
}
	
	public static String URLMaker(String base, String departure, String arrival, String datum, String time, boolean sel ){
        StringBuilder builder = new StringBuilder();
        builder.append(base);
        builder.append(STRFormatter(departure));
        builder.append("&to=");
        builder.append(STRFormatter(arrival));
        builder.append("&lang=nl");
        builder.append("&date=");
        builder.append(datum);
        builder.append("&time=");
        builder.append(time);
        builder.append("&timeSel=");
        builder.append(timeSelFormat(sel));
        return builder.toString();
}
	public static String timeSelFormat(boolean input)
	{
		if (input == true)
		{
			return "arrive";
		}
		else
		{
			return "depart";
		}
	}

	public static String URLMaker(String base, String request){
        StringBuilder builder = new StringBuilder();
        builder.append(base);
        builder.append(STRFormatter(request));
        System.out.println(builder.toString());
        builder.append("&fast=true");
        return builder.toString();
}
	public static String URLMaker(String base, String request, String datum, String tijd){
        StringBuilder builder = new StringBuilder();
        builder.append(base);
        builder.append(STRFormatter(request));
        builder.append("&fast=true");
        builder.append("&date=");
        builder.append(datum.toString());
        builder.append("&time=");
        builder.append(tijd);
        return builder.toString();
}
	
	public static String STRFormatter(String input)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(input);
		if(builder.indexOf("/") != -1)
		{
		builder.delete(builder.indexOf("/"), builder.length());
		}
		int index = builder.indexOf("-");
	    while (index != -1)
	    {
	        builder.replace(index, index + 1, "%20");
	        index += 3; // Move to the end of the replacement
	        index = builder.indexOf("-");
	       
	    }
	    
	    index = builder.indexOf(" ");
	    while (index != -1)
	    {
	        builder.replace(index, index + 1, "%20");
	        index += 3; // Move to the end of the replacement
	        index = builder.indexOf(" ");
	    }
	    return builder.toString();
	}

}
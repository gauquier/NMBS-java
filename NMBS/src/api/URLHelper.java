package api;

import java.util.Date;

public abstract class URLHelper {
	public static String URLMaker(String base, String departure, String arrival, boolean sel ){
        StringBuilder builder = new StringBuilder();
        builder.append(base);
        builder.append(STRFormatter(departure));
        builder.append("&to=");
        builder.append(STRFormatter(arrival));
        builder.append("&lang=nl");
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
        builder.append(dateFormat(datum));
        builder.append("&time=");
        builder.append(timeFormat(time));
        builder.append("&timeSel=");
        builder.append(timeSelFormat(sel));
        return builder.toString();
}
	public static String timeSelFormat(boolean input)
	{
		if (input = true)
		{
			return "arrive";
		}
		else
		{
			return "depart";
		}
	}
	public static String timeFormat(String input)
	{
		return "1218";
	}
	public static String dateFormat(String input)
	{
		return "281216";
	}
	public static String URLMaker(String base, String request){
        StringBuilder builder = new StringBuilder();
        builder.append(base);
        builder.append(STRFormatter(request));
        builder.append("&fast=true");
        System.out.println(builder.toString());
        return builder.toString();
        //"https://api.irail.be/liveboard/?station=LIEDEKERKE&fast=true"
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
        System.out.println(builder.toString());
        return builder.toString();
        //"https://api.irail.be/liveboard/?station=LIEDEKERKE&fast=true"
}
	public static String URLMaker(String base, String request, String datum){
        StringBuilder builder = new StringBuilder();
        builder.append(base);
        builder.append(STRFormatter(request));
        builder.append("&fast=true");
        builder.append("&date=");
        builder.append(datum.toString());
        System.out.println(builder.toString());
        return builder.toString();
        //"https://api.irail.be/liveboard/?station=LIEDEKERKE&fast=true"
}
	public static String STRFormatter(String input)
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

}
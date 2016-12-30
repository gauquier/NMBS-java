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
	public static String URLMaker(String base, String request, String datum){
        StringBuilder builder = new StringBuilder();
        builder.append(base);
        builder.append(STRFormatter(request));
        builder.append("&fast=true");
        builder.append("&date=");
        builder.append(datum.toString());
        return builder.toString();
}
	public static String STRFormatter(String input)
	{
		StringBuilder builder = new StringBuilder();
        builder.append(input);
        int lengte = input.length();
		for(int i = 0; i< lengte;i++)
		{
			if(input.charAt(i)==' '||input.charAt(i)=='-' )
			{
				 builder.deleteCharAt(i);
				builder.insert(i,"%20");
				System.out.println(builder);
				i=i+2;
				lengte=lengte+2;
			}
		}
		return builder.toString();
	}

}
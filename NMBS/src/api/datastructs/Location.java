package api.datastructs;

import java.io.Serializable;

/**
 *
 * @author pieterc
 */
public class Location implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1053961404468635331L;
	private double longitude, latitude;

    public Location(double longitude, double latitude)
    {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public double getLatitude()
    {
        return latitude;
    }
}
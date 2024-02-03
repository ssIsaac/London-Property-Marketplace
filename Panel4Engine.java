import java.lang.Math;
import java.util.ArrayList;

/**
 * This is the calculation of the distance bteween two borough's high street by using haversine formula
 *
 * @author Tingchen Chen
 * @30/03/2022
 */
public class Panel4Engine
{
    
    private AirbnbViewer mainEngine;
    private ArrayList<AirbnbListing> highStreets;
    /**
     * Constructor for objects of class Panel4Engine
     */
    public Panel4Engine()
    {
        mainEngine = new AirbnbViewer();
        highStreets = mainEngine.getHighStreet();
    }
    
    public void run()
    {
        AirbnbListing camdenHighSt = getHighSt("Camden");
        AirbnbListing southwarkHighSt = getHighSt("Southwark");
        System.out.println(getDistance(camdenHighSt, southwarkHighSt) + " km");
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public double getDistance(AirbnbListing lis1,AirbnbListing lis2)
    {
        double lat1 = lis1.getLatitude();
        double lon1 = lis1.getLongitude();
        
        double lat2 = lis2.getLatitude();
        double lon2 = lis2.getLongitude();
        
        
        final double RADIUS = 6371;
        double radLat = Math.toRadians(lat2-lat1);
        double radLon = Math.toRadians(lon2-lon1);
        
        double haversine = Math.sin(radLat/2) * Math.sin(radLat/2) +
        Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * 
        Math.sin(radLon/2) * Math.sin(radLon/2);
        
        double haversine1 = 2 * Math.atan2(Math.sqrt(haversine), Math.sqrt(1-haversine));
        double haversine2 = RADIUS * haversine1;
        return haversine2;
    
    }
    
    public AirbnbListing getHighSt(String boroughName)
    {
        for (AirbnbListing listing : highStreets) {
            if (boroughName.equals(listing.getNeighbourhood())) {
                return listing;
            }
        }
        // if not found, return null.
        return null; 
    }

}
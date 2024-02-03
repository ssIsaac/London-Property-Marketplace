
/**
 * Write a description of class AirbnbGUIListing here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AirbnbGUIListing extends AirbnbListing
{
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class AirbnbGUIListing
     */
    public AirbnbGUIListing(String id, String name, String host_id,
                         String host_name, String neighbourhood, double latitude,
                         double longitude, String room_type, int price,
                         int minimumNights, int numberOfReviews, String lastReview,
                         double reviewsPerMonth, int calculatedHostListingsCount, int availability365)
    {
        super(id,name, host_id, host_name, neighbourhood, latitude, longitude,
        room_type, price, minimumNights, numberOfReviews, lastReview,
        reviewsPerMonth, calculatedHostListingsCount, availability365);
    }

    public String displayInfoInList()
    {
        return (this.getPrice() + " per Night\n" +
                "Minimum: " + this.getMinimumNights() + " Nights\n"
                + this.getNumberOfReviews() + " Reviews");
    }
    
    public String displayDetails()
    {
        return (this.getHost_name() + "\n" +
                this.getPrice() + " per Night\n" +
                "Minimum: " + this.getMinimumNights() + " Nights\n"
                + this.getNumberOfReviews() + " Reviews\n\n" +
                this.getName());
    }
}

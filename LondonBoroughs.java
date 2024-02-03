import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.HashSet;

/**
 * This class is stores all the London Boroughs and its statistics
 *
 * @author Shun Sheng Lee, K21081997
 * @version 30.3.2022
 */
public class LondonBoroughs 
{
    //Stores all properties under the same borough
    private ArrayList <AirbnbListing> LDNborough;
    //Stores the data from BoroughDataSet
    private ArrayList <BoroughDataListing> boroughData;    
    //HashMap that returns the property name when its price is entered
    private HashMap <Integer, String> borough;

    private String mostExp;

    private MapEngine engine;
    //Stores all borough names
    private HashSet <String> boroughName;
    //Loads data from the second data set
    private BoroughDataLoader loader;

    /**
     * Constructor for objects of class LondonBoroughs
     */
    public LondonBoroughs(MapEngine mapEngine)
    {

        LDNborough = new ArrayList<>();
        boroughData = new ArrayList <>();

        borough = new HashMap <Integer, String> ();
        engine = mapEngine;

        boroughName = new HashSet<>();
        boroughName = getBoroughs();

        loader = new BoroughDataLoader();

        //stores the data from BoroughDataSet.csv
        boroughData = loader.load(); 
        //initialises methods
        makeHashMap();
        getBoroughs();
        mostExpensiveBorough();

    }

    /**
     * creates a HashMap that stores the price of a borough and its name
     */
    public void makeHashMap() 
    {
        borough.put (getBoroughPrice("Enfield"), "Enfield");
        borough.put (getBoroughPrice("Barnet"), "Barnet");
        borough.put (getBoroughPrice("Haringey"), "Haringey");
        borough.put (getBoroughPrice("Waltham Forest"), "Waltham Forest");
        borough.put (getBoroughPrice("Harrow"), "Harrow");
        borough.put (getBoroughPrice("Brent"), "Brent");
        borough.put (getBoroughPrice("Camden"), "Camden");
        borough.put (getBoroughPrice("Islington"), "Islington");
        borough.put (getBoroughPrice("Hackney"), "Hackney");
        borough.put (getBoroughPrice("Havering"), "Havering");
        borough.put (getBoroughPrice("Hillingdon"), "Hillingdon");
        borough.put (getBoroughPrice("Ealing"), "Ealing");
        borough.put (getBoroughPrice("Kensington and Chelsea"), "Kensington and Chelsea");
        borough.put (getBoroughPrice("Westminster"), "Westminster");
        borough.put (getBoroughPrice("Tower Hamlets"), "Tower Hamlets");
        borough.put (getBoroughPrice("Newham"), "Newham");
        borough.put (getBoroughPrice("Barking and Dagenham"), "Barking and Dagenham");
        borough.put (getBoroughPrice("Hounslow"), "Hounslow");
        borough.put (getBoroughPrice("Hammersmith and Fulham"), "Hammersmith and Fulham");
        borough.put (getBoroughPrice("Wandsworth"), "Wandsworth");
        borough.put (getBoroughPrice("City of London"), "City of London");
        borough.put (getBoroughPrice("Greenwich"), "Greenwich");
        borough.put (getBoroughPrice("Bexley"), "Bexley");
        borough.put (getBoroughPrice("Richmond upon Thames"), "Richmond upon Thames");
        borough.put (getBoroughPrice("Merton"), "Merton");
        borough.put (getBoroughPrice("Lambeth"), "Lambeth");
        borough.put (getBoroughPrice("Southwark"), "Southwark");
        borough.put (getBoroughPrice("Lewisham"), "Lewisham");
        borough.put (getBoroughPrice("Kingston upon Thames"), "Kingston upon Thames");
        borough.put (getBoroughPrice("Sutton"), "Sutton");
        borough.put (getBoroughPrice("Croydon"), "Croydon");
        borough.put (getBoroughPrice("Bromley"), "Bromley");

    }

    /**
     * @return a HashSet of neighbourhood/borough
     */
    private HashSet <String> getBoroughs ()
    {
        ArrayList <AirbnbListing> listing = new ArrayList <> ();
        listing = engine.getList();
        HashSet <String> neighbourhoods = new HashSet <> ();

        int index = 0;
        for (AirbnbListing list : listing){
            String neighbourhood = listing.get(index).getNeighbourhood();
            neighbourhoods.add (neighbourhood);
            index++;
        }
        return neighbourhoods;
    }

    /**
     * @param name of the borough
     * @return an arraylist of properties of a specific borough within (or not) the given price range
     */
    public ArrayList <AirbnbListing> getProperty (String borough)
    {
        LDNborough.clear();
        LDNborough = engine.propertiesInBoroughList (borough);
        return LDNborough;
    }

    /**
     * @param name of the borough
     * @return the average number of reviews per property within a borough
     */
    public int getAverageNumberOfReviewsPerProperty  (String borough)
    {
        ArrayList <AirbnbListing> listed = new ArrayList <> ();
        listed = getProperty(borough);
        if (listed.size() == 0) {
            return 0;
        }
        else {
            int index = 0;
            int reviews = 0;
            while (index < listed.size()){
                AirbnbListing listing = listed.get(index);
                int reviews1 = listing.getNumberOfReviews();
                reviews = reviews + reviews1;
                index++;
            }
            return reviews/listed.size();
        }
    }

    /**
     * @param name of the borough
     * @return the total number of available properties within a borough
     */
    public int getTotalNumberOfAvailablePropertiesInABorough (String borough)
    {   
        ArrayList <AirbnbListing> available= new ArrayList<AirbnbListing> ();
        ArrayList <AirbnbListing> listed = new ArrayList <> ();
        listed = getProperty(borough);
        int index = 0;
        while (index < listed.size())
        {
            AirbnbListing listing = listed.get (index);
            if (listing.getAvailability365() ==0){
                index++;
            }
            else {
                available.add (listing);
                index++;
            }
        }
        return available.size();
    }

    /**
     * @param name of the borough
     * @return number of entire ggome and apartments within a borough
     */
    public int numberOfEntireHomeAndApartments (String borough) 
    {
        ArrayList <AirbnbListing> entire= new ArrayList<AirbnbListing> ();
        ArrayList <AirbnbListing> listed = new ArrayList <> ();
        listed = getProperty(borough);
        int count = 0;
        for (AirbnbListing bnb : listed) {
            if (bnb.getRoom_type().equals("Entire home/apt")) {
                count++;
            }
        }
        return count;
    }

    /**
     * @param name of the borough
     * @return the total minimum price to stay in each and every property of a specific borough
     */
    private int getBoroughPrice (String borough)
    {
        ArrayList <AirbnbListing> listed = new ArrayList <> ();
        listed = getProperty(borough);
        int index = 0;
        int boroughPrice = 0 ;
        while (index < listed.size()){
            AirbnbListing list1 = listed.get(index);
            int minNights = list1.getMinimumNights();
            int price = list1.getPrice();
            int propertyPrice = minNights * price;
            boroughPrice = boroughPrice + propertyPrice;
            index++;
        }
        return boroughPrice;

    }

    /**
     * @return the highest value of the getBoroughPrice() method
     */
    private int getValueOfMostExpensiveBorough ()
    {   
        int index = 0;
        int value = 0;
        int maxValue = 0;
        String name = null;
        Iterator <String> it = boroughName.iterator();
        while(it.hasNext()){
            name = it.next();
            value = getBoroughPrice(name);
            if (value > maxValue){
                maxValue = value;
                index++;
            }
            else if (value < maxValue){
                index++;
            }
            else{
                index++;
            }
        }
        return maxValue;
    }  

    /**
     * @return the most expensive borough: the borough that requires the most money if 
     * a user was to stay in each and every single property 
     */
    private void mostExpensiveBorough ()
    {
        int priceBorough = 0;
        int index = 0;
        String name = null;
        Iterator <String> it = boroughName.iterator();
        while(it.hasNext()){
            name = it.next();
            if (getBoroughPrice(name) == getValueOfMostExpensiveBorough()){
                mostExp = borough.get(getBoroughPrice(name));
                break;
            }
        }
    }

    public String getMostExpensiveBorough()
    {
        return mostExp;
    }

    /**
     * @param name of the borough
     * @return most number of property listing in the borough
     */
    public String hostWithMostListing (String borough)
    {
        ArrayList <AirbnbListing> listed = new ArrayList <> ();
        listed = getProperty(borough);
        int count = 0;
        int mostCount = 0;
        String bigHostName = null;
        for (AirbnbListing bnb: listed){
            count = bnb.getCalculatedHostListingsCount();
            if (count > mostCount) {
                mostCount = count;
                bigHostName = bnb.getHost_name();
            }
        }
        return bigHostName;
    }

    /**
     * @param name of the borough
     * @return ranking of borough by crime rate
     */
    public int getCrimeRateRanking (String borough)
    {
        int index = 0;
        int rank = 0;
        for (BoroughDataListing data : boroughData ){
            if (data.getName().toLowerCase().trim().equals (borough.toLowerCase().trim())){
                rank = data.getCrimeRateRanking();
            }
            else {
                index++;
            }
        }
        return rank;
    }

    /**
     * @param name of the borough
     * @return tourist attraction of the borough
     */

    public String getTouristAttraction (String borough)
    {
        int index = 0;
        String attraction = null;
        for (BoroughDataListing data : boroughData ){
            if (data.getName().equals (borough)){
                attraction = data.getAttraction();
            }
            else {
                index++;
            }
        }
        return attraction; 
    }

    /**
     * @param name of the borough
     * @return the population density
     */
    public int getPopulationDensity (String borough)
    {
        int index = 0;
        int rank = 0;
        for (BoroughDataListing data : boroughData ){
            if (data.getName().equals (borough)){
                rank = data.getPopulationDensity();
            }
            else {
                index++;
            }
        }
        return rank;
    }
}
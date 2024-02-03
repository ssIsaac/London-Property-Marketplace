import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * MapEngine processes all functionalities of the map
 * panel underneath the GUI.
 *
 * @author Max Chang
 * @version 26.03.2022
 */
public class MapEngine 
{
    
    private ArrayList <AirbnbListing> allListings;
    private ArrayList <AirbnbListing> pricedListings;
    private ArrayList <AirbnbListing> highStreets;
    
    /**
     * Constructor for objects of class MapEngine.
     */
    public MapEngine(ArrayList <AirbnbListing> allListings, ArrayList <AirbnbListing> pricedListings
    , ArrayList <AirbnbListing> highStreets)
    {
        this.allListings = allListings;
        this.pricedListings = pricedListings;
        this.highStreets = highStreets;
    }
    
    /**
     * Get all listings.
     * @return ArrayList of all listings.
     */
    public ArrayList <AirbnbListing> getList ()
    {
        return allListings;
    }
    
    /**
     * Sets new priced listings with a different price quota.
     * @param newPricedListings The new priced listing.
     */
    public void setPricedListings(ArrayList<AirbnbListing> newPricedListings)
    {
        pricedListings = newPricedListings;
    }
    
    /**
     * Generates a list of the properties within a specified borough,
     * it use the list of all properties if a price range is not selected.
     * 
     * @param borough The name of the borough selected.
     * @return A list of all the AirbnbListings within that borough.
     */
    public ArrayList<AirbnbListing> propertiesInBoroughList(String borough)
    {
        if (pricedListings.isEmpty()) { 
            // Filter all listings according to its borough (user has not specified price range)
            return pricedListings;
        }
        else { 
            // Filter all listings according to its borough and price range
            return boroughListFilter(borough, pricedListings);
        }
    }
    
    /**
     * Filters out listings according to its neighbourhood name.
     * 
     * @param borough The name of the borough selected.
     * @param listings The list to filter from.
     * @return A list of all the AirbnbListings within that borough.
     */
    private ArrayList<AirbnbListing> boroughListFilter(String borough, ArrayList<AirbnbListing> listings)
    {

        ArrayList<AirbnbListing> outputList = new ArrayList<>();
        outputList.addAll(listings);
        outputList.removeIf(s -> !(borough.equals(s.getNeighbourhood()))); // remove listing if name does not match
        
        return outputList;        

    }
    
    /**
     * Counts the number of listings in a given borough.
     * @param borough The name of the borough.
     * @return The number of listings.
     */
    public int boroughListCount(String borough)
    {

        ArrayList<AirbnbListing> outputList = new ArrayList<>();
        outputList.addAll(allListings);
        outputList.removeIf(s -> !(borough.equals(s.getNeighbourhood()))); // remove listing if name does not match
        
        return outputList.size();        

    }
    
    /**
     * Returns the borough with the most listings.
     * @return The number of listings in the biggest borough.
     */
    public int biggestBorough()
    {
        ArrayList<Integer> counts = new ArrayList<>();
        for (AirbnbListing highStreet : highStreets) {
            counts.add(boroughListCount(highStreet.getNeighbourhood()));
        }
        return (int) Collections.max(counts);
    }
    
    /**
     * A count of all the properties within a borrow.
     * 
     * @param borough The name of the borough selected.
     * @return The number of properties in that borough.
     */
    public int propertiesInBoroughInt(String borough)
    {
        return propertiesInBoroughList(borough).size();
    }
    
    /**
     * Return a particular AirbnbListing.
     * 
     * @param index The index of the listing in the list.
     * @param listing The listing to select from.
     * 
     * @return The AirbnbListing specified.
     */
    public AirbnbListing getHighStreetListing(String borough)
    {
        for (AirbnbListing highStreet: highStreets) {
            if (borough.equals(highStreet.getNeighbourhood())) {
                return highStreet;
            }
        }
        return null;
    }
    
    /**
     * A merge sort algorithm to sort a list of AirbnbListings
     * 
     * @param listings A list of AirbnbListings.
     * @param sortBy The parameter to sort the list by ("Price", "Review", "MinNight", "Name").
     * @return A list of sorted AirbnbListings.
     *
     */
    public List<AirbnbListing> mergeSortAsc(List<AirbnbListing> listings, String sortBy)
    {
        if (listings.size() <= 1) {
            // Base Case
            return listings;
        }
        else {
            // Splits List of listings into two halves
            List<AirbnbListing> leftSortList = listings.subList(0, listings.size()/2);
            List<AirbnbListing> rightSortList = listings.subList(listings.size()/2, listings.size());
            
            //Recursively calls function to split further
            leftSortList = mergeSortAsc(leftSortList, sortBy);
            rightSortList = mergeSortAsc(rightSortList, sortBy);
            
            // Merges function
            return merge(leftSortList, rightSortList, sortBy);
        }
    }
    
    /**
     * Getter for list of all high streets.
     */
    public List<AirbnbListing> getHighStreet()
    {
        return highStreets;
    }
    
    /**
     * Merges the AirbnbListings in ascending order (a-z, 0-9)
     * 
     * @param leftSortList The left half of the list to be sorted.
     * @param rightSortList The right half of the list to be sorted.
     * @param sortBy The parameter to sort the list by.
     * 
     * @return A part of a sorted list of AirbnbListings.
     */
    private List<AirbnbListing> merge(List<AirbnbListing> leftSortList, List<AirbnbListing> rightSortList, String sortBy)
    {
        List<AirbnbListing> outputList = new ArrayList<>();
        
        List<AirbnbListing> duplicateLeft = new ArrayList<>();
        List<AirbnbListing> duplicateRight= new ArrayList<>();
        
        // Duplicates lists to avoid Concurrent Modification Exception
        duplicateLeft.addAll(leftSortList);
        duplicateRight.addAll(rightSortList);
        
        // Switch to sort by different categories
        switch(sortBy) {
            case "Price":
                //While loop for when both halves of the list are not empty
                while (!(duplicateLeft.isEmpty()) && !(duplicateRight.isEmpty())) {
                    if (duplicateLeft.get(0).getPrice() <= duplicateRight.get(0).getPrice()) { // Gets value from first element of the list
                        outputList.add(duplicateLeft.remove(0)); // Adds to output list and removes the first element
                    }
                    else {
                        outputList.add(duplicateRight.remove(0));
                    }
                }
                break;
                    
            case "Reviews":
                while (!(duplicateLeft.isEmpty()) && !(duplicateRight.isEmpty())) {
                    if (duplicateLeft.get(0).getNumberOfReviews() <= duplicateRight.get(0).getNumberOfReviews()) {
                        outputList.add(duplicateLeft.remove(0));
                    }
                    else {
                        outputList.add(duplicateRight.remove(0));
                    }
                }
                break;
            case "MinNight":
                while (!(duplicateLeft.isEmpty()) && !(duplicateRight.isEmpty())) {
                    if (duplicateLeft.get(0).getMinimumNights() <= duplicateRight.get(0).getMinimumNights()) {
                        outputList.add(duplicateLeft.remove(0));
                    }
                    else {
                        outputList.add(duplicateRight.remove(0));
                    }
                }
                break;
                
            case "Host Name":
                while (!(duplicateLeft.isEmpty()) && !(duplicateRight.isEmpty())) {
                    // Compares the strings Unicode values (the lesser value has a higher alphabet)
                    if (duplicateLeft.get(0).getHost_name().compareTo(duplicateRight.get(0).getHost_name()) <= 0) { 
                        outputList.add(duplicateLeft.remove(0));
                    }
                    else {
                        outputList.add(duplicateRight.remove(0));
                    }
                }
                break; 
        }
        
        while(!duplicateLeft.isEmpty()) {
            // Adds any remaining elements to the list
            outputList.add(duplicateLeft.remove(0));
        }
        
        while(!duplicateRight.isEmpty()) {
            outputList.add(duplicateRight.remove(0));
        }
        
        return outputList;
    }
}

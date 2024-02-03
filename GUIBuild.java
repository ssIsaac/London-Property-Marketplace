import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;

/**
 * This class helps the construction
 * of the statistics box (ie. setting up the methods)
 *
 * @author Shun Sheng Lee, K21081997
 * @version 30.3.2022
 * 
 */
public class GUIBuild
{
    //list of possible displayed headers
    private List <String> header;
    //randome number generator 
    private Random rand;
    //an instance of the class LondonBoroughs
    private LondonBoroughs ldnBorough;
    //stores the name of borough
    private String boroughName;
    //name of header
    private String name1;
    private String name2;
    private String name3;
    private String name4;
    private String name5;
    private String name6;
    private String name7;
    private String name8;

    //value of statistics in string
    private String stats1;
    private String stats2;
    private String stats3;
    private String stats4;

    private Integer value;

    /**
     * Constructor for objects of class Airbnb
     */
    public GUIBuild(MapEngine mapEngine)
    {
        rand = new Random();
        header = new ArrayList <>();
        boroughName = new String ();
        
        name1 = null;
        name2 = null;
        name3 = null;
        name4 = null;
        name5 = null;
        name6 = null;
        name7 = null;
        name8 = null;
       
        makeList();
        
        setHeader1();
        setHeader2();
        setHeader3();
        setHeader4();
        
        value = 0;
        ldnBorough = new LondonBoroughs(mapEngine);
        
        stats1 = null;
        stats2 = null;
        stats3 = null;
        stats4 = null;
        
        setStatistics1();
        setStatistics2();
        setStatistics3();
        setStatistics4();

    }

    
    
    /**
     * creates an ArrayList of header for the Statistics panel
     */
    private void makeList ()
    {
        header.add ("Average number of reviews per property:");
        header.add ("Total number of available property:");
        header.add ("Number of entire home and apartments:"); 
        header.add ("The most expensive borough:");    
        header.add ("Host with the most listing in this borough:");
        header.add ("Crime rate ranking:");
        header.add ("Tourist Attraction:");
        header.add ("Population Density:");
    }

    
    
    /**
     * Setting the header for the Statistics box1 GUI
     */
    public void setHeader1()
    {
        if (name5 == null){
            int index = rand.nextInt (header.size());
            for (String head: header){
                name1 = header.get(index);
                name5 = name1;
                header.remove(index);
                break;
            }
        }
        else {
            int index = rand.nextInt (header.size());
            header.add (name1);
            for (String head: header){
                name1 = header.get(index);
                name5 = name1;
                header.remove(index);
                break;
            }
        }
    }

    
    
    /**
     * Setting the header for the Statistics box2 GUI
     */
    public void setHeader2()
    {
        if (name6 == null){
            int index = rand.nextInt (header.size());
            for (String head: header){
                name2 = header.get(index);
                name6 = name2;
                header.remove(index);
                break;
            }
        }
        else {
            int index = rand.nextInt (header.size());
            header.add (name2);
            for (String head: header){
                name2 = header.get(index);
                name6 = name2;
                header.remove(index);
                break;
            }
        }
    }

    
    /**
     * Setting the header for the Statistics box3 GUI
     */
    public void setHeader3()
    {
        if (name7 == null){
            int index = rand.nextInt (header.size());
            for (String head: header){
                name3 = header.get(index);
                name7 = name3;
                header.remove(index);
                break;
            }
        }
        else {
            int index = rand.nextInt (header.size());
            header.add (name3);
            for (String head: header){
                name3 = header.get(index);
                name7 = name3;
                header.remove(index);
                break;
            }
        }
    }

    
    /**
     * Setting the header for the Statistics box4 GUI
     */
    public void setHeader4()
    {
        if (name8 == null){
            int index = rand.nextInt (header.size());
            for (String head: header){
                name4 = header.get(index);
                name8 = name4;
                header.remove(index);
                break;
            }
        }
        else {
            int index = rand.nextInt (header.size());
            header.add (name4);
            for (String head: header){
                name4 = header.get(index);
                name8 = name4;
                header.remove(index);
                break;
            }
        }
    }

    
    /**
     * @return the header stored in name1
     */
    public String getHeader1()
    {
        return name1;
    }

    
    /**
     * @return the header stored in name2
     */
    public String getHeader2()
    {
        return name2;
    }

    
    /**
     * @return the header stored in name3
     */
    public String getHeader3()
    {
        return name3;
    }

    
    /**
     * @return the header stored in name4
     */
    public String getHeader4()
    {
        return name4;
    }

    
    
    /**
     * setting the statistic to be displayed in the GUI for stats box1
     */
    public void setStatistics1 ()
    {
        if (getHeader1().equals("Average number of reviews per property:")){
            //System.out.println(borough);
            value = ldnBorough.getAverageNumberOfReviewsPerProperty  (returnBoroughName ());
            stats1 = value.toString();
        }
        else if(getHeader1().equals("Total number of available property:")){
            value = ldnBorough.getTotalNumberOfAvailablePropertiesInABorough (returnBoroughName ());
            stats1 = value.toString();
        }
        else if (getHeader1().equals("Number of entire home and apartments:")){
            value = ldnBorough.numberOfEntireHomeAndApartments (returnBoroughName ());
            stats1 = value.toString();
        }
        else if (getHeader1().equals("Host with the most listing in this borough:")){
            stats1 = ldnBorough.hostWithMostListing (returnBoroughName ());
        }
        else if (getHeader1().equals("The most expensive borough:")){
            stats1 = ldnBorough.getMostExpensiveBorough();
        }
        else if (getHeader1().equals("Crime rate ranking:")){
            value = ldnBorough.getCrimeRateRanking(returnBoroughName ());
            stats1 = value.toString();
        }
        else if (getHeader1().equals("Tourist Attraction:")){
            stats1 = ldnBorough.getTouristAttraction(returnBoroughName ());    
        }
        else if (getHeader1().equals("Population Density:")){
            value = ldnBorough.getPopulationDensity(returnBoroughName ());
            stats1 = value.toString();
        }
    }

    
    /**
     * setting the statistic to be displayed in the GUI for stats box2
     */
    public void setStatistics2 ()
    {
        if (getHeader2().equals("Average number of reviews per property:")){
            value = ldnBorough.getAverageNumberOfReviewsPerProperty  (returnBoroughName ());
            stats2 = value.toString();
        }
        else if(getHeader2().equals("Total number of available property:")){
            value = ldnBorough.getTotalNumberOfAvailablePropertiesInABorough (returnBoroughName ());
            stats2 = value.toString();
        }
        else if (getHeader2().equals("Number of entire home and apartments:")){
            value = ldnBorough.numberOfEntireHomeAndApartments (returnBoroughName ());
            stats2 = value.toString();
        }
        else if (getHeader2().equals("Host with the most listing in this borough:")){
            stats2 = ldnBorough.hostWithMostListing (returnBoroughName ());
        }
        else if (getHeader2().equals("The most expensive borough:")){
            stats2 = ldnBorough.getMostExpensiveBorough();
        }
        else if (getHeader2().equals("Crime rate ranking:")){
            value = ldnBorough.getCrimeRateRanking(returnBoroughName ());
            stats2 = value.toString();
        }
        else if (getHeader2().equals("Tourist Attraction:")){
            stats2 = ldnBorough.getTouristAttraction(returnBoroughName ());    
        }
        else if (getHeader2().equals("Population Density:")){
            value = ldnBorough.getPopulationDensity(returnBoroughName ());
            stats2 = value.toString();
        }
    }

    
    /**
     * setting the statistic to be displayed in the GUI for stats box3
     */
    public void setStatistics3 ()
    {
        if (getHeader3().equals("Average number of reviews per property:")){
            value = ldnBorough.getAverageNumberOfReviewsPerProperty  (returnBoroughName ());
            stats3 = value.toString();
        }
        else if(getHeader3().equals("Total number of available property:")){
            value = ldnBorough.getTotalNumberOfAvailablePropertiesInABorough (returnBoroughName ());
            stats3 = value.toString();
        }
        else if (getHeader3().equals("Number of entire home and apartments:")){
            value = ldnBorough.numberOfEntireHomeAndApartments (returnBoroughName ());
            stats3 = value.toString();
        }
        else if (getHeader3().equals("Host with the most listing in this borough:")){
            stats3 = ldnBorough.hostWithMostListing (returnBoroughName ());
        }
        else if (getHeader3().equals("The most expensive borough:")){
            stats3 = ldnBorough.getMostExpensiveBorough();
        }
        else if (getHeader3().equals("Crime rate ranking:")){
            value = ldnBorough.getCrimeRateRanking(returnBoroughName ());
            stats3 = value.toString();
        }
        else if (getHeader3().equals("Tourist Attraction:")){
            stats3 = ldnBorough.getTouristAttraction(returnBoroughName ());    
        }
        else if (getHeader3().equals("Population Density:")){
            value = ldnBorough.getPopulationDensity(returnBoroughName ());
            stats3 = value.toString();
        }
    }

    
    /**
     * setting the statistic to be displayed in the GUI for stats box4
     */
    public void setStatistics4 ()
    {
        if (getHeader4().equals("Average number of reviews per property:")){
            value = ldnBorough.getAverageNumberOfReviewsPerProperty  (returnBoroughName ());
            stats4 = value.toString();
        }
        else if(getHeader4().equals("Total number of available property:")){
            value = ldnBorough.getTotalNumberOfAvailablePropertiesInABorough (returnBoroughName ());
            stats4 = value.toString();
        }
        else if (getHeader4().equals("Number of entire home and apartments:")){
            value = ldnBorough.numberOfEntireHomeAndApartments (returnBoroughName ());
            stats4 = value.toString();
        }
        else if (getHeader4().equals("Host with the most listing in this borough:")){
            stats4 = ldnBorough.hostWithMostListing (returnBoroughName ());
        }
        else if (getHeader4().equals("The most expensive borough:")){
            stats4 = ldnBorough.getMostExpensiveBorough();
        }
        else if (getHeader4().equals("Crime rate ranking:")){
            value = ldnBorough.getCrimeRateRanking(returnBoroughName ());
            stats4 = value.toString();
        }
        else if (getHeader4().equals("Tourist Attraction:")){
            stats4 = ldnBorough.getTouristAttraction(returnBoroughName ());    
        }
        else if (getHeader4().equals("Population Density:")){
            value = ldnBorough.getPopulationDensity(returnBoroughName ());
            stats4 = value.toString();
        }
    }

    
    
    /**
     * @return the statistics to be displayed in the GUI for stats box1
     */
    public String getStatistics1 ()
    {
        return stats1;
    }

    
    /**
     * @return the statistics to be displayed in the GUI for stats box2
     */
    public String getStatistics2 ()
    {
        return stats2;
    }

    
    /**
     * @return the statistics to be displayed in the GUI for stats box3
     */
    public String getStatistics3 ()
    {
        return stats3;
    }

    
    /**
     * @return the statistics to be displayed in the GUI for stats box4
     */
    public String getStatistics4 ()
    {
        return stats4;
    }

    
    /**
     * sets the borough name of the borough selected on the map 
     */
    public void setBoroughName(String borough)
    {
        boroughName = borough;
    }

    
    /**
     * @return name of borough selected
     */
    public String returnBoroughName ()
    {
        return boroughName;
    }

  
}


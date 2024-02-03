
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import com.opencsv.CSVReader;
import java.net.URISyntaxException;

/**
 * @author Shun Sheng Lee K21081997 
 * @version 30.3.2022
 */
public class BoroughDataLoader {
 
    /** 
     * Return an ArrayList containing the rows in the Borough data set csv file.
     */
    public ArrayList<BoroughDataListing> load() {
        ArrayList<BoroughDataListing> listings = new ArrayList<BoroughDataListing>();
        try{
            URL url3 = getClass().getResource("BoroughDataSet.csv");
            CSVReader reader3 = new CSVReader(new FileReader(new File(url3.toURI()).getAbsolutePath()));
            String [] line;
            //skip the first row (column headers)
            reader3.readNext();
            while ((line = reader3.readNext()) != null) {
                String name = line[0];
                int population = convertInt (line[1]);
                double size = convertDouble (line [2]);
                int populationDensity = convertInt (line [3]);
                int reportedCrime = convertInt (line[4]);
                double crimeRate = convertDouble (line[5]);
                int crimeRateRanking = convertInt (line[6]);
                String frequentlyVisitedPlace = line[7];

                BoroughDataListing listing = new BoroughDataListing (name, population, size, populationDensity,
                reportedCrime, crimeRate, 
                crimeRateRanking, frequentlyVisitedPlace);
                listings.add(listing);
            }
        } catch(IOException | URISyntaxException e){
            System.out.println("Failure! Something went wrong");
            e.printStackTrace();
        }
        return listings;
    }

    /**
     *
     * @param doubleString the string to be converted to Double type
     * @return the Double value of the string, or -1.0 if the string is 
     * either empty or just whitespace
     */
    private Double convertDouble(String doubleString){
        if(doubleString != null && !doubleString.trim().equals("")){
            return Double.parseDouble(doubleString);
        }
        return -1.0;
    }

    /**
     *
     * @param intString the string to be converted to Integer type
     * @return the Integer value of the string, or -1 if the string is 
     * either empty or just whitespace
     */
    private Integer convertInt(String intString){
        if(intString != null && !intString.trim().equals("")){
            return Integer.parseInt(intString);
        }
        return -1;
    }

}

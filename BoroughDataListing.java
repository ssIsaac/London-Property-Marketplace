/**
 * Represents one listing of a borough in London.
 * This is essentially one row in the data table. Each column
 * has a corresponding field.
 * @author Shun Sheng Lee K21081997
 * @version 30.3.2022
 */ 

public class BoroughDataListing {
    /**
     * The name of the borough
     */
    private String name;

    /**
     * The population of the selected borough
     */
    private int population;

    /**
     * The size of the borough in km squared
     */
    private double size;

    /**
     * The population density of the borough 
     */
    private int population_density;

    /**
     * The number of reported crime in two months
     */
    private int reported_crime;

    /**
     * The crime rate in two months
     */
    private double crime_rate;

    /**
     * The ranking of borough by crime rate
     */
    private int crime_rate_ranking;

    /**
     * The name of the tourist attraction
     */
    private String frequently_visited_place;

    public BoroughDataListing(String name, int population,double size, int population_density,
    int reported_crime, double crime_rate,
    int crime_rate_ranking,
    String frequently_visited_place) {
        this.name = name;
        this.population = population;
        this.size = size;
        this.population_density = population_density;
        this.reported_crime = reported_crime;
        this.crime_rate = crime_rate;
        this.crime_rate_ranking = crime_rate_ranking;
        this.frequently_visited_place = frequently_visited_place;
    }

    public String getName ()
    {
        return name;
    }

    public int getPopulation()
    {
        return population;
    }

    public double getSize()
    {
        return size;
    }

    public int getPopulationDensity()
    {
        return population_density;
    }

    public int getCrime()
    {
        return reported_crime;
    }

    public double getCrimeRate ()
    {
        return crime_rate;
    }

    public int getCrimeRateRanking ()
    {
        return crime_rate_ranking;
    }

    public String getAttraction ()
    {
        return frequently_visited_place;
    }

    @Override
    public String toString() {
        return "AirbnbListing{" +
        ", name='" + name + '\'' +
        ", population=' " + population + '\'' +
        ", size=' " + size + '\'' +
        ", population_density='" + population_density + '\'' +
        ", reported_crime='" + reported_crime + '\'' +
        ", crime_rate='" + crime_rate + '\'' +
        ", crime_rate_ranking='" + crime_rate_ranking + '\'' +
        ", frequently_visited_place=" + frequently_visited_place +
        '}';
    }
}
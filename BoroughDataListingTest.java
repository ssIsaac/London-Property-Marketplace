

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class BoroughDataListingTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BoroughDataListingTest
{
    /**
     * Default constructor for test class BoroughDataListingTest
     */
    public BoroughDataListingTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void testBoroughName()
    {
        BoroughDataListing boroughD1 = new BoroughDataListing("Barnet", 399007, 86.74, 4600, 26555, 0.066553, 26, "John Keble Church");
        assertEquals("Barnet", boroughD1.getName());

    }

    @Test
    public void testPopulation()
    {
        BoroughDataListing boroughD1 = new BoroughDataListing("Barnet", 399007, 86.74, 4600, 26555, 0.066553, 26, "John Keble Church");
        assertEquals(399007, boroughD1.getPopulation());
    }

    @Test
    public void testSize()
    {
        BoroughDataListing boroughD1 = new BoroughDataListing("Barnet", 399007, 86.74, 4600, 26555, 0.066553, 26, "John Kebble Church");
        assertEquals(86.74, boroughD1.getSize(), 0.1);
    }

    @Test
    public void testPopulationDensity()
    {
        BoroughDataListing boroughD1 = new BoroughDataListing("Barnet", 399007, 86.74, 4600, 26555, 0.066553, 26, "John Keble Church");
        assertEquals(4600, boroughD1.getPopulationDensity());
    }

    @Test
    public void testReportedCrime()
    {
        BoroughDataListing boroughD1 = new BoroughDataListing("Barnet", 399007, 86.74, 4600, 26555, 0.066553, 26, "John Keble Church");
        assertEquals(0.066553, boroughD1.getCrimeRate(), 0.1);
    }

    @Test
    public void testCrimeRank()
    {
        BoroughDataListing boroughD1 = new BoroughDataListing("Barnet", 399007, 86.74, 4600, 26555, 0.066553, 26, "John Keble Church");
        assertEquals(26, boroughD1.getCrimeRateRanking());
    }

    @Test
    public void getVIsitedPlace()
    {
        BoroughDataListing boroughD1 = new BoroughDataListing("Barnet", 39907, 86.74, 4600, 26555, 0.066553, 26, "John Keble Church");
        assertEquals("Jonh Keble Church", boroughD1.getAttraction());
    }

    @Test
    public void testAttraction()
    {
        BoroughDataListing boroughD1 = new BoroughDataListing("Barnet", 399007, 86.74, 4600, 26555, 0.066553, 26, "John Keble Church");
        assertEquals("John Keble Church", boroughD1.getAttraction());
    }

    @Test
    public void testRCrime()
    {
        BoroughDataListing boroughD1 = new BoroughDataListing("Barnet", 399007, 86.74, 4600, 26555, 0.066553, 26, "John Keble Church");
        assertEquals(26555, boroughD1.getCrime());
    }
}
package src.appActions;

import javafx.util.Pair;
import org.junit.jupiter.api.Test;
import src.logic.PriceListEntity;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Port informations actions test.
 */
class PortInformationsActionsTest {


    /**
     * The S.
     */
    Short s = 10;
    /**
     * The Price list.
     */
    PriceListEntity priceList = new PriceListEntity(1, s, s, s, s, s, s, s, s, s, s);

    /**
     * The Price list everything not available.
     */
    PriceListEntity priceListEverythingNotAvailable = new PriceListEntity(1, null, null, null, null, null, null, null, null, null, null);
    /**
     * The Price list some available some not.
     */
    PriceListEntity priceListSomeAvailableSomeNot = new PriceListEntity(1, null, s, s, null, s, s, null, s, s, null);
    /**
     * The Port informations actions.
     */
    PortInformationsActions portInformationsActions = new PortInformationsActions();

    /**
     * Test get prices all services available.
     */
    @Test
    void testGetPricesAllServicesAvailable() {
        Vector<Pair<String, String>> prices = new Vector<Pair<String, String>>();
        prices.add(new Pair<>("laundry", s.toString()));
        prices.add(new Pair<>("drying room", s.toString()));
        prices.add(new Pair<>("water", s.toString()));
        prices.add(new Pair<>("shower", s.toString()));
        prices.add(new Pair<>("sauna", s.toString()));
        prices.add(new Pair<>("place shorter than 7m", s.toString()));
        prices.add(new Pair<>("place between 7m and 12m", s.toString()));
        prices.add(new Pair<>("place between 12m and 17m", s.toString()));
        prices.add(new Pair<>("place between 17m and 20m", s.toString()));
        prices.add(new Pair<>("place longer than 20m", s.toString()));

        var returnedVector = portInformationsActions.getPrices(priceList);
        assertEquals(prices, returnedVector);
    }

    /**
     * Test get prices all services not available.
     */
    @Test
    void testGetPricesAllServicesNotAvailable() {
        Vector<Pair<String, String>> prices = new Vector<Pair<String, String>>();
        prices.add(new Pair<>("laundry", "Not available"));
        prices.add(new Pair<>("drying room", "Not available"));
        prices.add(new Pair<>("water", "Not available"));
        prices.add(new Pair<>("shower", "Not available"));
        prices.add(new Pair<>("sauna", "Not available"));
        prices.add(new Pair<>("place shorter than 7m", "Not available"));
        prices.add(new Pair<>("place between 7m and 12m", "Not available"));
        prices.add(new Pair<>("place between 12m and 17m", "Not available"));
        prices.add(new Pair<>("place between 17m and 20m", "Not available"));
        prices.add(new Pair<>("place longer than 20m", "Not available"));

        var returnedVector = portInformationsActions.getPrices(priceListEverythingNotAvailable);
        assertEquals(prices, returnedVector);
    }


    /**
     * Test get prices some services available.
     */
    @Test
    void testGetPricesSomeServicesAvailable() {
        Vector<Pair<String, String>> prices = new Vector<Pair<String, String>>();
        prices.add(new Pair<>("laundry", "Not available"));
        prices.add(new Pair<>("drying room", s.toString()));
        prices.add(new Pair<>("water", s.toString()));
        prices.add(new Pair<>("shower", "Not available"));
        prices.add(new Pair<>("sauna", s.toString()));
        prices.add(new Pair<>("place shorter than 7m", s.toString()));
        prices.add(new Pair<>("place between 7m and 12m","Not available"));
        prices.add(new Pair<>("place between 12m and 17m", s.toString()));
        prices.add(new Pair<>("place between 17m and 20m", s.toString()));
        prices.add(new Pair<>("place longer than 20m", "Not available"));

        var returnedVector = portInformationsActions.getPrices(priceListSomeAvailableSomeNot);
        assertEquals(prices, returnedVector);
    }
}
package src.appActions;

import javafx.util.Pair;
import src.app.DataBase;
import src.app.PriceListDialog;
import src.logic.PortsEntity;
import src.logic.PriceListEntity;

import java.util.Vector;

/**
 * The type Port Information Actions.
 */
public class PortInformationsActions {
    /**
     * GetPrices.
     * <p>
     * Get data to Prices Window.
     *
     * @param list PriceListEntity
     * @return the prices
     */
    public Vector<Pair<String, String>> getPrices(PriceListEntity list) {
        Vector<Pair<String, String>> prices = new Vector<Pair<String, String>>();
        if (list.getLaundry() != null) prices.add(new Pair<>("laundry", list.getLaundry().toString()));
        else prices.add(new Pair<>("laundry", "Not available"));
        if (list.getDryingRoom() != null) prices.add(new Pair<>("drying room", list.getDryingRoom().toString()));
        else prices.add(new Pair<>("drying room", "Not available"));
        if (list.getWater() != null) prices.add(new Pair<>("water", list.getWater().toString()));
        else prices.add(new Pair<>("water", "Not available"));
        if (list.getShower() != null) prices.add(new Pair<>("shower", list.getShower().toString()));
        else prices.add(new Pair<>("shower", "Not available"));
        if (list.getSauna() != null) prices.add(new Pair<>("sauna", list.getSauna().toString()));
        else prices.add(new Pair<>("sauna", "Not available"));
        if (list.getPlaceLess7M() != null) prices.add(new Pair<>("place shorter than 7m", list.getPlaceLess7M().toString()));
        else prices.add(new Pair<>("place shorter than 7m", "Not available"));
        if (list.getPlace712M() != null) prices.add(new Pair<>("place between 7m and 12m", list.getPlace712M().toString()));
        else prices.add(new Pair<>("place between 7m and 12m", "Not available"));
        if (list.getPlace1217M() != null) prices.add(new Pair<>("place between 12m and 17m", list.getPlace1217M().toString()));
        else prices.add(new Pair<>("place between 12m and 17m", "Not available"));
        if (list.getPlace1720M() != null) prices.add(new Pair<>("place between 17m and 20m", list.getPlace1720M().toString()));
        else prices.add(new Pair<>("place between 17m and 20m", "Not available"));
        if (list.getPlaceMore20M() != null) prices.add(new Pair<>("place longer than 20m", list.getPlaceMore20M().toString()));
        else prices.add(new Pair<>("place longer than 20m", "Not available"));
        return prices;
    }
}

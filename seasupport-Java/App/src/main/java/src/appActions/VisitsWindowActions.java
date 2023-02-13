package src.appActions;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.persistence.PersistenceException;
import javafx.scene.chart.PieChart;
import javafx.util.Pair;
import org.hibernate.exception.ConstraintViolationException;
import src.logic.*;
import src.app.DataBase;
import org.tinylog.Logger;


/**
 * The type Visits Window Actions.
 */
public class VisitsWindowActions {
    /**
     * AddShip.
     * <p>
     * Checking given data and add new ship to database.
     *
     * @param data Vector
     * @return the int
     */
    public int addShip(Vector<String> data){
        for(int i =0; i < data.size(); i++){
            if(data.get(i).equals(""))
                return 0;
        }
        if(shipInDataBase(data.get(0))){
            Logger.warn("Wrong input of ship CallSign");
            return 1;
        }
        if(!data.get(3).chars().allMatch( Character::isDigit )){
            Logger.warn("Wrong input of ship length");
            return 3;
        }
        int n = Integer.valueOf(data.get(3));
        if(n > 30){
            Logger.warn("Wrong input of ship length");
            return 4;
        }
        ShipOwnersEntity owner = DataBase.getInstance().getOwner(data.get(4));
        if(owner == null){
            Logger.warn("Wrong input of ship owner");
            return 6;
        }
        System.out.println(owner.getEmail());
        short len = Short.valueOf(data.get(3));
        ShipsEntity ship = new ShipsEntity(data.get(0), data.get(1), data.get(2), len, owner);
        System.out.println(ship.getShipName());
        DataBase.getInstance().addShip(ship);
        return 5;
    }

    /**
     * ShipInDataBase.
     *
     * Checking that the ship is in database.
     * @param callSign String
     */
    private boolean shipInDataBase(String callSign){
        ShipsEntity s = DataBase.getInstance().getShip(callSign);
        if(s == null) return false;
        return true;
    }

    /**
     * OwnerInDataBase.
     *
     * Checking that the shipowner is in database.
     * @param id String
     */
    private boolean ownerInDataBase(String id){
        ShipOwnersEntity s = DataBase.getInstance().getOwner(id);
        if(s == null) return false;
        return true;
    }

    /**
     * AddVisit.
     * <p>
     * Checking given data and add new visit to database.
     *
     * @param data  Vector
     * @param begin LocalDate
     * @param end   LocalDate
     * @param port  PortsEntity
     * @param user  AllUsersEntity
     * @return the int
     */
    public int addVisit(Vector<String> data, LocalDate begin, LocalDate end, PortsEntity port, AllUsersEntity user){
        if(begin == null || end == null) return 8;

        Date dateBegin = Date.valueOf(begin);
        Date dateEnd = Date.valueOf(end);

        for(int i =0; i < data.size(); i++){
            if(data.get(i).equals(""))
                return 0;
        }

        if(userInPortByDate(dateBegin, dateEnd, user)){
            Logger.warn("Wrong input of visit term");
            return 9;
        }
        if(begin.isBefore(LocalDate.now())){
            Logger.warn("Wrong input of visit begin");
            return 2;
        }
        if(begin.isAfter(end)){
            Logger.warn("Wrong input of visit end");
            return 3;
        }
        if(!shipInDataBase(data.get(2))){
            Logger.warn("Wrong input of ship");
            return 4;
        }
        if(shipInPortByDate(dateBegin, dateEnd, data.get(2))){
            Logger.warn("Wrong input of ship in visit dialog");
            return 10;
        }
        if(!captianInDataBase(data.get(3))){
            Logger.warn("Wrong input of ship in visit dialog");
            return 5;
        }

        ShipsEntity ship = DataBase.getInstance().getShip(data.get(2));
        CaptainsEntity cap = DataBase.getInstance().getCaptain(Integer.valueOf(data.get(3)));

        Integer avaliblePlaces = 0;
        if(ship.getShipLength() > 18) avaliblePlaces = port.getPlacesShipsBig() -
                getBookedPlacesNow(port, dateBegin).getValue() - getBookedPlacesBetween(port, dateBegin, dateEnd).getValue();
        else
            avaliblePlaces = port.getPlacesShipsSmall() -
                    getBookedPlacesNow(port, dateBegin).getKey() - getBookedPlacesBetween(port, dateBegin, dateEnd).getKey();
        if(avaliblePlaces == 0) return 6;

        VisitsEntity v = new VisitsEntity(dateBegin, dateEnd, port, user, ship, cap);
        DataBase.getInstance().addVisit(v);
        Logger.info("New visit added");
        return 5;
    }

    /**
     * endVisit.
     * <p>
     * End actually visit.
     *
     * @param user  AllUsersEntity
     * @param visit the visit
     */
    public void endVisit(AllUsersEntity user, VisitsEntity visit){
        java.sql.Date today = new Date(Calendar.getInstance().getTime().getTime());
        if(today.after(visit.getDateBegin())){
            visit.setDateEnd(today);
            DataBase.getInstance().addVisit(visit);
            Logger.info("Visit ended");
        }
        else{
            DataBase.getInstance().removeVisit(visit);
            Logger.info("Visit canceled");
        }

    }
    /**
     * CaptainInDataBase.
     *
     * Checking that the captain is in database.
     * @param id String
     */
    private boolean captianInDataBase(String id){
        int i_id = Integer.valueOf(id);
        CaptainsEntity c = DataBase.getInstance().getCaptain(i_id);
        if(c == null) return false;
        return true;
    }

    /**
     * AddCaptain.
     * <p>
     * Checking given data and add new captain to database.
     *
     * @param data Vector
     * @return the int
     */
    public int addCaptian(Vector<String> data){
        for(int i =0; i < data.size(); i++){
            if(data.get(i).equals(""))
                return 0;
        }
        if(data.get(2).length() != 11) {
            Logger.warn("Wrong input of pesel in new Captain dialog");
            return 1;
        }
        CaptainsEntity cap = new CaptainsEntity(data.get(0), data.get(1), data.get(2));
        try{
            DataBase.getInstance().addCaptain(cap);
            Logger.info("New captain added");
        }
        catch (PersistenceException e)
        {
            Logger.warn("Wrong input of pesel, pesel has been already used");
            return 3;
        }
        return 2;
    }

    /**
     * AddOwner.
     * <p>
     * Checking given data and add new shipowner to database.
     *
     * @param data Vector
     * @return the int
     */
    public int addOwner(Vector<String> data){
        for(int i =0; i < data.size(); i++){
            if(data.get(i).equals(""))
                return 0;
        }
        if(data.get(1).length() != 9){
            Logger.warn("Wrong input of phone number in add owner dialog");
            return 1;
        }
        if(!emailSuit(data.get(2))){
            Logger.warn("Wrong input of email in new Captain dialog");
            return 2;
        }
        if(!emailIsAvailable(data.get(2))){
            Logger.warn("Wrong input of email in new Captain dialog, it has been already used");
            return 6;
        }
        if(data.get(0).equals("private")) {
            if(data.get(5).length() != 11){
                Logger.warn("Wrong input of pesel in add owner dialog");
                return 3;
            }
            ShipOwnersEntity owner = new ShipOwnersEntity(data.get(1), data.get(2), data.get(3),
                    data.get(4), data.get(5));
            try{
                DataBase.getInstance().addOwner(owner);
            }
            catch (PersistenceException e)
            {return 7;}
            return 5;
        }
        else{
            if(data.get(0).equals("commercial") && !data.get(4).chars().allMatch( Character::isDigit )){
                Logger.warn("Wrong input of NIP in new owner dialog");
                return 4;
            }
            Integer n = Integer.valueOf(data.get(4));
            ShipOwnersEntity owner = new ShipOwnersEntity(data.get(2),
                    data.get(3), n, data.get(1));
            try{
                DataBase.getInstance().addOwner(owner);
            }
            catch (PersistenceException e)
            {return 7;}
            return 5;
        }
    }

    /**
     * emailSuit.
     *
     * Checking if given String can be an email.
     * @param email String
     */
    private boolean emailSuit(String email){
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);
        if(mat.matches()) return true;
        return false;
    }
    /**
     * emailIsAvailable.
     *
     * Checking if given email have not been used before.
     * @param email String
     */
    private boolean emailIsAvailable(String email){
        ShipOwnersEntity owner = DataBase.getInstance().getOwnerByEmail(email);
        if(owner == null)  return true;
        return false;
    }

    /**
     * GetBookedPlacesNow.
     * <p>
     * Check how much ships is now in port.
     *
     * @param port PortsEntity
     * @param date Date
     * @return the pair
     */
    public Pair<Integer, Integer> getBookedPlacesNow(PortsEntity port, Date date){
        Integer big = 0, small = 0;
        List<VisitsEntity>  visits = DataBase.getInstance().getVisitFromPort(port, date);
        for(VisitsEntity v: visits){
            if(v.getShipsEntity().getShipLength() > 18) big += 1;
            else small += 1;
        }
        Pair val = new Pair(small, big);
        return val;
    }

    /**
     * getBookedPlacesBetween.
     * <p>
     * Check how much places are booked between begin and end dates.
     *
     * @param port  PortsEntity
     * @param begin Date
     * @param end   Date
     * @return the pair
     */
    public Pair<Integer, Integer> getBookedPlacesBetween(PortsEntity port, Date begin, Date end){
        List<VisitsEntity> visits = DataBase.getInstance().getVisitByPortBetween(port, begin, end);
        Integer small = 0;
        Integer big = 0;
        for(VisitsEntity v: visits){
            if(v.getShipsEntity().getShipLength() > 18) big += 1;
            else small+= 1;
        }
        Pair<Integer, Integer> v = new Pair<>(small, big);
        return v;
    }

    /**
     * userInPortByDate.
     * <p>
     * Check that if user will be in port on this day.
     *
     * @param begin Date
     * @param end   Date
     * @param user  AllUsersEntity
     * @return the boolean
     */
    boolean userInPortByDate(Date begin, Date end, AllUsersEntity user){
        if(DataBase.getInstance().getVisit(user, begin) != null||DataBase.getInstance().getVisit(user, end) !=null) return true;
        return false;
    }

    /**
     * shipInPortByDate.
     * <p>
     * Check that if ship will be in port on this day.s
     *
     * @param begin    Date
     * @param end      Date
     * @param callSign String
     * @return the boolean
     */
    boolean shipInPortByDate(Date begin, Date end, String callSign){
        ShipsEntity ship = DataBase.getInstance().getShip(callSign);
        if(DataBase.getInstance().getVisit(ship, begin) != null||DataBase.getInstance().getVisit(ship, end) !=null) return true;
        return false;
    }
}

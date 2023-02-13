package src.appActions;

import src.logic.*;
import src.app.DataBase;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Vector;
import java.sql.Date;
import java.text.SimpleDateFormat;
import org.tinylog.Logger;

/**
 * The type Login Window Actions.
 */
public class LoginWindowActions {
    /**
     * Login.
     * <p>
     * Try to log in using given login and password.
     *
     * @param login    String
     * @param password String
     * @return the all users entity
     */
    public AllUsersEntity login(String login, String password){
        AllUsersEntity currentUser = DataBase.getInstance().getUser(login);
        if(currentUser != null){
            if(currentUser.getUserPassword().equals(password)) {
                Logger.info("Login into account {}", currentUser.getLogin());
                return currentUser;
            }
            else{
                Logger.warn("User given incorrect password");
            }
        }
        return null;
    }

    /**
     * Register.
     *
     * @param data      Vector
     * @param birthdate LocalDate
     * @return AllUsersEntity the all users entity
     */
    public AllUsersEntity register(Vector<String> data, LocalDate birthdate){
        String login = data.get(0);
        String password = data.get(1);
        String name = data.get(4);
        String surname = data.get(5);
        String phone = data.get(6);
        Date date = Date.valueOf(birthdate);
        String pesel = data.get(7);
        String userType = data.get(3);
        if(loginIsAvalible(login)){
            AllUsersEntity newUser = new AllUsersEntity(login, password, name, surname, phone, date, pesel, userType);
            DataBase.getInstance().addUser(newUser);
            return newUser;
        }
        return null;
    }

    /**
     * ConvertToDate.
     * <p>
     * Convert string representation of Date to Date format.
     *
     * @param dateStr String
     * @return the date
     */
    public Date convertToDate(String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = (Date) formatter.parse(dateStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * CheckRegData.
     * <p>
     * Checking the correctness of the given data.
     *
     * @param data Vector
     * @return int error message
     */
    public int checkRegData(Vector<String> data){
        for(int i =0; i < data.size(); i++){
            if(data.get(i).equals(""))
                return 0;
        }
        if(!loginIsAvalible(data.get(0))){
            Logger.warn("Wrong input of email in registration dialog");
            return 1;
        }
        if(!data.get(1).equals(data.get(2))){
            Logger.warn("Wrong input of password in registration dialog");
            return 2;
        }
        if(!(data.get(3).equals("normal")||data.get(3).equals("admin"))){
            Logger.warn("Wrong input of user type in registration dialog");
            return 3;
        }
        if(data.get(6).length() != 9){
            Logger.warn("Wrong input of phone number in registration dialog");
            return 4;
        }
        if(data.get(7).length() != 11){
            Logger.warn("Wrong input of pesel in registration dialog");
            return 5;
        }
        // sprawdzenie daty.
        return 7;
    }

    /**
     * LoginIsAvailable.
     * <p>
     * Checking that the login is available
     *
     * @param login String
     * @return the boolean
     */
    public boolean loginIsAvalible(String login){
        AllUsersEntity currentUser = DataBase.getInstance().getUser(login);
        if(currentUser != null)
            return false;
        return true;
    }

    /**
     * UserInPort
     * <p>
     * Checking if the user is in any port.
     *
     * @param user AllUsersEntity
     * @return the ports entity
     */
    public PortsEntity userInPort(AllUsersEntity user){
        Date today = new Date(Calendar.getInstance().getTime().getTime());
        VisitsEntity v = DataBase.getInstance().getVisit(user, today);
        if( v != null){
            PortsEntity port = v.getPortsEntity();
            return port;
        }
        return null;
    }
}

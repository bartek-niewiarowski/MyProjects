package src.appActions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import src.app.DataBase;
import src.logic.AllUsersEntity;
import src.logic.PortsEntity;
import src.logic.VisitsEntity;

import javax.sound.sampled.Port;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Vector;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * The type Login window actions test.
 */
class LoginWindowActionsTest {


    /**
     * The Mocked database.
     */
    MockedStatic<DataBase> mockedDatabase;
    /**
     * The Data base.
     */
    DataBase dataBase = mock(DataBase.class);

    /**
     * The Mocked calendar.
     */
    MockedStatic<Calendar> mockedCalendar;
    /**
     * The Calendar.
     */
    Calendar calendar = mock(Calendar.class);

    /**
     * Sets .
     */
    @BeforeEach
    void setup() {
        mockedDatabase = mockStatic(DataBase.class);
        mockedDatabase.when(DataBase::getInstance).thenReturn(dataBase);

        mockedCalendar = mockStatic(Calendar.class);
        mockedCalendar.when(Calendar::getInstance).thenReturn(calendar);
    }

    /**
     * Clean.
     */
    @AfterEach
    void clean() {
        mockedDatabase.close();
        mockedCalendar.close();
    }

    /**
     * Test login for different parameters.
     *
     * @param password           the password
     * @param expectedUserEntity the expected user entity
     */
    @ParameterizedTest
    @MethodSource("provideParamsForLogin")
    void testLoginForDifferentParameters(String password, AllUsersEntity expectedUserEntity) {
        // given
        var login = "jan";
        var correctPassword = "mypassword";
        AllUsersEntity userWithCorrectPassword = createUser(login, correctPassword);
        when(dataBase.getUser(login)).thenReturn(userWithCorrectPassword);

        // when
        LoginWindowActions loginWindowActions = new LoginWindowActions();
        AllUsersEntity returnedLogin = loginWindowActions.login(login, password);

        // then
        assertEquals(expectedUserEntity, returnedLogin);
    }

    private static Stream<Arguments> provideParamsForLogin() {
        return Stream.of(
                Arguments.of("badpassword", null),
                Arguments.of("innebadpasww", null),
                Arguments.of(null, null),
                Arguments.of("mypassword", createUser("jan", "mypassword"))
        );
    }

    private static AllUsersEntity createUser(String login, String password) {
        AllUsersEntity allUsersEntity = new AllUsersEntity();
        allUsersEntity.setLogin(login);
        allUsersEntity.setUserPassword(password);
        return allUsersEntity;
    }

    /**
     * Register with correct login.
     */
    @Test
    void RegisterWithCorrectLogin() {
        // given
        Vector<String> data = new Vector<>();
        data.add("login");
        data.add("password");
        data.add("password");
        data.add("userType");
        data.add("name");
        data.add("surname");
        data.add("phone");
        data.add("pesel");
        LocalDate localDate = LocalDate.now();
        Date date = Date.valueOf(localDate);

        AllUsersEntity expectedUser = new AllUsersEntity("login", "password","name", "surname", "phone", date, "pesel", "userType");

        var login = "login";
        when(dataBase.getUser(login)).thenReturn(null);

        // when
        LoginWindowActions loginWindowActions = new LoginWindowActions();
        AllUsersEntity newUser = loginWindowActions.register(data, localDate);

        // then
        assertEquals(expectedUser, newUser);
    }

    /**
     * Register with occupied login.
     */
    @Test
    void RegisterWithOccupiedLogin() {
        // given
        Vector<String> data = new Vector<>();
        data.add("login");
        data.add("password");
        data.add("password");
        data.add("userType");
        data.add("name");
        data.add("surname");
        data.add("phone");
        data.add("pesel");
        LocalDate localDate = LocalDate.now();

        var login = "login";
        var pass = "password";
        AllUsersEntity userWithOccupiedLogin = createUser(login, pass);
        when(dataBase.getUser(login)).thenReturn(userWithOccupiedLogin);

        // when
        LoginWindowActions loginWindowActions = new LoginWindowActions();
        AllUsersEntity newUser = loginWindowActions.register(data, localDate);

        // then
        assertEquals(null, newUser);
    }

    /**
     * Test login is available for different parameters.
     *
     * @param login          the login
     * @param expectedResult the expected result
     */
    @ParameterizedTest
    @MethodSource("provideParamsForLoginIsAvailable")
    void testLoginIsAvailableForDifferentParameters(String login, boolean expectedResult) {
        // given
        var l1 = "janek114";
        var l2 = "mylogin";
        var l3 = "adaś";

        var password = "mypassword";
        AllUsersEntity userWithOccupiedLogin = createUser(login, password);

        when(dataBase.getUser(l1)).thenReturn(userWithOccupiedLogin);
        when(dataBase.getUser(l2)).thenReturn(userWithOccupiedLogin);
        when(dataBase.getUser(l3)).thenReturn(userWithOccupiedLogin);

        // when
        LoginWindowActions loginWindowActions = new LoginWindowActions();
        boolean returnedAvailability = loginWindowActions.loginIsAvalible(login);

        // then
        assertEquals(expectedResult, returnedAvailability);
    }

    private static Stream<Arguments> provideParamsForLoginIsAvailable() {
        return Stream.of(
                Arguments.of("janek", true),
                Arguments.of("innebadpasww", true),
                Arguments.of("", true),
                Arguments.of("janek114", false),
                Arguments.of("adaś", false),
                Arguments.of("mylogin", false)
        );
    }

    /**
     * Test user in port.
     */
    @Test
    void testUserInPort() {
        // given
        LocalDate localDate = LocalDate.now();
        Date date = Date.valueOf(localDate);

        AllUsersEntity myUser = createUser("adam", "zysk");
        PortsEntity myPort = createPort("Port Jeden");
        VisitsEntity myVisit = createVisit(date, myUser, myPort);

        when(calendar.getTime()).thenReturn(date);
        when(dataBase.getVisit(myUser, date)).thenReturn(myVisit);

        // when
        LoginWindowActions loginWindowActions = new LoginWindowActions();
        PortsEntity returnedPort = loginWindowActions.userInPort(myUser);

        // then
        assertEquals(myPort, returnedPort);
        assertEquals("Port Jeden", returnedPort.getPortName());
        assertNotEquals(null, returnedPort);
    }

    /**
     * Test user in port when user is not in port.
     */
    @Test
    void testUserInPortWhenUserIsNotInPort() {
        // given
        LocalDate localDate = LocalDate.now();
        Date today = Date.valueOf(localDate);

        AllUsersEntity myUser = createUser("adam", "zysk");

        when(calendar.getTime()).thenReturn(today);
        when(dataBase.getVisit(myUser, today)).thenReturn(null);

        // when
        LoginWindowActions loginWindowActions = new LoginWindowActions();
        PortsEntity newPort = loginWindowActions.userInPort(myUser);

        // then
        assertEquals(null, newPort);
    }

    private static PortsEntity createPort(String portName) {
        PortsEntity portEntity = new PortsEntity();
        portEntity.setPortName(portName);
        return portEntity;
    }

    private static VisitsEntity createVisit(Date dateBegin, AllUsersEntity userEntity, PortsEntity portEntity) {
        VisitsEntity visitEntity = new VisitsEntity();
        visitEntity.setDateBegin(dateBegin);
        visitEntity.setAllUsersEntity(userEntity);
        visitEntity.setPortsEntity(portEntity);
        return visitEntity;
    }

    /**
     * Test check reg data.
     *
     * @param data           the data
     * @param expectedResult the expected result
     */
    @ParameterizedTest
    @MethodSource("provideParamsForCheckRegData")
    void testCheckRegData(Vector<String> data, int expectedResult) {
        // given
        var login = "mylogin";
        var password = "mypassword";
        AllUsersEntity userWithOccupiedLogin = createUser(login, password);

        when(dataBase.getUser(login)).thenReturn(userWithOccupiedLogin);

        // when

        LoginWindowActions loginWindowActions = new LoginWindowActions();
        int result = loginWindowActions.checkRegData(data);

        // then
        assertEquals(expectedResult, result);
    }

    private static Stream<Arguments> provideParamsForCheckRegData() {
        Vector<String> occupiedLogin = new Vector<>();
        occupiedLogin.add("mylogin");
        occupiedLogin.add("password");

        Vector<String> blankSpaceLogin = new Vector<>();
        blankSpaceLogin.add("login");
        blankSpaceLogin.add("");

        Vector<String> differentPasswords = new Vector<>();
        differentPasswords.add("login");
        differentPasswords.add("pass");
        differentPasswords.add("mypass");

        Vector<String> noAdminOrNormalUser = new Vector<>();
        noAdminOrNormalUser.add("login");
        noAdminOrNormalUser.add("pass");
        noAdminOrNormalUser.add("pass");
        noAdminOrNormalUser.add("admin/normal");

        Vector<String> incorrectPhoneNumber = new Vector<>();
        incorrectPhoneNumber.add("login");
        incorrectPhoneNumber.add("password");
        incorrectPhoneNumber.add("password");
        incorrectPhoneNumber.add("normal");
        incorrectPhoneNumber.add("name");
        incorrectPhoneNumber.add("surname");
        incorrectPhoneNumber.add("111222333999");

        Vector<String> incorrectPesel = new Vector<>();
        incorrectPesel.add("login");
        incorrectPesel.add("password");
        incorrectPesel.add("password");
        incorrectPesel.add("normal");
        incorrectPesel.add("name");
        incorrectPesel.add("surname");
        incorrectPesel.add("111222333");
        incorrectPesel.add("0123");

        Vector<String> idealUser = new Vector<>();
        idealUser.add("login");
        idealUser.add("password");
        idealUser.add("password");
        idealUser.add("normal");
        idealUser.add("name");
        idealUser.add("surname");
        idealUser.add("111222333");
        idealUser.add("01234567890");

        return Stream.of(
                Arguments.of(blankSpaceLogin, 0),
                Arguments.of(occupiedLogin, 1),
                Arguments.of(differentPasswords, 2),
                Arguments.of(noAdminOrNormalUser, 3),
                Arguments.of(incorrectPhoneNumber, 4),
                Arguments.of(incorrectPesel, 5),
                Arguments.of(idealUser, 7)

        );
    }
}
package src.appActions;

import javafx.util.Pair;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import src.app.DataBase;
import src.logic.*;

import javax.xml.crypto.dsig.SignatureProperties;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * The type Visits window actions test.
 */
class VisitsWindowActionsTest {


    /**
     * The Mocked database.
     */
    MockedStatic<DataBase> mockedDatabase;
    /**
     * The Data base.
     */
    DataBase dataBase = mock(DataBase.class);

    /**
     * Sets .
     */
    @BeforeEach
    void setup() {
        mockedDatabase = mockStatic(DataBase.class);
        mockedDatabase.when(DataBase::getInstance).thenReturn(dataBase);
    }

    /**
     * Clean.
     */
    @AfterEach
    void clean() {
        mockedDatabase.close();
    }

    /**
     * Test add ship for different parameters.
     *
     * @param data           the data
     * @param expectedResult the expected result
     */
    @ParameterizedTest
    @MethodSource("provideParamsForAddShip")
    void testAddShipForDifferentParameters(Vector<String> data, int expectedResult) {
        // given
        var ownerId = "112";
        var forename = "Jan";
        var surname = "Antecki";

        var occupied = "Zajęty";

        ShipOwnersEntity shipOwner = createShipOwner(forename, surname);

        when(dataBase.getOwner(ownerId)).thenReturn(shipOwner);
        when(dataBase.getOwner("999")).thenReturn(null);
        when(dataBase.getShip(occupied)).thenReturn(createShip("Zajęty"));

        // when
        VisitsWindowActions visitsWindowActions = new VisitsWindowActions();
        int result = visitsWindowActions.addShip(data);

        // then
        assertEquals(expectedResult, result);
    }

    private static ShipOwnersEntity createShipOwner(String forename, String surname) {
        ShipOwnersEntity shipOwnerEntity = new ShipOwnersEntity();
        shipOwnerEntity.setForname(forename);
        shipOwnerEntity.setSurname(surname);
        return shipOwnerEntity;
    }

    private static ShipsEntity createShip(String callSign) {
        ShipsEntity shipEntity = new ShipsEntity();
        shipEntity.setCallSign(callSign);
        return shipEntity;
    }

    private static Stream<Arguments> provideParamsForAddShip() {
        Vector<String> noShipInDatabase = new Vector<>();
        noShipInDatabase.add("Zajęty");

        Vector<String> shipWithBlankSpace = new Vector<>();
        shipWithBlankSpace.add("CallSign");
        shipWithBlankSpace.add("");


        Vector<String> shipWithNonDigitLength = new Vector<>();
        shipWithNonDigitLength.add("CallSign");
        shipWithNonDigitLength.add("ShipName");
        shipWithNonDigitLength.add("Shiptype");
        shipWithNonDigitLength.add("Długość");

        Vector<String> shipWithTooBigLength = new Vector<>();
        shipWithTooBigLength.add("CallSign");
        shipWithTooBigLength.add("ShipName");
        shipWithTooBigLength.add("Shiptype");
        shipWithTooBigLength.add("123");

        Vector<String> shipWithNoOwner = new Vector<>();
        shipWithNoOwner.add("CallSign");
        shipWithNoOwner.add("ShipName");
        shipWithNoOwner.add("Shiptype");
        shipWithNoOwner.add("12");
        shipWithNoOwner.add("999");

        Vector<String> perfectShip = new Vector<>();
        perfectShip.add("CallSign");
        perfectShip.add("ShipName");
        perfectShip.add("Shiptype");
        perfectShip.add("12");
        perfectShip.add("112");


        return Stream.of(
                Arguments.of(shipWithBlankSpace, 0),
                Arguments.of(noShipInDatabase, 1),
                Arguments.of(shipWithNonDigitLength, 3),
                Arguments.of(shipWithTooBigLength, 4),
                Arguments.of(shipWithNoOwner, 6),
                Arguments.of(perfectShip, 5)
        );
    }


    /**
     * Test add visit for different parameters.
     *
     * @param data           the data
     * @param begin          the begin
     * @param end            the end
     * @param port           the port
     * @param user           the user
     * @param expectedResult the expected result
     */
    @ParameterizedTest
    @MethodSource("provideParamsForAddVisit")
    void testAddVisitForDifferentParameters(Vector<String> data, LocalDate begin, LocalDate end, PortsEntity port, AllUsersEntity user, int expectedResult) {
        // given
        Short shipLength = 12;
        
        var ourShipName = "Pływak";

        LocalDate localDateBegin = LocalDate.of(2022, 12, 12);
        Date dateBegin = Date.valueOf(localDateBegin);

        LocalDate localDateEnd = LocalDate.of(2022, 12, 15);
        Date dateEnd = Date.valueOf(localDateEnd);

        LocalDate futureLocalDate = LocalDate.of(2024, 5, 13);
        Date futureDate = Date.valueOf(futureLocalDate);

        LocalDate secondFutureLocalDate = LocalDate.of(2024, 5, 20);
        Date secondFutureDate = Date.valueOf(secondFutureLocalDate);

        VisitsEntity visitExample = createVisit(dateBegin);
        AllUsersEntity userExample = createUser("Jan", "Antek");
        ShipsEntity ourShip = createShip(ourShipName);
        ourShip.setShipLength(shipLength);

        when(dataBase.getVisit(userExample, dateBegin)).thenReturn(visitExample);
        when(dataBase.getVisit(userExample, dateEnd)).thenReturn(visitExample);
        when(dataBase.getShip(ourShipName)).thenReturn(ourShip);
        when(dataBase.getVisit(ourShip, futureDate)).thenReturn(visitExample);
        when(dataBase.getVisit(ourShip, secondFutureDate)).thenReturn(visitExample);
        when(dataBase.getCaptain(1)).thenReturn(createCaptain(1));

        // when
        VisitsWindowActions visitsWindowActions = new VisitsWindowActions();
        int result = visitsWindowActions.addVisit(data, begin, end, port, user);

        // then
        assertEquals(expectedResult, result);
    }

    private static Stream<Arguments> provideParamsForAddVisit() {
        Vector<String> data = new Vector<>();
        data.add("begin");

        Vector<String> dataWithBlankSpace = new Vector<>();
        dataWithBlankSpace.add("begin");
        dataWithBlankSpace.add("end");
        dataWithBlankSpace.add("");

        Vector<String> noShipInDatabase = new Vector<>();
        noShipInDatabase.add("begin");
        noShipInDatabase.add("end");
        noShipInDatabase.add("Nowy");

        Vector<String> existingShip = new Vector<>();
        existingShip.add("begin");
        existingShip.add("end");
        existingShip.add("Pływak");

        Vector<String> notExistingCaptaind = new Vector<>();
        notExistingCaptaind.add("begin");
        notExistingCaptaind.add("end");
        notExistingCaptaind.add("Pływak");
        notExistingCaptaind.add("2");

        Vector<String> goodData = new Vector<>();
        goodData.add("begin");
        goodData.add("end");
        goodData.add("Pływak");
        goodData.add("1");

        LocalDate beginReturningVisit = LocalDate.of(2022, 12, 12);
        LocalDate endReturningVisit = LocalDate.of(2022, 12, 15);
        LocalDate pastDate = LocalDate.of(2022, 4, 3);

        LocalDate futureDate = LocalDate.of(2024, 5, 13);
        LocalDate secondFutureDate = LocalDate.of(2024, 5, 20);

        LocalDate beginFutureDateWithNoVisit = LocalDate.of(2024, 5, 21);
        LocalDate endFutureDateWithNoVisit = LocalDate.of(2024, 5, 30);

        AllUsersEntity user = createUser("Jan", "Antek");

        PortsEntity port = createPort("Porcik", 10, 10);
        PortsEntity noPlacesForShipsPort = createPort("Pełny Port", 0, 0);



        return Stream.of(
                Arguments.of(data, null, endReturningVisit, port, user, 8),
                Arguments.of(dataWithBlankSpace, pastDate, pastDate, port, user, 0),
                Arguments.of(data, pastDate, endReturningVisit, port, user, 9),
                Arguments.of(data, pastDate, futureDate, port, user, 2),
                Arguments.of(data, futureDate, pastDate, port, user, 3),
                Arguments.of(noShipInDatabase, futureDate, secondFutureDate, port, user, 4),
                Arguments.of(existingShip, futureDate, secondFutureDate, port, user, 10),
                Arguments.of(notExistingCaptaind, beginFutureDateWithNoVisit, endFutureDateWithNoVisit, port, user, 5),
                Arguments.of(goodData, beginFutureDateWithNoVisit, endFutureDateWithNoVisit, noPlacesForShipsPort, user, 6),
                Arguments.of(goodData, beginFutureDateWithNoVisit, endFutureDateWithNoVisit, port, user, 5)
        );
    }

    private static AllUsersEntity createUser(String login, String password) {
        AllUsersEntity allUsersEntity = new AllUsersEntity();
        allUsersEntity.setLogin(login);
        allUsersEntity.setUserPassword(password);
        return allUsersEntity;
    }

    private static CaptainsEntity createCaptain(int id) {
        CaptainsEntity captainsEntity = new CaptainsEntity();
        captainsEntity.setCaptainId(id);
        return captainsEntity;
    }

    private static PortsEntity createPort(String portName, int bigPlaces, int smallPlaces) {
        PortsEntity portEntity = new PortsEntity();
        portEntity.setPortName(portName);
        portEntity.setPlacesShipsBig(bigPlaces);
        portEntity.setPlacesShipsSmall(smallPlaces);
        return portEntity;
    }

    private static VisitsEntity createVisit(Date dateBegin) {
        VisitsEntity visitEntity = new VisitsEntity();
        visitEntity.setDateBegin(dateBegin);
        return visitEntity;
    }


    /**
     * Test add captain.
     */
    @Test
    void testAddCaptain() {
        // given
        Vector<String> data = new Vector<>();
        data.add("Imie");
        data.add("Nazwisko");
        data.add("01234567890");
        data.add("12");

        // when
        VisitsWindowActions visitsWindowActions = new VisitsWindowActions();
        int result = visitsWindowActions.addCaptian(data);

        // then
        assertEquals(2, result);
    }

    /**
     * Test add captain incorrect pesel.
     */
    @Test
    void testAddCaptainIncorrectPesel() {
        // given
        Vector<String> data = new Vector<>();
        data.add("Imie");
        data.add("Nazwisko");
        data.add("12345");
        data.add("12");

        // when
        VisitsWindowActions visitsWindowActions = new VisitsWindowActions();
        int result = visitsWindowActions.addCaptian(data);

        // then
        assertEquals(1, result);
    }

    /**
     * Test add captain empty data.
     */
    @Test
    void testAddCaptainEmptyData() {
        // given
        Vector<String> data = new Vector<>();
        data.add("Imie");
        data.add("");
        data.add("12345");
        data.add("12");

        // when
        VisitsWindowActions visitsWindowActions = new VisitsWindowActions();
        int result = visitsWindowActions.addCaptian(data);

        // then
        assertEquals(0, result);
    }

    /**
     * Test add owner for different parameters.
     *
     * @param data           the data
     * @param expectedResult the expected result
     */
    @ParameterizedTest
    @MethodSource("provideParamsForAddOwner")
    void testAddOwnerForDifferentParameters(Vector<String> data, int expectedResult) {
        // given
        var occupiedEmail = "jan@gmail.com";

        when(dataBase.getOwnerByEmail(occupiedEmail)).thenReturn(createShipOwner("Jan", "Antecki"));

        // when
        VisitsWindowActions visitsWindowActions = new VisitsWindowActions();
        int returnedAvailability = visitsWindowActions.addOwner(data);

        // then
        assertEquals(expectedResult, returnedAvailability);
    }

    private static Stream<Arguments> provideParamsForAddOwner() {
        Vector<String> dataWithBlankSpace = new Vector<>();
        dataWithBlankSpace.add("commercial");
        dataWithBlankSpace.add("");

        Vector<String> dataWithIncorrectPhone = new Vector<>();
        dataWithIncorrectPhone.add("commercial");
        dataWithIncorrectPhone.add("12345");

        Vector<String> dataWithincorrectEmail = new Vector<>();
        dataWithincorrectEmail.add("commercial");
        dataWithincorrectEmail.add("123456789");
        dataWithincorrectEmail.add("miłoszMałpa.com");

        Vector<String> dataWithOccupiedEmail = new Vector<>();
        dataWithOccupiedEmail.add("commercial");
        dataWithOccupiedEmail.add("123456789");
        dataWithOccupiedEmail.add("jan@gmail.com");

        Vector<String> dataWithIncorrectPesel = new Vector<>();
        dataWithIncorrectPesel.add("private");
        dataWithIncorrectPesel.add("123456789");
        dataWithIncorrectPesel.add("janeczek@gmail.com");
        dataWithIncorrectPesel.add("00110");

        Vector<String> correctPrivate = new Vector<>();
        correctPrivate.add("private");
        correctPrivate.add("123456789");
        correctPrivate.add("janeczek@gmail.com");
        correctPrivate.add("Jan");
        correctPrivate.add("Antecki");
        correctPrivate.add("01234567890");


        Vector<String> dataWithIncorrectNip = new Vector<>();
        dataWithIncorrectNip.add("commercial");
        dataWithIncorrectNip.add("123456789");
        dataWithIncorrectNip.add("janeczek@gmail.com");
        dataWithIncorrectNip.add("01234567890");
        dataWithIncorrectNip.add("notDigit");

        Vector<String> correctCommercial = new Vector<>();
        correctCommercial.add("commercial");
        correctCommercial.add("123456789");
        correctCommercial.add("janeczek@gmail.com");
        correctCommercial.add("01234567890");
        correctCommercial.add("1234");


        return Stream.of(
                Arguments.of(dataWithBlankSpace, 0),
                Arguments.of(dataWithIncorrectPhone, 1),
                Arguments.of(dataWithincorrectEmail, 2),
                Arguments.of(dataWithOccupiedEmail, 6),
                Arguments.of(correctPrivate, 5),
                Arguments.of(dataWithIncorrectNip, 4),
                Arguments.of(correctCommercial, 5)
        );
    }

    /**
     * Gets booked places now.
     */
    @Test
    void getBookedPlacesNow() {
        // given
        Short length1 = 9;
        Short length2 = 20;

        LocalDate localToday = LocalDate.now();
        Date today = Date.valueOf(localToday);

        PortsEntity myPort = createPort("Porcik", 10, 10);

        ShipsEntity ship1 = createShip("Jeden");
        ship1.setShipLength(length1);

        ShipsEntity ship2 = createShip("Dwa");
        ship2.setShipLength(length2);

        VisitsEntity visit1 = createVisit(today);
        VisitsEntity visit2 = createVisit(today);
        visit1.setShipsEntity(ship1);
        visit2.setShipsEntity(ship2);

        List<VisitsEntity> myVisits = new ArrayList<VisitsEntity>();
        myVisits.add(visit1);
        myVisits.add(visit2);

        when(dataBase.getVisitFromPort(myPort, today)).thenReturn(myVisits);

        // when
        VisitsWindowActions visitsWindowActions = new VisitsWindowActions();
        Pair<Integer, Integer> result = visitsWindowActions.getBookedPlacesNow(myPort, today);
        Pair expectedPair = new Pair(1, 1);
        // then
        assertEquals(expectedPair, result);
    }

}
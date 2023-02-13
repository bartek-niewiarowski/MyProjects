package src.logic;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * The type All users entity test.
 */
class AllUsersEntityTest {

    /**
     * The Date.
     */
    Date date = new Date(2002, 3, 16);
    /**
     * The Empty date.
     */
    Date emptyDate = new Date(0, 0, 0);
    /**
     * The My user.
     */
    AllUsersEntity myUser = new AllUsersEntity("jan123", "haslo123", "Jan", "Antecki", "511151118", date, "12345671829", "admin");
    /**
     * The Empty user.
     */
    AllUsersEntity emptyUser = new AllUsersEntity("", "", "", "", "", null, "", "");

    /**
     * Gets login.
     */
    @Test
    void getLogin() {
        assertEquals("jan123", myUser.getLogin());
        assertNotEquals("", myUser.getLogin());
        assertEquals("", emptyUser.getLogin());
    }

    /**
     * Sets login.
     */
    @Test
    void setLogin() {
        emptyUser.setLogin("Adam");
        assertEquals("Adam", emptyUser.getLogin());
    }

    /**
     * Gets user password.
     */
    @Test
    void getUserPassword() {
        assertEquals("haslo123", myUser.getUserPassword());
        assertNotEquals("", myUser.getUserPassword());
        assertEquals("", emptyUser.getUserPassword());
    }

    /**
     * Sets user password.
     */
    @Test
    void setUserPassword() {
        emptyUser.setUserPassword("qwerty");
        assertEquals("qwerty", emptyUser.getUserPassword());
    }

    /**
     * Gets forename.
     */
    @Test
    void getForename() {
        assertEquals("Jan", myUser.getForename());
        assertNotEquals("", myUser.getForename());
        assertEquals("", emptyUser.getForename());
    }

    /**
     * Sets forename.
     */
    @Test
    void setForename() {
        emptyUser.setForename("Maciej");
        assertEquals("Maciej", emptyUser.getForename());
    }

    /**
     * Gets surname.
     */
    @Test
    void getSurname() {
        assertEquals("Antecki", myUser.getSurname());
        assertNotEquals("", myUser.getSurname());
        assertEquals("", emptyUser.getSurname());
    }

    /**
     * Sets surname.
     */
    @Test
    void setSurname() {
        emptyUser.setSurname("Nowak");
        assertEquals("Nowak", emptyUser.getSurname());
    }

    /**
     * Gets phone number.
     */
    @Test
    void getPhoneNumber() {
        assertEquals("511151118", myUser.getPhoneNumber());
        assertNotEquals("", myUser.getPhoneNumber());
        assertEquals("", emptyUser.getPhoneNumber());
    }

    /**
     * Sets phone number.
     */
    @Test
    void setPhoneNumber() {
        emptyUser.setPhoneNumber("111111111");
        assertEquals("111111111", emptyUser.getPhoneNumber());
    }

    /**
     * Gets birthday.
     */
    @Test
    void getBirthday() {
        assertEquals(date, myUser.getBirthday());
        assertNotEquals(emptyDate, myUser.getBirthday());
        assertEquals(null, emptyUser.getBirthday());
    }

    /**
     * Sets birthday.
     */
    @Test
    void setBirthday() {
        Date newDate = new Date(2022, 1, 13);
        emptyUser.setBirthday(newDate);
        assertEquals(newDate, emptyUser.getBirthday());
    }

    /**
     * Gets pesel.
     */
    @Test
    void getPesel() {
        assertEquals("12345671829", myUser.getPesel());
        assertNotEquals("", myUser.getPesel());
        assertEquals("", emptyUser.getPesel());
    }

    /**
     * Sets pesel.
     */
    @Test
    void setPesel() {
        emptyUser.setPesel("12312312399");
        assertEquals("12312312399", emptyUser.getPesel());
    }

    /**
     * Gets user type.
     */
    @Test
    void getUserType() {
        assertEquals("admin", myUser.getUserType());
        assertNotEquals("", myUser.getUserType());
        assertEquals("", emptyUser.getUserType());
    }

    /**
     * Sets user type.
     */
    @Test
    void setUserType() {
        emptyUser.setUserType("normal");
        assertEquals("normal", emptyUser.getUserType());
    }
}
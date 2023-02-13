package src.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * The type Ship owners entity test.
 */
class ShipOwnersEntityTest {

    /**
     * The Owner.
     */
    ShipOwnersEntity Owner = new ShipOwnersEntity("nowak@gmail.com", "Podróżnicy", 12345, "519259223");

    /**
     * The Owner 2.
     */
    ShipOwnersEntity Owner2 = new ShipOwnersEntity("123456789", "antek@gmail.com", "jan", "antecki", "01010101");

    /**
     * The Empty.
     */
    ShipOwnersEntity Empty = new ShipOwnersEntity("", "", "", "", "");


    /**
     * Sets ship owner id.
     */
    @Test
    void setShipOwnerId() {
        Owner.setShipOwnerId(1);
        Owner2.setShipOwnerId(9999);
        Empty.setShipOwnerId(-123);
        assertEquals(1, Owner.getShipOwnerId());
        assertEquals(9999, Owner2.getShipOwnerId());
        assertEquals(-123, Empty.getShipOwnerId());
    }

    /**
     * Gets phone number.
     */
    @Test
    void getPhoneNumber() {
        assertEquals("123456789", Owner2.getPhoneNumber());
        assertEquals("519259223", Owner.getPhoneNumber());
    }

    /**
     * Sets phone number.
     */
    @Test
    void setPhoneNumber() {
        Empty.setPhoneNumber("999999999");
        assertEquals("999999999", Empty.getPhoneNumber());
    }

    /**
     * Gets email.
     */
    @Test
    void getEmail() {
        assertEquals("nowak@gmail.com", Owner.getEmail());
    }

    /**
     * Sets email.
     */
    @Test
    void setEmail() {
        Empty.setEmail("empty@gmail.com");
        assertEquals("empty@gmail.com", Empty.getEmail());
    }

    /**
     * Gets forname.
     */
    @Test
    void getForname() {
    }

    /**
     * Sets forname.
     */
    @Test
    void setForname() {
    }

    /**
     * Gets surname.
     */
    @Test
    void getSurname() {
    }

    /**
     * Sets surname.
     */
    @Test
    void setSurname() {
    }

    /**
     * Gets pesel.
     */
    @Test
    void getPesel() {
    }

    /**
     * Sets pesel.
     */
    @Test
    void setPesel() {
    }

    /**
     * Gets name company.
     */
    @Test
    void getNameCompany() {
    }

    /**
     * Sets name company.
     */
    @Test
    void setNameCompany() {
    }

    /**
     * Gets nip.
     */
    @Test
    void getNip() {
    }

    /**
     * Sets nip.
     */
    @Test
    void setNip() {
    }

    /**
     * Test equals.
     */
    @Test
    void testEquals() {
    }
}
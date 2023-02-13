package src.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Captains entity test.
 */
class CaptainsEntityTest {


    /**
     * The My captain.
     */
    CaptainsEntity myCaptain = new CaptainsEntity("Jan", "Długosz", "10101001", 1);
    /**
     * The My captain 2.
     */
    CaptainsEntity myCaptain2 = new CaptainsEntity("Maciek", "Pudzianowski", "11111111111", 13);
    /**
     * The Empty captain.
     */
    CaptainsEntity emptyCaptain = new CaptainsEntity("", "", "", 0);

    /**
     * Gets forename.
     */
    @Test
    void getForename() {
        assertEquals("Jan", myCaptain.getForename());
        assertEquals("", emptyCaptain.getForename());
    }


    /**
     * Sets forename.
     */
    @Test
    void setForename() {
        myCaptain.setForename("Mariusz");
        assertEquals("Mariusz", myCaptain.getForename());
    }

    /**
     * Gets surname.
     */
    @Test
    void getSurname() {
        assertEquals("Długosz", myCaptain.getSurname());
        assertEquals("", emptyCaptain.getSurname());
    }

    /**
     * Sets surname.
     */
    @Test
    void setSurname() {
        myCaptain.setSurname("Kowalski");
        assertEquals("Kowalski", myCaptain.getSurname());
    }

    /**
     * Gets pesel.
     */
    @Test
    void getPesel() {
        assertEquals("10101001", myCaptain.getPesel());
        assertEquals("", emptyCaptain.getPesel());
    }

    /**
     * Sets pesel.
     */
    @Test
    void setPesel() {
        myCaptain.setPesel("10101010101");
        assertEquals("10101010101", myCaptain.getPesel());
    }

    /**
     * Gets captain id.
     */
    @Test
    void getCaptainId() {
        assertEquals(1, myCaptain.getCaptainId());
        assertEquals(0, emptyCaptain.getCaptainId());
    }

    /**
     * Sets captain id.
     */
    @Test
    void setCaptainId() {
        myCaptain.setCaptainId(2);
        assertEquals(2, myCaptain.getCaptainId());
    }

    /**
     * Sets negative captain id.
     */
    @Test
    void setNegativeCaptainID() {
        myCaptain.setCaptainId(-1);
        assertEquals(-1, myCaptain.getCaptainId());
//        assertThrows(IllegalArgumentException.class, () -> { myCaptain.setCaptainId(-1);});
    }

    /**
     * Test equals.
     */
    @Test
    void testEquals() {
        assertEquals(true, myCaptain2.equals(myCaptain2));
        var newCaptain2 = new CaptainsEntity("Maciek", "Pudzianowski", "11111111111", 13);
        assertEquals(true, myCaptain2.equals(newCaptain2));
        assertEquals(false, myCaptain.equals(myCaptain2));
    }

}
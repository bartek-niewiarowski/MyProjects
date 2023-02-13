package src.app;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * The type Registration dialog test.
 */
class RegistrationDialogTest {

    /**
     * String converter.
     */
    @Test
    void stringConverter() {
        var obj = new RegistrationDialog();
        var stringConverter = obj.createStringConverter();
        var convertedDate = stringConverter.fromString("15/12/22");
        var date = LocalDate.of(2022, 12, 15);
        assertEquals(date, convertedDate);

    }

}
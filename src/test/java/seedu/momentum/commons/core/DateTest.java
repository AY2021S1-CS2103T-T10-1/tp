package seedu.momentum.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.momentum.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.momentum.commons.util.DateUtil;

public class DateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date((String) null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "asfd";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDate));
    }

    @Test
    public void isValidDate() {
        // null date time
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // invalid date time
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate(" ")); // spaces only
        assertFalse(Date.isValidDate("^")); // only non-alphanumeric characters
        assertFalse(Date.isValidDate("peter*")); // contains non-alphanumeric characters
        assertFalse(Date.isValidDate("12/02/12*")); // wrong format
        assertFalse(Date.isValidDate("2019-30-23")); // not a real date

        // valid date time
        assertTrue(Date.isValidDate("2019-09-23")); // typical time
    }

    @Test
    public void toString_formatsCorrectly() {
        Date date = new Date("2019-09-23");
        assertEquals(date.getDate().format(DateUtil.FORMAT_DATE_MEDIUM), date.getFormattedDate());
    }
}

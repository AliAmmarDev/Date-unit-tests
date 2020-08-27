package unittests;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Unit tests for the Date class.
 */
public class DateTest {

    private Date birthday;
    private Date christmas;
    private Date today;

    @Before
    public void setUp() {
        birthday = new Date(1997, 2, 10);
        christmas = new Date(1997, 12, 25);
        today = new Date();
    }

    @Test
    public void todayExists() {
        new Date();
    }

    @Test
    public void toStringTest() {
        assertThat(birthday.toString(), is("1997-02-10"));
        assertThat(birthday.toString(), is(not("1997-2-10")));
        assertThat(birthday.toString(), is(not("1996-02-10")));
    }
    @Test(expected=IllegalArgumentException.class)
    public void yearTooLow() {
        new Date(-1, 05, 22);
    }

    @Test(expected=IllegalArgumentException.class)
    public void monthTooLow() {
        new Date(2000, -1, 4);
    }

    @Test(expected=IllegalArgumentException.class)
    public void monthTooHigh() {
        new Date(1990, 13, 22);
    }

    @Test(expected=IllegalArgumentException.class)
    public void dayTooLow() {
        new Date(1000000000, 11, -1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void dayTooHigh() {
        new Date(23, 2, 29);
    }

    @Test
    public void isLeapYear() {
        new Date(2000, 2, 29);
        new Date(2304, 2, 29);
        new Date(1804, 2, 29);
    }

    @Test(expected=IllegalArgumentException.class)
    public void notLeapYear() {
        new Date(1800, 2, 29);
    }

    @Test
    public void equality() {
        assertTrue(birthday.equals(birthday));
        assertFalse(birthday.equals(christmas));
        assertTrue(birthday.equals(new Date(1997, 2, 10)));
        assertFalse(birthday.equals(new Date(1997, 3, 10)));
        assertFalse(birthday.equals(new Date(1997, 2, 11)));
        assertFalse(birthday.equals("1997-02-10"));
        assertFalse(christmas.equals(new Date(2015, 12, 26)));
        assertFalse(christmas.equals(new Date(22, 11, 25)));
    }

    @Test
    public void dayOfYear() {
        assertThat(christmas.getDayOfYear(), is(359));
        assertThat(birthday.getDayOfYear(), is(41));
        assertThat(christmas.getDayOfYear(), is(not(360)));
        assertThat(birthday.getDayOfYear(), is(not(40)));
    }

    @Test
    public void todayTest() {
        assertTrue(today.equals(new Date()));
        assertThat(today, is(new Date()));
    }

}

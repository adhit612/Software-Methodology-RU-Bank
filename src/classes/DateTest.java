package classes;

import static org.junit.Assert.*;

public class DateTest {

    @org.junit.Test
    public void testDaysInFebNonLeap() {
        Date date = new Date("2/29/2011");
        assertFalse(date.isValid());
    }
    @org.junit.Test
    public void testValidMonth() {
        Date date = new Date("13/20/2023");
        assertFalse(date.isValid());
    }
    @org.junit.Test
    public void testValidDaysOverThirty() {
        Date date = new Date("06/31/2002");
        assertFalse(date.isValid());
    }
    @org.junit.Test
    public void testValidDaysOverThirtyOne() {
        Date date = new Date("08/32/2002");
        assertFalse(date.isValid());
    }
    @org.junit.Test
    public void testLeapYrOverMaxDays() {
        Date date = new Date("02/30/2008");
        assertFalse(date.isValid());
    }
    @org.junit.Test
    public void testValidDay() {
        Date date = new Date("10/14/2023"); //day this method is tested
        assertFalse(date.checkIfWithinBounds(date.getMonth(), date.getYear(), date.getDay()));
    }
    @org.junit.Test
    public void testValidYear() {
        Date date = new Date("8/22/2025");
        assertFalse(date.checkIfWithinBounds(date.getMonth(), date.getYear(), date.getDay()));
    }
    @org.junit.Test
    public void testGoodDateWithoutAccountLimits() {
        Date date = new Date("5/15/2022");
        assertTrue(date.isValid());
    }
    @org.junit.Test
    public void testDaysInFebLeap() {
        Date date = new Date("2/29/2008");
        assertTrue(date.isValid());
    }
}
package com.lacombe.aggregate.rssstore.utils;

import org.junit.Before;
import org.junit.Test;

import javax.xml.datatype.DatatypeFactory;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 30/03/13
 * Time: 14:01
 * To change this template use File | Settings | File Templates.
 */
public class DateConverterTest {
    public static final String TEXT_DATE = "2013-03-30T13:13:13.000+01:00";
    private GregorianCalendar calendar = new GregorianCalendar(2013, 2, 30, 13, 13, 13);
    private final Date date = calendar.getTime();

    private DateConverter dateConverter;

    @Before
    public void setUp() throws Exception {

        dateConverter = new DateConverter(DatatypeFactory.newInstance());

    }

    @Test
    public void testToBsonDate() throws Exception {
        assertEquals(TEXT_DATE, dateConverter.toBsonDate(date));

    }

    @Test
    public void testFromBsonDate() throws Exception {
        assertEquals(date, dateConverter.fromBsonDate(TEXT_DATE));
    }
}

package com.lacombe.aggregate.rssstore.utils;

import javax.inject.Inject;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 30/03/13
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class DateConverter {
    private DatatypeFactory datatypeFactory;

    @Inject
    public DateConverter(DatatypeFactory datatypeFactory) {
        this.datatypeFactory = datatypeFactory;
    }

    public String toBsonDate(Date date) {
        GregorianCalendar gCalendar = new GregorianCalendar();
        gCalendar.setTime(date);
        XMLGregorianCalendar xmlCalendar = datatypeFactory.newXMLGregorianCalendar(gCalendar);
        return xmlCalendar.toXMLFormat();
    }

    public Date fromBsonDate(String strDate) {
        XMLGregorianCalendar xmlCalendar = datatypeFactory.newXMLGregorianCalendar(strDate);
        return xmlCalendar.toGregorianCalendar().getTime();
    }
}

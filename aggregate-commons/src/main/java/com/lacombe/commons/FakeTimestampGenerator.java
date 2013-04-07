package com.lacombe.commons;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 07/04/13
 * Time: 16:35
 * To change this template use File | Settings | File Templates.
 */
public class FakeTimestampGenerator implements TimestampGenerator {
    private Date date;

    public FakeTimestampGenerator(Date date) {
        this.date = date;
    }

    @Override
    public Date generate() {
        return date;
    }
}

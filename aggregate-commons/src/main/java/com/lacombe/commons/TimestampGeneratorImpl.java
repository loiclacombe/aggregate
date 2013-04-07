package com.lacombe.commons;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 07/04/13
 * Time: 16:31
 */
public class TimestampGeneratorImpl implements TimestampGenerator {

    @Override
    public Date generate() {
        return new Date();
    }
}

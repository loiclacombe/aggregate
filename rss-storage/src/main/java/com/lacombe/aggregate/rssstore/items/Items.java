package com.lacombe.aggregate.rssstore.items;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 30/03/13
 * Time: 13:43
 * To change this template use File | Settings | File Templates.
 */
@Retention(RetentionPolicy.RUNTIME)
@BindingAnnotation
public @interface Items {
    /**
     * Created with IntelliJ IDEA.
     * User: Damaki
     * Date: 30/03/13
     * Time: 13:49
     * To change this template use File | Settings | File Templates.
     */
    interface TimestampGenerator {
        Date generate();
    }

    /**
     * Created with IntelliJ IDEA.
     * User: Damaki
     * Date: 30/03/13
     * Time: 13:48
     * To change this template use File | Settings | File Templates.
     */
    class TimestampGeneratorImpl implements TimestampGenerator {

        @Override
        public Date generate() {
            return new Date();
        }
    }
}

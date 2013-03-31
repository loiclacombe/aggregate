package com.lacombe.aggregate.rssstore;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.lacombe.aggregate.rssstore.items.ItemsModule;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 30/03/13
 * Time: 10:27
 * To change this template use File | Settings | File Templates.
 */
public class RssStoreModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new ItemsModule());
        install(new MongoModule());
    }


    @Provides
    public DatatypeFactory provideDatatypeFactory() throws DatatypeConfigurationException {
        return DatatypeFactory.newInstance();
    }
}

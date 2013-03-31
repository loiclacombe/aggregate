package com.lacombe.aggregate.rssstore.items;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Named;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

import static com.lacombe.aggregate.rssstore.conf.ConfigurationKeys.AGGREGATOR_DB;
import static com.lacombe.aggregate.rssstore.items.ItemConstants.*;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 30/03/13
 * Time: 17:46
 * To change this template use File | Settings | File Templates.
 */
public class ItemsModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().build(ChannelItemsStoreFactory.class));
        install(new FactoryModuleBuilder().build(ItemToDbFactory.class));
    }

    @Provides
    @Items
    public DBCollection provideItems(@Named(AGGREGATOR_DB) DB db) {
        DBCollection collection = db.getCollection("items");
        BasicDBObject indexDescription = new BasicDBObject();
        indexDescription.append(CHANNEL_ID, 1);
        indexDescription.append(PUB_DATE, -1);
        indexDescription.append(TITLE, 1);
        collection.ensureIndex(indexDescription);
        return collection;
    }
}

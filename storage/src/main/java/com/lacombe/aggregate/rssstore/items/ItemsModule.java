package com.lacombe.aggregate.rssstore.items;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

import static com.lacombe.aggregate.rssstore.conf.ConfigurationKeys.AGGREGATOR_DB;
import static com.lacombe.aggregate.rssstore.items.ItemConstants.*;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 30/03/13
 * Time: 17:46
 */
public class ItemsModule {

    @Produces
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

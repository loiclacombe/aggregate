package com.lacombe.aggregate.rssstore.items;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.lacombe.aggregate.rssstore.beans.Item;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.softwaremill.common.cdi.autofactory.CreatedWith;
import com.softwaremill.common.cdi.autofactory.FactoryParameter;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 30/03/13
 * Time: 11:31
 */
@CreatedWith(ChannelItemsStore.Factory.class)
public class ChannelItemsStoreImpl implements ChannelItemsStore {
    private ItemToDb itemToDb;
    private DbToItem dbToItem;
    private DBCollection channels;


    @Inject
    public ChannelItemsStoreImpl(@Items DBCollection channels, ItemToDbFactory toDbFactory, DbToItem dbToItem,
                                 @FactoryParameter String channelId) {
        this.channels = channels;
        this.dbToItem = dbToItem;
        this.itemToDb = toDbFactory.create(channelId);
    }

    @Override
    public void persist(Item item) {
        BasicDBObject bsonItem = itemToDb.apply(item);
        channels.insert(bsonItem);
    }

    @Override
    public void persist(Collection<Item> items) {
        for (Item item : items) {
            persist(item);
        }
    }

    @Override
    public List<Item> loadlastItems(int count, int skip) {
        BasicDBObject criteria = new BasicDBObject();

        List<? super DBObject> items = channels.find(criteria).limit(count).skip(skip).toArray();
        return Lists.transform(items, (Function) dbToItem);
    }
}

package com.lacombe.aggregate.rssstore.items;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.inject.assistedinject.Assisted;
import com.lacombe.aggregate.rssstore.beans.Item;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import javax.inject.Inject;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 30/03/13
 * Time: 11:31
 * To change this template use File | Settings | File Templates.
 */
public class ChannelItemsStore {
    private ItemToDb itemToDb;
    private DbToItem dbToItem;
    private DBCollection channels;

    @Inject
    public ChannelItemsStore(@Items DBCollection channels, ItemToDbFactory toDbFactory, DbToItem dbToItem,
                             @Assisted String channelId) {
        this.channels = channels;
        this.dbToItem = dbToItem;
        this.itemToDb = toDbFactory.create(channelId);
    }

    public void create() {
    }

    public void persist(Item item) {
        BasicDBObject bsonItem = itemToDb.apply(item);
        channels.insert(bsonItem);
    }

    public List<Item> loadlastItems(int count, int skip) {
        BasicDBObject criteria = new BasicDBObject();

        List<? super DBObject> items = channels.find(criteria).limit(count).skip(skip).toArray();
        return Lists.transform(items, (Function) dbToItem);
    }
}

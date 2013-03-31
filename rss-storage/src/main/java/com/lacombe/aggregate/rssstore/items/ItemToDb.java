package com.lacombe.aggregate.rssstore.items;

import com.google.common.base.Function;
import com.google.inject.assistedinject.Assisted;
import com.lacombe.aggregate.rssstore.beans.Item;
import com.lacombe.aggregate.rssstore.utils.DateConverter;
import com.mongodb.BasicDBObject;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.lacombe.aggregate.rssstore.items.ItemConstants.*;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 30/03/13
 * Time: 17:55
 * To change this template use File | Settings | File Templates.
 */
public class ItemToDb implements Function<Item, BasicDBObject> {

    private DateConverter dateConverter;
    private String channelId;

    @Inject
    public ItemToDb(DateConverter dateConverter, @Assisted String channelId) {
        this.dateConverter = dateConverter;
        this.channelId = channelId;
    }

    @Override
    public BasicDBObject apply(Item item) {
        checkNotNull(item);
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.append(AUTHOR, item.getAuthor());
        dbObject.append(TITLE, item.getTitle());
        dbObject.append(CATEGORY, item.getCategory());
        dbObject.append(DESCRIPTION, item.getDescription());
        dbObject.append(LINK, item.getLink());
        dbObject.append(PUB_DATE, dateConverter.toBsonDate(item.getPubDate()));
        dbObject.append(CHANNEL_ID, channelId);
        dbObject.append(VERSION, CURRENT_VERSION);

        return dbObject;
    }
}

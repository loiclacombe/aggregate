package com.lacombe.aggregate.rssstore.items;

import com.google.common.base.Function;
import com.lacombe.aggregate.rssstore.beans.Item;
import com.lacombe.aggregate.rssstore.utils.DateConverter;
import com.mongodb.BasicDBObject;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkArgument;
import static com.lacombe.aggregate.rssstore.items.ItemConstants.*;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 30/03/13
 * Time: 18:00
 * To change this template use File | Settings | File Templates.
 */
public class DbToItem implements Function<BasicDBObject, Item> {


    private DateConverter dateConverter;

    @Inject
    public DbToItem(DateConverter dateConverter) {
        this.dateConverter = dateConverter;
    }


    @Override
    public Item apply(BasicDBObject itemDbObject) {
        checkArgument(CURRENT_VERSION.equals(itemDbObject.getString(VERSION)), "incompatible version");

        Item itemBean = new Item();
        itemBean.setAuthor(itemDbObject.getString(AUTHOR));
        itemBean.setCategory(itemDbObject.getString(CATEGORY));
        itemBean.setDescription(itemDbObject.getString(DESCRIPTION));
        itemBean.setLink(itemDbObject.getString(LINK));
        itemBean.setPubDate(dateConverter.fromBsonDate(itemDbObject.getString(PUB_DATE)));
        itemBean.setTitle(itemDbObject.getString(TITLE));
        return itemBean;
    }
}
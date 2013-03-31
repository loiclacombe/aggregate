package com.lacombe.aggregate.rssstore.items;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 30/03/13
 * Time: 18:02
 */
public interface ItemToDbFactory {
    ItemToDb create(String channelId);
}

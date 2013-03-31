package com.lacombe.aggregate.rssstore.items;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 30/03/13
 * Time: 13:31
 */
public interface ChannelItemsStoreFactory {
    ChannelItemsStore create(String channelId);
}

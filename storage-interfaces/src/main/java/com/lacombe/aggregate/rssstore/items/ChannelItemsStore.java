package com.lacombe.aggregate.rssstore.items;

import com.lacombe.aggregate.rssstore.beans.Item;

import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 07/04/13
 * Time: 17:18
 */
public interface ChannelItemsStore {
    interface Factory {
        ChannelItemsStore create(String channelId);
    }

    void persist(Item item);

    void persist(Collection<Item> items);

    List<Item> loadlastItems(int count, int skip);
}

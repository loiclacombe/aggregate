package com.lacombe.aggregate.rssstore.service;

import com.google.inject.assistedinject.Assisted;
import com.lacombe.aggregate.rssstore.beans.Item;
import com.lacombe.aggregate.rssstore.items.ChannelItemsStore;
import com.lacombe.aggregate.rssstore.items.ChannelItemsStoreFactory;

import javax.inject.Inject;
import java.io.Serializable;
import java.net.URL;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 01/04/13
 * Time: 17:20
 */
public class Collector implements Serializable {
    private URL url;
    private String channelId;
    private ChannelItemsStoreFactory storeFactory;
    private HorrorssWrapper horrorssWrapper;

    @Inject
    public Collector(ChannelItemsStoreFactory storeFactory, HorrorssWrapper horrorssWrapper) {
        this.storeFactory = storeFactory;
        this.horrorssWrapper = horrorssWrapper;
    }

    public void init(URL url, @Assisted String channelId) {
        this.url = url;
        this.channelId = channelId;
    }

    public void collect() throws Exception {
        List<Item> persistableItems = horrorssWrapper.loadItems(url);

        ChannelItemsStore store = storeFactory.create(channelId);
        store.persist(persistableItems);
    }
}

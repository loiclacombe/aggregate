package com.lacombe.aggregate.rssstore.items;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 30/03/13
 * Time: 18:02
 */
public class ItemToDbFactory {
    private Provider<ItemToDb> itemToDbProvider;

    @Inject
    public ItemToDbFactory(Provider<ItemToDb> itemToDbProvider) {
        this.itemToDbProvider = itemToDbProvider;
    }

    public ItemToDb create(String channelId) {
        ItemToDb itemToDb = itemToDbProvider.get();
        itemToDb.init(channelId);
        return itemToDb;
    }
}

package com.lacombe.aggregate.rssstore;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.lacombe.aggregate.rssstore.beans.Item;
import com.lacombe.aggregate.rssstore.conf.ConfigurationModule;
import com.lacombe.aggregate.rssstore.items.ChannelItemsStore;
import com.lacombe.aggregate.rssstore.items.ChannelItemsStoreFactory;
import com.lacombe.aggregate.rssstore.items.Items;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

import static com.google.inject.Guice.createInjector;
import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 30/03/13
 * Time: 14:32
 * To change this template use File | Settings | File Templates.
 */
public class ChannelItemsStoreIT {
    private static Injector injector;
    private ChannelItemsStore channelItemsStore;
    private DBCollection dbCollection;

    @BeforeClass
    public static void setUpClass() throws Exception {
        injector = createInjector(new RssStoreModule(), new ConfigurationModule("/test-conf.properties"));
        //clear
        injector.getInstance(Key.get(DBCollection.class, Items.class)).remove(new BasicDBObject());
    }

    @Before
    public void setUp() throws Exception {

        dbCollection = injector.getInstance(Key.get(DBCollection.class, Items.class));
        channelItemsStore = injector.getInstance(ChannelItemsStoreFactory.class).create("channelId1");
    }

    @Test
    public void testPersist() throws Exception {
        for (int i = 0; i < 20; i++) {
            channelItemsStore.persist(createItem(i));
        }

        assertEquals(20, dbCollection.count());
    }

    private Item createItem(int i) {
        Item item = new Item();
        item.setAuthor("author" + i);
        item.setCategory("category" + i);
        item.setDescription("description" + i);
        item.setLink("link" + i);
        item.setTitle("title" + i);
        item.setPubDate(new Date());
        return item;
    }

    @Test
    public void testLoad10Items() throws Exception {

    }
}

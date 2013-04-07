package com.lacombe.aggregate.rssstore;

import com.lacombe.aggregate.rssstore.beans.Item;
import com.lacombe.aggregate.rssstore.items.ChannelItemsStore;
import com.lacombe.aggregate.rssstore.items.ChannelItemsStoreImpl;
import com.lacombe.aggregate.rssstore.items.Items;
import com.lacombe.aggregate.rssstore.items.ItemsModule;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.WriteResult;
import com.softwaremill.common.cdi.config.ConfigExtension;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 30/03/13
 * Time: 14:32
 * To change this template use File | Settings | File Templates.
 */

@RunWith(Arquillian.class)
public class ChannelItemsStoreIT {
    private ChannelItemsStore channelItemsStore;
    @Inject
    @Items
    private DBCollection dbCollection;

    @Inject
    private ChannelItemsStore.Factory channelItemsStoreFactory;


    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
                .addClass(ChannelItemsStore.class)
                .addClass(ChannelItemsStore.Factory.class)
                .addClass(ChannelItemsStoreImpl.class)
                .addClass(DBCollection.class)
                .addClass(ItemsModule.class)
                .addClass(ConfigExtension.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(jar.toString(true));
        return jar;
    }


    private WriteResult clearDatabase() {
        return dbCollection.remove(new BasicDBObject());
    }

    @Before
    public void setUp() throws Exception {

        channelItemsStore = channelItemsStoreFactory.create("channelId1");
    }

    @Test
    public void testPersist() throws Exception {
        for (int i = 0; i < 20; i++) {
            channelItemsStore.persist(createItem(i));
        }

        assertEquals(20, dbCollection.count());

        List<Item> items = channelItemsStore.loadlastItems(10, 0);
        Item item = items.get(0);
        assertEquals("", item.getDescription());
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

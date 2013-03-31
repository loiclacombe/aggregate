package com.lacombe.aggregate.rssstore;

import com.lacombe.aggregate.rssstore.beans.Item;
import com.lacombe.aggregate.rssstore.items.DbToItem;
import com.lacombe.aggregate.rssstore.items.ItemToDb;
import com.lacombe.aggregate.rssstore.utils.DateConverter;
import com.mongodb.BasicDBObject;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;

import static com.lacombe.aggregate.rssstore.items.ItemConstants.*;
import static org.easymock.EasyMock.createStrictControl;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 30/03/13
 * Time: 13:08
 * To change this template use File | Settings | File Templates.
 */
public class ConvertersTest {
    public static final String TEXT_DATE = "2013-03-30T13:13:13.000+01:00";
    private GregorianCalendar calendar = new GregorianCalendar(2013, 2, 30, 13, 13, 13);
    private final Date date = calendar.getTime();
    private IMocksControl control = createStrictControl();
    private DateConverter dateConverter = control.createMock(DateConverter.class);
    private ItemToDb itemToDb;
    private DbToItem dbToItem;


    @Before
    public void setUp() throws Exception {
        itemToDb = new ItemToDb(dateConverter, "channelId");
        dbToItem = new DbToItem(dateConverter);
    }

    @Test
    public void testConvertToDbObject() throws Exception {


        Item item = createDefaultItemBean();

        BasicDBObject expected = createDefaultDbItem();

        expect(dateConverter.toBsonDate(date)).andReturn(TEXT_DATE);

        control.replay();
        BasicDBObject result = itemToDb.apply(item);
        assertEquals(expected, result);
        control.verify();
    }

    @Test
    public void testConvertToItem() throws Exception {


        Item expected = createDefaultItemBean();

        BasicDBObject dbObject = createDefaultDbItem();
        expect(dateConverter.fromBsonDate(TEXT_DATE)).andReturn(date);
        control.replay();
        Item result = dbToItem.apply(dbObject);
        assertEquals(expected, result);
        control.verify();
    }

    @Test
    public void testConvertToItem_incompatibleVersion() throws Exception {


        Item expected = createDefaultItemBean();

        BasicDBObject dbObject = createDefaultDbItem();
        dbObject.removeField(VERSION);
        dbObject.append(VERSION, "0.0");

        try {
            Item result = dbToItem.apply(dbObject);
            fail("there should be an exception");
        } catch (IllegalArgumentException e) {
            assertEquals("incompatible version", e.getMessage());
        }
    }

    private Item createDefaultItemBean() {
        Item item = new Item();
        item.setAuthor("author");
        item.setCategory("category");
        item.setDescription("description");
        item.setLink("link");
        item.setTitle("title");
        item.setPubDate(date);
        return item;
    }

    private BasicDBObject createDefaultDbItem() {
        BasicDBObject expected = new BasicDBObject();
        expected.append(AUTHOR, "author");
        expected.append(TITLE, "title");
        expected.append(CATEGORY, "category");
        expected.append(DESCRIPTION, "description");
        expected.append(LINK, "link");
        expected.append(PUB_DATE, TEXT_DATE);
        expected.append(CHANNEL_ID, "channelId");
        expected.append(VERSION, CURRENT_VERSION);
        return expected;
    }

}

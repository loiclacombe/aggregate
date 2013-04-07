package com.lacombe.aggregate.rssstore.service;

import com.lacombe.aggregate.rssstore.beans.Channel;
import com.lacombe.aggregate.rssstore.beans.Item;
import org.horrabin.horrorss.RssChannelBean;
import org.horrabin.horrorss.RssFeed;
import org.horrabin.horrorss.RssItemBean;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 07/04/13
 * Time: 18:23
 * To change this template use File | Settings | File Templates.
 */
public class HorrorRssConverterTest {
    public static final Date PUB_DATE = new Date();
    private HorrorRssConverter horrorRssConverter;

    @Before
    public void setUp() throws Exception {
        horrorRssConverter = new HorrorRssConverter();
    }

    @Test
    public void testConvertChannel() throws Exception {
        RssFeed rssFeed = createRssFeed();

        Channel expected = new Channel();
        expected.setDescription("description");
        expected.setLink("link");
        expected.setPubDate(PUB_DATE);
        expected.setTitle("title");

        Channel channel = horrorRssConverter.convert(rssFeed);
        assertEquals(expected, channel);
    }

    private RssFeed createRssFeed() {
        RssFeed rssFeed = new RssFeed();
        RssChannelBean rssChannelBean = new RssChannelBean();
        rssFeed.setChannel(rssChannelBean);
        rssChannelBean.setDescription("description");
        rssChannelBean.setLink("link");
        rssChannelBean.setPubDate(PUB_DATE);
        rssChannelBean.setTitle("title");

        List<RssItemBean> items = new ArrayList<RssItemBean>();
        rssFeed.setItems(items);

        addRssItemBean(items, 1);
        addRssItemBean(items, 2);
        addRssItemBean(items, 3);
        addRssItemBean(items, 4);

        return rssFeed;
    }

    private void addRssItemBean(List<RssItemBean> items, int i) {
        RssItemBean itemBean = new RssItemBean();
        items.add(itemBean);
        itemBean.setAuthor("author" + i);
        itemBean.setCategory("category" + i);
        itemBean.setDescription("description" + i);
        itemBean.setLink("link" + i);
    }

    @Test
    public void testConvertItems() throws Exception {
        RssFeed rssFeed = createRssFeed();

        List<Item> expected = new ArrayList<>();
        addItem(expected, 1);
        addItem(expected, 2);
        addItem(expected, 3);
        addItem(expected, 4);

        List<Item> items = horrorRssConverter.convert(rssFeed.getItems());
    }

    private void addItem(List<Item> items, int i) {
        Item itemBean = new Item();
        items.add(itemBean);
        itemBean.setAuthor("author" + i);
        itemBean.setCategory("category" + i);
        itemBean.setDescription("description" + i);
        itemBean.setLink("link" + i);
    }
}

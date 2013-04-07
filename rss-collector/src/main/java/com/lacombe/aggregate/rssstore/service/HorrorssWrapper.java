package com.lacombe.aggregate.rssstore.service;

import com.lacombe.aggregate.rssstore.beans.Channel;
import com.lacombe.aggregate.rssstore.beans.Item;
import org.horrabin.horrorss.RssFeed;
import org.horrabin.horrorss.RssItemBean;

import javax.inject.Inject;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.apache.derby.tools.JDBCDisplayUtil.checkNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 07/04/13
 * Time: 17:39
 */
public class HorrorssWrapper {
    private RssFeedLoader rssFeedLoader;
    private HorrorRssConverter horrorRssConverter;

    @Inject
    public HorrorssWrapper(RssFeedLoader rssFeedLoader, HorrorRssConverter horrorRssConverter) {
        this.rssFeedLoader = rssFeedLoader;
        this.horrorRssConverter = horrorRssConverter;
    }

    @Inject
    public HorrorssWrapper(RssFeedLoader rssFeedLoader) {
        this.rssFeedLoader = rssFeedLoader;
    }

    public List<Item> loadItems(URL url) throws Exception {
        RssFeed rssFeed = rssFeedLoader.load(url);

        List<RssItemBean> items = rssFeed.getItems();
        checkNotNull(items, "items is null");
        return new ArrayList<>();
    }

    public Channel loadChannel(URL url) throws Exception {
        RssFeed rssFeed = rssFeedLoader.load(url);

        return horrorRssConverter.convert(rssFeed);
    }


}

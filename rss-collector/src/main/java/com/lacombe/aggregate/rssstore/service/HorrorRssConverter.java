package com.lacombe.aggregate.rssstore.service;

import com.lacombe.aggregate.rssstore.beans.Channel;
import com.lacombe.aggregate.rssstore.beans.Item;
import org.horrabin.horrorss.RssChannelBean;
import org.horrabin.horrorss.RssFeed;
import org.horrabin.horrorss.RssItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 07/04/13
 * Time: 18:22
 * To change this template use File | Settings | File Templates.
 */
public class HorrorRssConverter {


    public Channel convert(RssFeed rssFeed) {
        Channel channel = new Channel();
        RssChannelBean rssFeedChannel = rssFeed.getChannel();
        channel.setDescription(rssFeedChannel.getDescription());
        channel.setLink(rssFeedChannel.getLink());
        channel.setPubDate(rssFeedChannel.getPubDate());
        channel.setTitle(rssFeedChannel.getTitle());
        return channel;
    }

    public List<Item> convert(List<RssItemBean> itemBeans) {
        List<Item> items = new ArrayList<>();

        for (RssItemBean rssItemBean : itemBeans) {
            Item item = new Item();
            items.add(item);
            item.setAuthor(rssItemBean.getAuthor());
            item.setCategory(rssItemBean.getCategory());
            item.setDescription(rssItemBean.getDescription());
            item.setLink(rssItemBean.getLink());
            item.setPubDate(rssItemBean.getPubDate());
            item.setTitle(rssItemBean.getTitle());
        }

        return items;
    }
}

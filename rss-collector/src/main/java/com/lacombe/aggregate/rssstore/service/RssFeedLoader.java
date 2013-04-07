package com.lacombe.aggregate.rssstore.service;

import org.horrabin.horrorss.RssFeed;
import org.horrabin.horrorss.RssParser;

import javax.inject.Inject;
import javax.inject.Provider;
import java.io.InputStream;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 07/04/13
 * Time: 18:16
 * To change this template use File | Settings | File Templates.
 */
public class RssFeedLoader {
    private Provider<RssParser> rssParserProvider;

    @Inject
    public RssFeedLoader(Provider<RssParser> rssParserProvider) {
        this.rssParserProvider = rssParserProvider;
    }

    RssFeed load(URL url) throws Exception {
        try (InputStream inputStream = url.openStream()) {
            RssFeed rssFeed = rssParserProvider.get().load(inputStream);
            return rssFeed;
        }
    }
}

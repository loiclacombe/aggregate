package com.lacombe.aggregate.rssstore.channels;

import com.mongodb.DBCollection;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 30/03/13
 * Time: 17:42
 * To change this template use File | Settings | File Templates.
 */
public class ChannelStore {
    @Channels
    DBCollection channels;

    @Inject
    public ChannelStore(DBCollection channels) {
        this.channels = channels;
    }


}

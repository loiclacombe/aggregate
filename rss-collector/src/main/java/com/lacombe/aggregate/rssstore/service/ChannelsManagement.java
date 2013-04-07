package com.lacombe.aggregate.rssstore.service;

import javax.inject.Inject;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 07/04/13
 * Time: 18:03
 * To change this template use File | Settings | File Templates.
 */
public class ChannelsManagement {
    private HorrorssWrapper horrorssWrapper;

    @Inject
    public ChannelsManagement(HorrorssWrapper horrorssWrapper) {
        this.horrorssWrapper = horrorssWrapper;
    }

    public void createChannel(URL url) throws Exception {
        horrorssWrapper.loadChannel(url);

    }
}

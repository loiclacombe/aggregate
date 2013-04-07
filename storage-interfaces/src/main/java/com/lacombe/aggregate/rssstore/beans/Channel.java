package com.lacombe.aggregate.rssstore.beans;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 31/03/13
 * Time: 03:01
 * To change this template use File | Settings | File Templates.
 */

@Data
public class Channel {
    private String id;
    private String title;
    private String link;
    private String description;
    private Date pubDate;

}

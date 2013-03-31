package com.lacombe.aggregate.rssstore.beans;

import com.google.common.base.Objects;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 31/03/13
 * Time: 03:01
 * To change this template use File | Settings | File Templates.
 */

public class Channel {
    private String id;
    private String title;
    private String link;
    private String description;
    private Date pubDate;

    public Channel() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Channel channel = (Channel) o;

        if (description != null ? !description.equals(channel.description) : channel.description != null) return false;
        if (id != null ? !id.equals(channel.id) : channel.id != null) return false;
        if (link != null ? !link.equals(channel.link) : channel.link != null) return false;
        if (pubDate != null ? !pubDate.equals(channel.pubDate) : channel.pubDate != null) return false;
        if (title != null ? !title.equals(channel.title) : channel.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (pubDate != null ? pubDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("title", title)
                .add("link", link)
                .add("description", description)
                .add("pubDate", pubDate)
                .toString();
    }
}

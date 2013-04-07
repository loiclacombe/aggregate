package com.lacombe.aggregate.rssstore.beans;

import com.google.common.base.Objects;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 30/03/13
 * Time: 14:21
 * To change this template use File | Settings | File Templates.
 */
public class Item {
    private String title;
    private String link;
    private String description;
    private String author;
    private Date pubDate;
    private String category;

    public Item() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (author != null ? !author.equals(item.author) : item.author != null) return false;
        if (category != null ? !category.equals(item.category) : item.category != null) return false;
        if (description != null ? !description.equals(item.description) : item.description != null) return false;
        if (link != null ? !link.equals(item.link) : item.link != null) return false;
        if (pubDate != null ? !pubDate.equals(item.pubDate) : item.pubDate != null) return false;
        if (title != null ? !title.equals(item.title) : item.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (pubDate != null ? pubDate.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("title", title)
                .add("link", link)
                .add("description", description)
                .add("author", author)
                .add("pubDate", pubDate)
                .add("category", category)
                .toString();
    }
}

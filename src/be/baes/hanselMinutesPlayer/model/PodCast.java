package be.baes.hanselMinutesPlayer.model;

import java.io.Serializable;

public class PodCast implements Serializable{
    private String title;
    private String pubDate;
    private String link;
    private String mP3Link;
    private String description;
    
    public PodCast(String title, String pubDate, String link, String mP3Link, String description) {
        this.title = title;
        this.description = description;
        this.pubDate = pubDate;
        this.link = link;
        this.mP3Link = mP3Link;
    }
    
    public String getDescription()
    {
        return description;
    }

    public String getPodCastName()
    {
        return mP3Link.substring(mP3Link.length()-22);
    }
    
    public String getMP3Link() {
        return mP3Link;
    }

    public String getTitle() {
        return title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getLink() {
        return link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PodCast podCast = (PodCast) o;

        return !(link != null ? !link.equals(podCast.link) : podCast.link != null);

    }

    @Override
    public int hashCode() {
        return link != null ? link.hashCode() : 0;
    }

    @Override
    public String toString() {
        return String.format("PodCast{title='%s', pubDate=%s}", title, pubDate);
    }
}

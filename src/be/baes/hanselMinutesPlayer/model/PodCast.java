package be.baes.hanselMinutesPlayer.model;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 9/12/11
 * Time: 9:13
 * To change this template use File | Settings | File Templates.
 */
public class PodCast {
    private String title;
    private String pubDate;
    private String link;
    private String mP3Link;
    
    public PodCast(String title, String pubDate, String link, String mP3Link) {
        this.title = title;
        this.pubDate = pubDate;
        this.link = link;
        this.mP3Link = mP3Link;
    }

    public String getMP3Link() {
        return mP3Link;
    }

    public void setMP3Link(String MP3Link) {
        this.mP3Link = MP3Link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PodCast podCast = (PodCast) o;

        if (link != null ? !link.equals(podCast.link) : podCast.link != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return link != null ? link.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PodCast{" +
                "title='" + title + '\'' +
                ", pubDate=" + pubDate +
                '}';
    }
}

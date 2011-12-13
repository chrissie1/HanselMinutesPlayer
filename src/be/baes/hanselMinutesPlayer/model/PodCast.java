package be.baes.hanselMinutesPlayer.model;

public class PodCast {
    private String title;
    private String pubDate;
    private String link;
    private String mP3Link;
    private Byte[] podCast;
   
    public PodCast(String title, String pubDate, String link, String mP3Link) {
        this.title = title;
        this.pubDate = pubDate;
        this.link = link;
        this.mP3Link = mP3Link;
    }
    
    public Byte[] getPodCast()
    {
        return podCast;
    }
    
    public void setPodCast(Byte[] podCast)
    {
        this.podCast = podCast;
    }
    
    public boolean hasPodCast()
    {
        if(podCast==null) return false;
        return true;
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
        return "PodCast{" +
                "title='" + title + '\'' +
                ", pubDate=" + pubDate +
                '}';
    }
}

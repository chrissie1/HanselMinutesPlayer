package be.baes.hanselMinutesPlayer.rss;

public class RSSItem
{
	private String title = null;
	private String link = null;
	private String pubDate = null;
	private String mp3Link = null;
	private String description = null;
    
	RSSItem()
	{
	}

    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
	public String getMp3Link() {
		return mp3Link;
	}
	void setMp3Link(String _mp3Link) {
		this.mp3Link = _mp3Link;
	}
	void setTitle(String title)
	{
		this.title = title;
	}
	void setLink(String link)
	{
		this.link = link;
	}
	void setPubDate(String pubdate)
	{
		this.pubDate = pubdate;
	}
	public String getTitle()
	{
		return title;
	}
	public String getLink()
	{
		return link;
	}
	public String getPubDate()
	{
		return pubDate;
	}
	public String toString()
	{
		// limit how much text we display
		if (title.length() > 42)
		{
			return title.substring(0, 42) + "...";
		}
		return title;
	}
}

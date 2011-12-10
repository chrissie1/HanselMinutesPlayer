package be.baes.hanselMinutesPlayer.rss;


import java.util.ArrayList;
import java.util.List;

public class RSSFeed 
{
	private int itemCount = 0;
	private List<RSSItem> itemList;

	RSSFeed()
	{
		itemList = new ArrayList<RSSItem>();
	}
	int addItem(RSSItem item)
	{
		itemList.add(item);
		itemCount++;
		return itemCount;
	}
	public List<RSSItem> getAllItems()
	{
		return itemList;
	}

}

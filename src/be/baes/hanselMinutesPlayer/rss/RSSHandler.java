package be.baes.hanselMinutesPlayer.rss;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

public class RSSHandler extends DefaultHandler 
{
	
	RSSFeed _feed;
	RSSItem _item;
	final int RSS_TITLE = 1;
	final int RSS_LINK = 2;
	final int RSS_PUBDATE = 5;

	int depth = 0;
	int currentstate = 0;
	/*
	 * Constructor 
	 */
	public RSSHandler()
	{
	}
	
	/*
	 * getFeed - this returns our feed when all of the parsing is complete
	 */
	public RSSFeed getFeed()
	{
		return _feed;
	}
	
	
	public void startDocument() throws SAXException
	{
		// initialize our RSSFeed object - this will hold our parsed contents
		_feed = new RSSFeed();
		// initialize the RSSItem object - we will use this as a crutch to grab the info from the channel
		// because the channel and items have very similar entries..
		_item = new RSSItem();

	}
	public void endDocument() throws SAXException
	{
	}
	
	public void startElement(String namespaceURI, String localName,String qName, Attributes atts) throws SAXException
	{
		depth++;
		if (localName.equals("channel"))
		{
			currentstate = 0;
			return;
		}
		if (localName.equals("item"))
		{
			// create a new item
			_item = new RSSItem();
			return;
		}
		if (localName.equals("title"))
		{
			currentstate = RSS_TITLE;
			return;
		}
		if (localName.equals("guid"))
		{
			currentstate = RSS_LINK;
			return;
		}
		if (localName.equals("pubDate"))
		{
			currentstate = RSS_PUBDATE;
			return;
		}
		if (qName.equals("media:content"))
		{
			_item.setMp3Link(atts.getValue(0));
			return;
		}
		// if we don't explicitly handle the element, make sure we don't wind up erroneously 
		// storing a newline or other bogus data into one of our existing elements
		currentstate = 0;
	}
	
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException
	{
		depth--;
		if (localName.equals("item"))
		{
			_feed.addItem(_item);
		}
	}
	 
	public void characters(char ch[], int start, int length)
	{
		String theString = new String(ch,start,length);
		
		switch (currentstate)
		{
			case RSS_TITLE:
				_item.setTitle(theString);
				currentstate = 0;
				break;
			case RSS_LINK:
				_item.setLink(theString);
				currentstate = 0;
				break;
			case RSS_PUBDATE:
				_item.setPubDate(theString);
				currentstate = 0;
				break;
		}
		
	}
}

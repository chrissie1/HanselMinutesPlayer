package be.baes.hanselMinutesPlayer.controllers;

import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import roboguice.inject.InjectView;

import com.google.inject.Inject;

import be.baes.hanselMinutesPlayer.R;
import be.baes.hanselMinutesPlayer.adapters.RssItemAdapter;
import be.baes.hanselMinutesPlayer.rss.RSSFeed;
import be.baes.hanselMinutesPlayer.rss.RSSHandler;
import android.app.Activity;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class OnRefreshListClickListener implements OnClickListener {
	@InjectView(R.id.listView1) ListView listView;
	@Inject Activity activity;
	private RSSFeed feed = null;
	
	@Override
    public void onClick(View arg0) {
		feed = getFeed("http://feeds.feedburner.com/HanselminutesCompleteMP3?format=xml");
        UpdateDisplay();
    }
	
	private RSSFeed getFeed(String urlToRssFeed)
    {
    	ThreadPolicy tp = ThreadPolicy.LAX;
    	StrictMode.setThreadPolicy(tp);
    	try
    	{
    		URL url = new URL(urlToRssFeed);
    		SAXParserFactory factory = SAXParserFactory.newInstance();
    		SAXParser parser = factory.newSAXParser();
    		XMLReader xmlreader = parser.getXMLReader();
    		RSSHandler theRssHandler = new RSSHandler();
    		xmlreader.setContentHandler(theRssHandler);
    		InputSource is = new InputSource(url.openStream());
    		xmlreader.parse(is);
    		return theRssHandler.getFeed();
    	}
    	catch (IOException ee)
    	{
    		ee.printStackTrace();
			Toast.makeText(activity, "Error: " + ee.getMessage(), Toast.LENGTH_LONG).show();
    		return null;
    	} catch (ParserConfigurationException e) {
			e.printStackTrace();
			Toast.makeText(activity, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
    		return null;
    	} catch (SAXException e) {
			e.printStackTrace();
			Toast.makeText(activity, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
    		return null;
    	}
    }
        
    private void UpdateDisplay()
    {
        if (feed == null)
        {
        	Log.i("cbaes", "Feed is null");
        	return;
        }
        RssItemAdapter adapter = new RssItemAdapter(activity,R.layout.row,feed.getAllItems());
        listView.setAdapter(adapter);
        listView.setSelection(0);
    }
}

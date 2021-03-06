package be.baes.hanselMinutesPlayer.rss;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;

public class HanselFeed {

    public RSSFeed getFeed(String feed) throws IOException, SAXException, ParserConfigurationException {
        URL url = new URL(feed);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLReader xmlreader = parser.getXMLReader();
        RSSHandler theRssHandler = new RSSHandler();
        xmlreader.setContentHandler(theRssHandler);
        InputSource is = new InputSource(new BufferedInputStream(url.openStream(),4096));
        xmlreader.parse(is);
        return theRssHandler.getFeed();
    }
}

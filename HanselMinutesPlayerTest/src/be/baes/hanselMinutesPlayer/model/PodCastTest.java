package be.baes.hanselMinutesPlayer.model;

import junit.framework.TestCase;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 11/12/11
 * Time: 20:00
 * To change this template use File | Settings | File Templates.
 */
public class PodCastTest extends TestCase {

    public void testIfLinkIsSetByConstructor()
    {
        PodCast podCast = new PodCast("title", "pubdate", "link", "mp3link");
        assertEquals("link",podCast.getLink());
    }

    public void testIfPubDateIsSetByConstructor()
    {
        PodCast podCast = new PodCast("title", "pubdate", "link", "mp3link");
        assertEquals("pubdate",podCast.getPubDate());
    }

    public void testIfMP3LinkIsSetByConstructor()
    {
        PodCast podCast = new PodCast("title", "pubdate", "link", "mp3link");
        assertEquals("mp3link",podCast.getMP3Link());
    }

    public void testIfTitleIsSetByConstructor()
    {
        PodCast podCast = new PodCast("title", "pubdate", "link", "mp3link");
        assertEquals("title",podCast.getTitle());
    }


}

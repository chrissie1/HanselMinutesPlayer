package be.baes.hanselMinutesPlayer.model;

import junit.framework.TestCase;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 11/12/11
 * Time: 20:00
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

    public void testWhenLinkIsTheSameTwoPodCastsAreTheSame()
    {
        PodCast podCast1 = new PodCast("","", "link","");
        PodCast podCast2 = new PodCast("","", "link","");
        assertTrue(podCast1.equals(podCast2));
    }

    public void testWhenLinkIsTheNotTheSameTwoPodCastsAreNotEqual()
    {
        PodCast podCast1 = new PodCast("","", "link1","");
        PodCast podCast2 = new PodCast("","", "link2","");
        assertFalse(podCast1.equals(podCast2));
    }

    public void testToStringShouldReturnTitleAndPubDate()
    {
        PodCast podCast = new PodCast("title", "pubdate", "link", "mp3link");
        assertEquals("PodCast{title='title', pubDate=pubdate}",podCast.toString());
    }
}

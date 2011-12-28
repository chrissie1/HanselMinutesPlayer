package be.baes.hanselMinutesPlayer;

import junit.framework.TestCase;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 26/12/11
 * Time: 13:03
 */
public class ConstantsTest extends TestCase {
    public void testIfLogIdIsCorrect()
    {
        assertEquals("cbaes", Constants.LOG_ID);
    }

    public void testIfCurrentPageIsCorrect()
    {
        assertEquals("currentPage", Constants.CURRENT_PAGE);
    }

    public void testIfListViewPositionIsCorrect()
    {
        assertEquals("listViewPosition", Constants.LIST_VIEW_POSITION);
    }

    public void testIfPositionIsCorrect()
    {
        assertEquals("position", Constants.POSITION);
    }

    public void testIfPodCastsColumnLinkIsCorrect()
    {
        assertEquals("link", Constants.PODCASTS_COLUMN_LINK);
    }

    public void testIfPodCastsColumnTitleIsCorrect()
    {
        assertEquals("title", Constants.PODCASTS_COLUMN_TITLE);
    }

    public void testIfPodCastsColumnPubdateIsCorrect()
    {
        assertEquals("pubdate", Constants.PODCASTS_COLUMN_PUBDATE);
    }

    public void testIfPodCastsColumnMp3LinkIsCorrect()
    {
        assertEquals("mp3link", Constants.PODCASTS_COLUMN_MP3LINK);
    }

    public void testIfPodCastsColumnDescriptionIsCorrect()
    {
        assertEquals("description", Constants.PODCASTS_COLUMN_DESCRIPTION);
    }

    public void testIfTablePodCastsIsCorrect()
    {
        assertEquals("podcasts", Constants.TABLE_PODCASTS);
    }

    public void testIfDatabaseNameIsCorrect()
    {
        assertEquals("HanselMinutesPlayer", Constants.DATABASE_NAME);
    }

    public void testIfDatabaseVersionIsCorrect()
    {
        assertEquals(4, Constants.DATABASE_VERSION);
    }

    public void testIfDatabaseCreateIsCorrect()
    {
        assertEquals(String.format("Create table %s(%s text primary key, %s text not null, %s text not null, %s text not null, %s text not null)", Constants.TABLE_PODCASTS, Constants.PODCASTS_COLUMN_LINK, Constants.PODCASTS_COLUMN_TITLE, Constants.PODCASTS_COLUMN_PUBDATE, Constants.PODCASTS_COLUMN_MP3LINK,Constants.PODCASTS_COLUMN_DESCRIPTION), Constants.DATABASE_CREATE);
    }

    public void testIfDropTableIsCorrect()
    {
        assertEquals(String.format("DROP TABLE IF EXISTS %s", Constants.TABLE_PODCASTS),Constants.DROP_TABLE);
    }

    public void testIfPrefixIsCorrect()
    {
        assertEquals("http://www.hanselminutes.com/default.aspx?ShowID=", Constants.PREFIX);
    }

    public void testIfUrlToRssFeedAllIsCorrect()
    {
        assertEquals("http://feeds.feedburner.com/HanselminutesCompleteMP3?format=xml", Constants.urlToRssFeedAll);
    }

    public void testIfUrlToRssFeedLatestIsCorrect()
    {
        assertEquals("http://feeds.feedburner.com/Hanselminutes?format=xml", Constants.urlToRssFeedLatest);
    }

    public void testIfDownloadPodCastOptionIsCorrect()
    {
        assertEquals(0, Constants.DOWNLOAD_PODCAST_OPTION);
    }

    public void testIfDeleteDownloadedPodcastOptionIsCorrect()
    {
        assertEquals(1,Constants.DELETE_DOWNLOADED_PODCAST_OPTION);
    }

    public void testIfHasPodCastIsCorrect()
    {
        assertEquals("HasPodCast", Constants.HASPODCAST);
    }

    public void testIfMaxDurationIsCorrect()
    {
        assertEquals("MaxDuration", Constants.MAX_DURATION);
    }

    public void testIfProgressIsCorrect()
    {
        assertEquals("Progress", Constants.PROGRESS);
    }

    public void testIfMessageIsCorrect()
    {
        assertEquals("Message", Constants.MESSAGE);
    }

    public void testIfTimerIsCorrect()
    {
        assertEquals("Timer", Constants.TIMER);
    }

    public void testIfDescriptionIsCorrect()
    {
        assertEquals("Description", Constants.DESCRIPTION);
    }

}

package be.baes.hanselMinutesPlayer;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 19/12/11
 * Time: 14:02
 */
public class Constants {
    public static final String LOG_ID = "cbaes";
    public static final String CURRENT_PAGE = "currentPage";
    public static final String LIST_VIEW_POSITION = "listViewPosition";
    public static final String POSITION = "position";
    public static final String PODCASTS_COLUMN_LINK = "link";
    public static final String PODCASTS_COLUMN_TITLE = "title";
    public static final String PODCASTS_COLUMN_PUBDATE = "pubdate";
    public static final String PODCASTS_COLUMN_MP3LINK = "mp3link";
    public static final String PODCASTS_COLUMN_DESCRIPTION = "description";
    public static final String TABLE_PODCASTS = "podcasts";
    public static final String DATABASE_NAME = "HanselMinutesPlayer";
    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_CREATE = String.format("Create table %s(%s text primary key, %s text not null, %s text not null, %s text not null, %s text not null)", TABLE_PODCASTS, PODCASTS_COLUMN_LINK, PODCASTS_COLUMN_TITLE, PODCASTS_COLUMN_PUBDATE, PODCASTS_COLUMN_MP3LINK, PODCASTS_COLUMN_DESCRIPTION);
    public static final String DROP_TABLE = String.format("DROP TABLE IF EXISTS %s", TABLE_PODCASTS);
    public static final String PREFIX = "http://www.hanselminutes.com/default.aspx?ShowID=";
    public static final String urlToRssFeedAll = "http://feeds.feedburner.com/HanselminutesCompleteMP3?format=xml";
    public static final String urlToRssFeedLatest = "http://feeds.feedburner.com/Hanselminutes?format=xml";
    public static final int DOWNLOAD_PODCAST_OPTION = 0;
    public static final int DELETE_DOWNLOADED_PODCAST_OPTION = 1;
    public static final String HASPODCAST = "HasPodCast";
    public static final String MAX_DURATION = "MaxDuration";
    public static final String PROGRESS = "Progress";
    public static final String MESSAGE = "Message";
    public static final String TIMER = "Timer";
    public static final String DESCRIPTION = "Description";
    public static final String SEARCH_TEXT = "SearchText";
    public static final String PARENT = "parent";
    public static final String SEARCH_ACTIVITY = "SearchActivity";
    public static final String HANSELMINUTESPLAYER_ACTIVITY = "HanselMinutesPlayerActivity";
}

package be.baes.hanselMinutesPlayer;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 19/12/11
 * Time: 14:02
 * To change this template use File | Settings | File Templates.
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
    public static final String TABLE_PODCASTS = "podcasts";
    public static final String DATABASE_NAME = "HanselMinutesPlayer";
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_CREATE = String.format("Create table %s(%s text primary key, %s text not null, %s text not null, %s text not null)", TABLE_PODCASTS, PODCASTS_COLUMN_LINK, PODCASTS_COLUMN_TITLE, PODCASTS_COLUMN_PUBDATE, PODCASTS_COLUMN_MP3LINK);
    public static final String DROP_TABLE = String.format("DROP TABLE IF EXISTS %s", TABLE_PODCASTS);
    public static final String PREFIX = "http://www.hanselminutes.com/default.aspx?ShowID=";
    public static final String urlToRssFeed = "http://feeds.feedburner.com/HanselminutesCompleteMP3?format=xml";
    public static final String DELETE_ALL = "Delete All";
    public static final String REFRESH_LIST = "Refresh List";
    public static final String DOWNLOAD_PODCAST = "Download podcast";
    public static final int DELETE_ALL_OPTION = 0;
    public static final int REFRESH_LIST_OPTION = 1;
    public static final int DOWNLOAD_PODCAST_OPTION = 2;
}
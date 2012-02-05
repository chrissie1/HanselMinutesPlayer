package be.baes.hanselMinutesPlayer.facade.task;

import android.os.AsyncTask;
import android.util.Log;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.dal.PodCastAdapter;
import be.baes.hanselMinutesPlayer.facade.PodCastList;
import be.baes.hanselMinutesPlayer.model.PodCast;
import be.baes.hanselMinutesPlayer.resources.StringResources;
import be.baes.hanselMinutesPlayer.rss.HanselFeed;
import be.baes.hanselMinutesPlayer.rss.RSSFeed;
import be.baes.hanselMinutesPlayer.rss.RSSItem;
import be.baes.hanselMinutesPlayer.view.ProgressReport;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class GetListFromRssAndUpdateDatabaseAsyncTask extends AsyncTask<String,String,Void> {
    private be.baes.hanselMinutesPlayer.dal.PodCastAdapter podCastAdapter;
    private StringResources stringResources;
    private HanselFeed hanselFeed;
    private ProgressReport progressReport;
    private PodCastList podCastList;

    public GetListFromRssAndUpdateDatabaseAsyncTask(PodCastAdapter podCastAdapter,HanselFeed hanselFeed, ProgressReport progressReport, StringResources settings, PodCastList podCastList) {
        this.hanselFeed = hanselFeed;
        this.progressReport = progressReport;
        this.podCastAdapter = podCastAdapter;
        this.stringResources =  settings;
        this.podCastList = podCastList;
    }

    @Override
    protected void onPreExecute()
    {
        progressReport.startProgress(stringResources.getDownloading());
    }

    @Override
    protected void onPostExecute(Void result)
    {
        progressReport.endProgress();
        podCastList.load(0);
    }

    @Override
    protected void onProgressUpdate(String... messages)
    {
        progressReport.updateProgess(messages[0]);
    }

    @Override
    protected Void doInBackground(String... feeds) {
        RSSFeed feed = null;
        try {
            feed = hanselFeed.getFeed(feeds[0]);
        } catch (IOException e) {
            Log.e(Constants.LOG_ID, String.format("Error: %s", e.getMessage()),e);
            e.printStackTrace();
            publishProgress("Error: " + e.getMessage());
        } catch (SAXException e) {
            Log.e(Constants.LOG_ID, String.format("Error: %s", e.getMessage()),e);
            e.printStackTrace();
            publishProgress("Error: " + e.getMessage());
        } catch (ParserConfigurationException e) {
            Log.e(Constants.LOG_ID, String.format("Error: %s", e.getMessage()),e);
            e.printStackTrace();
            publishProgress("Error: " + e.getMessage());
        }
        if (feed == null)
        {
            Log.i(Constants.LOG_ID, "Feed is null");
        } else {
            List<RSSItem> rssItems = feed.getAllItems();
            List<PodCast> podCastItems = podCastAdapter.getAllItems();
            int count = 0;
            int total = rssItems.size();
            for (RSSItem rssItem : rssItems) {
                PodCast podCast = new PodCast(rssItem.getTitle(), rssItem.getPubDate(), rssItem.getLink(), rssItem.getMp3Link(), rssItem.getDescription());
                if (podCastItems.contains(podCast)) {
                    if(!podCast.getTitle().equals(podCastItems.get(podCastItems.indexOf(podCast)).getTitle())||!podCast.getPubDate().equals(podCastItems.get(podCastItems.indexOf(podCast)).getPubDate())||!podCast.getDescription().equals(podCastItems.get(podCastItems.indexOf(podCast)).getDescription()))
                    {
                        Log.i(Constants.LOG_ID, String.format("Updating podcast: %s", podCast.getLink()));
                        if(podCast.getLink().startsWith(Constants.PREFIX))
                        {
                            podCastAdapter.updatePodCast(podCast);
                            this.publishProgress("Updating podcast: " + count + " of " + total);
                        }

                    }
                } else {
                    Log.i(Constants.LOG_ID, String.format("Inserting podcast: %s", podCast.getLink()));
                    Log.i(Constants.LOG_ID, String.valueOf(podCast.getLink().contains(Constants.PREFIX)));
                    if(podCast.getLink().contains(Constants.PREFIX))
                    {
                        Log.i(Constants.LOG_ID, "Has correct link, so inserting");
                        podCastAdapter.insertPodCast(podCast);
                        this.publishProgress("Inserting podcast: " + count + " of " + total);
                    }
                }
                count++;
            }
        }
        return null;
    }
}

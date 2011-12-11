package be.baes.hanselMinutesPlayer.facade.task;

import android.os.AsyncTask;
import android.util.Log;
import be.baes.hanselMinutesPlayer.facade.PodCastList;
import be.baes.hanselMinutesPlayer.model.PodCast;
import be.baes.hanselMinutesPlayer.rss.HanselFeed;
import be.baes.hanselMinutesPlayer.rss.RSSFeed;
import be.baes.hanselMinutesPlayer.rss.RSSItem;
import be.baes.hanselMinutesPlayer.view.ProgressReport;

import java.util.List;

public class GetListFromRssAndUpdateDatabaseAsyncTask extends AsyncTask<PodCastList,PodCastList,PodCastList> {
    private be.baes.hanselMinutesPlayer.dal.PodCastAdapter podCastAdapter;
    private HanselFeed hanselFeed;
    private ProgressReport progressReport;

    public GetListFromRssAndUpdateDatabaseAsyncTask(be.baes.hanselMinutesPlayer.dal.PodCastAdapter podCastAdapter,HanselFeed hanselFeed, ProgressReport progressReport) {
        this.hanselFeed = hanselFeed;
        this.progressReport = progressReport;
        this.podCastAdapter = podCastAdapter;
    }

    @Override
    protected void onPreExecute()
    {
        progressReport.startProgress("Downloading...");
    }

    @Override
    protected void onPostExecute(PodCastList result)
    {
        progressReport.endProgress();
        result.load(0);
    }

    @Override
    protected PodCastList doInBackground(PodCastList... voids) {
        RSSFeed feed = hanselFeed.getFeed();
        if (feed == null)
        {
            Log.i("cbaes", "Feed is null");
        } else {
            List<RSSItem> rssItems = feed.getAllItems();
            List<PodCast> podCastItems = podCastAdapter.getAllItems();
            for (RSSItem rssItem : rssItems) {
                PodCast podCast = new PodCast(rssItem.getTitle(), rssItem.getPubDate(), rssItem.getLink(), rssItem.getMp3Link());
                if (podCastItems.contains(podCast)) {
                    if(!podCast.getTitle().equals(podCastItems.get(podCastItems.indexOf(podCast)).getTitle())&&!podCast.getPubDate().equals(podCastItems.get(podCastItems.indexOf(podCast)).getPubDate()))
                    {
                        Log.i("cbaes", "Updating podcast: " + podCast.getLink());
                        if(podCast.getLink().startsWith("http://www.hanselminutes.com/default.aspx?ShowID="))
                        podCastAdapter.updatePodCast(podCast);
                    }
                } else {
                    Log.i("cbaes", "Inserting podcast: " + podCast.getLink());
                    if(podCast.getLink().startsWith("http://www.hanselminutes.com/default.aspx?ShowID="))
                        podCastAdapter.insertPodCast(podCast);
                }
            }
        }
        return voids[0];
    }
}

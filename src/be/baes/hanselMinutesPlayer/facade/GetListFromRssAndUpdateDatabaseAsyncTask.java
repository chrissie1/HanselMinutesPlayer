package be.baes.hanselMinutesPlayer.facade;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import be.baes.hanselMinutesPlayer.model.PodCast;
import be.baes.hanselMinutesPlayer.rss.HanselFeed;
import be.baes.hanselMinutesPlayer.rss.RSSFeed;
import be.baes.hanselMinutesPlayer.rss.RSSItem;
import com.google.inject.Inject;

import java.util.List;

/**
* Created by IntelliJ IDEA.
* User: christiaan
* Date: 10/12/11
* Time: 9:03
* To change this template use File | Settings | File Templates.
*/
public class GetListFromRssAndUpdateDatabaseAsyncTask extends AsyncTask<PodCastList,PodCastList,PodCastList> {
    private Activity activity;
    private be.baes.hanselMinutesPlayer.dal.PodCastAdapter podCastAdapter;
    private HanselFeed hanselFeed;
    private ProgressDialog dialog;

    public GetListFromRssAndUpdateDatabaseAsyncTask(Activity activity,be.baes.hanselMinutesPlayer.dal.PodCastAdapter podCastAdapter,HanselFeed hanselFeed) {
        this.activity = activity;
        this.hanselFeed = hanselFeed;
        this.podCastAdapter = podCastAdapter;
    }

    @Override
    protected void onPreExecute()
    {
        dialog = new ProgressDialog(activity);
        dialog.setMessage("Downloading...");
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    protected void onPostExecute(PodCastList result)
    {
        dialog.dismiss();
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

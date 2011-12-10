package be.baes.hanselMinutesPlayer.facade;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.TextView;
import be.baes.hanselMinutesPlayer.R;
import be.baes.hanselMinutesPlayer.adapters.PodCastAdapter;
import be.baes.hanselMinutesPlayer.model.PodCast;
import be.baes.hanselMinutesPlayer.rss.HanselFeed;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import roboguice.inject.InjectView;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 9/12/11
 * Time: 9:36
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class PodCastList {
    @InjectView(R.id.listView1) ListView listView;
    @InjectView(R.id.numberofpodcasts) TextView numberOfPodCasts;
    @Inject Activity activity;
    @Inject be.baes.hanselMinutesPlayer.dal.PodCastAdapter podCastAdapter;
    GetListFromRssAndUpdateDatabaseAsyncTask task;
    FillListAsyncTask fillListAsyncTask;
    @Inject HanselFeed hanselFeed;

    public void load(int page)
    {
        fillListAsyncTask = new FillListAsyncTask(activity,podCastAdapter,numberOfPodCasts,listView);
        fillListAsyncTask.execute(page);
    }

    public void getListFromRssAndUpdateDatabase()
    {
        task = new GetListFromRssAndUpdateDatabaseAsyncTask(activity,podCastAdapter,hanselFeed);
        task.execute(this);
    }

}

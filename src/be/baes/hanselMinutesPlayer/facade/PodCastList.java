package be.baes.hanselMinutesPlayer.facade;

import android.app.Activity;
import be.baes.hanselMinutesPlayer.facade.task.FillListAsyncTask;
import be.baes.hanselMinutesPlayer.facade.task.GetListFromRssAndUpdateDatabaseAsyncTask;
import be.baes.hanselMinutesPlayer.model.FillListResult;
import be.baes.hanselMinutesPlayer.rss.HanselFeed;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.Observable;

@Singleton
public class PodCastList extends Observable {
    @Inject Activity activity;
    @Inject be.baes.hanselMinutesPlayer.dal.PodCastAdapter podCastAdapter;
    @Inject HanselFeed hanselFeed;
    GetListFromRssAndUpdateDatabaseAsyncTask task;
    FillListAsyncTask fillListAsyncTask;

    public void load(int page)
    {
        fillListAsyncTask = new FillListAsyncTask(activity,podCastAdapter,this);
        fillListAsyncTask.execute(page);
    }

    public void getListFromRssAndUpdateDatabase()
    {
        task = new GetListFromRssAndUpdateDatabaseAsyncTask(activity,podCastAdapter,hanselFeed);
        task.execute(this);
    }

    public void updateList(FillListResult result)
    {
        setChanged();
        notifyObservers(result);
    }

}

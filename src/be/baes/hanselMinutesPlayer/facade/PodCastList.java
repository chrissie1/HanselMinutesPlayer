package be.baes.hanselMinutesPlayer.facade;

import be.baes.hanselMinutesPlayer.facade.task.FillListAsyncTask;
import be.baes.hanselMinutesPlayer.facade.task.GetListFromRssAndUpdateDatabaseAsyncTask;
import be.baes.hanselMinutesPlayer.model.FillListResult;
import be.baes.hanselMinutesPlayer.rss.HanselFeed;
import be.baes.hanselMinutesPlayer.view.ProgressReport;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.Observable;

@Singleton
public class PodCastList extends Observable {
    @Inject be.baes.hanselMinutesPlayer.dal.PodCastAdapter podCastAdapter;
    @Inject HanselFeed hanselFeed;
    @Inject ProgressReport progressReport;
    GetListFromRssAndUpdateDatabaseAsyncTask task;
    FillListAsyncTask fillListAsyncTask;

    public void load(int page)
    {
        fillListAsyncTask = new FillListAsyncTask(podCastAdapter,this, progressReport);
        fillListAsyncTask.execute(page);
    }

    public void getListFromRssAndUpdateDatabase()
    {
        task = new GetListFromRssAndUpdateDatabaseAsyncTask(podCastAdapter,hanselFeed, progressReport);
        task.execute(this);
    }

    public void updateList(FillListResult result)
    {
        setChanged();
        notifyObservers(result);
    }

}

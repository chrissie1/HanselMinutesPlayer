package be.baes.hanselMinutesPlayer.facade;

import be.baes.hanselMinutesPlayer.facade.task.FillListAsyncTask;
import be.baes.hanselMinutesPlayer.facade.task.GetListFromRssAndUpdateDatabaseAsyncTask;
import be.baes.hanselMinutesPlayer.model.FillListResult;
import be.baes.hanselMinutesPlayer.rss.HanselFeed;
import be.baes.hanselMinutesPlayer.view.ProgressReport;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import roboguice.inject.ContextScoped;

import java.util.Observable;

@Singleton
public class PodCastListImpl extends Observable implements PodCastList {
    @Inject be.baes.hanselMinutesPlayer.dal.PodCastAdapter podCastAdapter;
    @Inject HanselFeed hanselFeed;
    @Inject ProgressReport progressReport;
    GetListFromRssAndUpdateDatabaseAsyncTask task;
    FillListAsyncTask fillListAsyncTask;

    public PodCastListImpl()
    {

    }

    @Override
    public void load(int page)
    {
        fillListAsyncTask = new FillListAsyncTask(podCastAdapter,this, progressReport);
        fillListAsyncTask.execute(page);
    }

    @Override
    public void getListFromRssAndUpdateDatabase()
    {
        task = new GetListFromRssAndUpdateDatabaseAsyncTask(podCastAdapter,hanselFeed, progressReport);
        task.execute(this);
    }

    @Override
    public void updateList(FillListResult result)
    {
        setChanged();
        notifyObservers(result);
    }

}

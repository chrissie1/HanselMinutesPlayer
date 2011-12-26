package be.baes.hanselMinutesPlayer.facade;

import be.baes.hanselMinutesPlayer.facade.task.FillListAsyncTask;
import be.baes.hanselMinutesPlayer.facade.task.GetListFromRssAndUpdateDatabaseAsyncTask;
import be.baes.hanselMinutesPlayer.model.FillListResult;
import be.baes.hanselMinutesPlayer.resources.StringResources;
import be.baes.hanselMinutesPlayer.rss.HanselFeed;
import be.baes.hanselMinutesPlayer.view.ProgressReport;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.io.File;
import java.util.Observable;

@Singleton
public class PodCastListImpl extends Observable implements PodCastList {
    @Inject be.baes.hanselMinutesPlayer.dal.PodCastAdapter podCastAdapter;
    @Inject HanselFeed hanselFeed;
    @Inject ProgressReport progressReport;
    @Inject StringResources stringResources;
    @Inject Settings settings;
    GetListFromRssAndUpdateDatabaseAsyncTask task;
    FillListAsyncTask fillListAsyncTask;
    private int currentPage;

    public PodCastListImpl()
    {

    }

    @Override
    public void load(int page)
    {
        currentPage = page;
        fillListAsyncTask = new FillListAsyncTask(podCastAdapter,this, progressReport, stringResources);
        fillListAsyncTask.execute(page);
    }
    
    @Override
    public void load(int page, int position)
    {
        currentPage = page;

        fillListAsyncTask = new FillListAsyncTask(podCastAdapter,this, progressReport, stringResources);
        Integer ints[] = {page, position};
        fillListAsyncTask.execute(ints);
    }

    @Override
    public void getListFromRssAndUpdateDatabase(String feed)
    {
        task = new GetListFromRssAndUpdateDatabaseAsyncTask(podCastAdapter,hanselFeed, progressReport, stringResources,this);
        task.execute(feed);
    }

    @Override
    public void updateList(FillListResult result)
    {
        setChanged();
        result.setNumberOfDownloadedPodCasts(numberOfDownloadedPodCasts());
        notifyObservers(result);
    }

    @Override
    public int getCurrentPage() {
        return currentPage;
    }
    
    private int numberOfDownloadedPodCasts()
    {
        return new File(settings.getCacheDirectory().getPath()).list().length;
    }

}

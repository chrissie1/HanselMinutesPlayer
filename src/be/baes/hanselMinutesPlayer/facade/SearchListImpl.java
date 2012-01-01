package be.baes.hanselMinutesPlayer.facade;

import be.baes.hanselMinutesPlayer.dal.PodCastAdapter;
import be.baes.hanselMinutesPlayer.facade.task.FillListAsyncTask;
import be.baes.hanselMinutesPlayer.facade.task.FillSearchListAsyncTask;
import be.baes.hanselMinutesPlayer.model.FillListResult;
import be.baes.hanselMinutesPlayer.resources.StringResources;
import be.baes.hanselMinutesPlayer.view.ProgressReport;
import com.google.inject.Inject;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 31/12/11
 * Time: 8:57
 */
public class SearchListImpl extends Observable implements SearchList{
    private FillSearchListAsyncTask fillSearchListAsyncTask;
    @Inject PodCastAdapter podCastAdapter;
    @Inject ProgressReport progressReport;
    @Inject StringResources stringResources;
    
    @Override
    public void search(String query) {
        fillSearchListAsyncTask = new FillSearchListAsyncTask(podCastAdapter,this, progressReport, stringResources);
        fillSearchListAsyncTask.execute(query);
    }

    @Override
    public void updateList(FillListResult result) {
        setChanged();
        notifyObservers(result);
    }

    @Override
    public void search(String query, int position) {
        fillSearchListAsyncTask = new FillSearchListAsyncTask(podCastAdapter,this, progressReport, stringResources);
        String ints[] = {query, String.valueOf(position)};
        fillSearchListAsyncTask.execute(ints);
    }
}

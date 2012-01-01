package be.baes.hanselMinutesPlayer.facade.task;

import android.os.AsyncTask;
import be.baes.hanselMinutesPlayer.dal.PodCastAdapter;
import be.baes.hanselMinutesPlayer.facade.PodCastList;
import be.baes.hanselMinutesPlayer.facade.SearchList;
import be.baes.hanselMinutesPlayer.model.FillListResult;
import be.baes.hanselMinutesPlayer.model.PodCast;
import be.baes.hanselMinutesPlayer.resources.StringResources;
import be.baes.hanselMinutesPlayer.view.ProgressReport;

import java.util.List;

public class FillSearchListAsyncTask extends AsyncTask<String,Void,FillListResult>{
    private final static Integer PAGE_SIZE = 30;
    private PodCastAdapter podCastAdapter;
    private ProgressReport progressReport;
    private SearchList searchList;
    private StringResources stringResources;

    public FillSearchListAsyncTask(PodCastAdapter podCastAdapter, SearchList searchList1, ProgressReport progressReport, StringResources stringResources1) {
        this.podCastAdapter = podCastAdapter;
        this.progressReport = progressReport;
        this.searchList = searchList1;
        this.stringResources = stringResources1;
    }

    @Override
    protected void onPreExecute()
    {
        progressReport.startProgress(stringResources.getLoading() + " SearchList");
    }

    @Override
    protected void onPostExecute(FillListResult result)
    {
        progressReport.endProgress();
        searchList.updateList(result);
    }

    @Override
    protected FillListResult doInBackground(String... query) {
        FillListResult fillListResult;
        if(query.length == 2 && query[1]!=null && Integer.getInteger(query[1]) !=null)
        {
            fillListResult = new FillListResult(null,"", Integer.getInteger(query[1]));
        }
        else
        {
            fillListResult = new FillListResult(null,"", 0);
        }
        List<PodCast> podCasts;
        podCasts = podCastAdapter.findItems(query[0]);
        if(podCasts.size() > 0)
        {
            fillListResult.setNumberOfPodCasts("Found: " + podCasts.size());
            fillListResult.setPodCasts(podCasts);
        }
        else
        {
            fillListResult.setPodCasts(null);
            fillListResult.setNumberOfPodCasts("Nothing found");
        }
        return fillListResult;
    }
}
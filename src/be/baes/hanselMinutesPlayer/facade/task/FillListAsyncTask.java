package be.baes.hanselMinutesPlayer.facade.task;

import android.os.AsyncTask;
import be.baes.hanselMinutesPlayer.dal.PodCastAdapter;
import be.baes.hanselMinutesPlayer.facade.PodCastList;
import be.baes.hanselMinutesPlayer.facade.Settings;
import be.baes.hanselMinutesPlayer.model.FillListResult;
import be.baes.hanselMinutesPlayer.model.PodCast;
import be.baes.hanselMinutesPlayer.view.ProgressReport;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FillListAsyncTask extends AsyncTask<Integer,Void,FillListResult>{
    private final static Integer PAGE_SIZE = 30;
    private be.baes.hanselMinutesPlayer.dal.PodCastAdapter podCastAdapter;
    private ProgressReport progressReport;
    private PodCastList podCastList;
    private Settings settings;

    public FillListAsyncTask(PodCastAdapter podCastAdapter, PodCastList podCastList, ProgressReport progressReport, Settings settings) {
        this.podCastAdapter = podCastAdapter;
        this.progressReport = progressReport;
        this.podCastList = podCastList;
        this.settings = settings;
    }

    @Override
    protected void onPreExecute()
    {
        progressReport.startProgress(settings.getLoading() + "List");
    }

    @Override
    protected void onPostExecute(FillListResult result)
    {
        progressReport.endProgress();
        podCastList.updateList(result);
    }

    @Override
    protected FillListResult doInBackground(Integer... integers) {
        FillListResult fillListResult;
        if(integers.length == 1)
        {
            fillListResult = new FillListResult(null,"", integers[0]*PAGE_SIZE - 1);
        }
        else
        {
            fillListResult = new FillListResult(null,"", integers[1]);
        }
        List<PodCast> podCasts;
        podCasts = podCastAdapter.getAllItems(0,PAGE_SIZE*(integers[0]+1));
        if(podCasts.size() > 0)
        {
            fillListResult.setNumberOfPodCasts(String.format(settings.getTotalLoaded(), podCastAdapter.numberOfPodcasts(), podCasts.size()));
            fillListResult.setPodCasts(podCasts);
        }
        else
        {
            fillListResult.setPodCasts(null);
            fillListResult.setNumberOfPodCasts(settings.getNoPodCasts());
        }
        return fillListResult;
    }
}
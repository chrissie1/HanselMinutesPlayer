package be.baes.hanselMinutesPlayer.facade.task;

import android.os.AsyncTask;
import be.baes.hanselMinutesPlayer.facade.PodCastList;
import be.baes.hanselMinutesPlayer.model.FillListResult;
import be.baes.hanselMinutesPlayer.model.PodCast;
import be.baes.hanselMinutesPlayer.view.ProgressReport;

import java.util.List;

public class FillListAsyncTask extends AsyncTask<Integer,Void,FillListResult>{
    private final static Integer PAGE_SIZE = 30;
    private be.baes.hanselMinutesPlayer.dal.PodCastAdapter podCastAdapter;
    private ProgressReport progressReport;
    private PodCastList podCastList;

    public FillListAsyncTask(be.baes.hanselMinutesPlayer.dal.PodCastAdapter podCastAdapter,PodCastList podCastList, ProgressReport progressReport) {
        this.podCastAdapter = podCastAdapter;
        this.progressReport = progressReport;
        this.podCastList = podCastList;
    }

    @Override
    protected void onPreExecute()
    {
        progressReport.startProgress("Loading...");
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
        List<PodCast> podCasts = podCastAdapter.getAllItems(0,PAGE_SIZE*(integers[0]+1));
        if(podCasts.size() > 0)
        {
            fillListResult.setNumberOfPodCasts(String.format("Total: %d Loaded: %d", podCastAdapter.numberOfPodcasts(), podCasts.size()));
            fillListResult.setPodCasts(podCasts);
        }
        else
        {
            fillListResult.setPodCasts(null);
            fillListResult.setNumberOfPodCasts("No podcasts, click refreshlist to download the list");
        }
        return fillListResult;
    }
}
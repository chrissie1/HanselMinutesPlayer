package be.baes.hanselMinutesPlayer.facade;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import be.baes.hanselMinutesPlayer.R;
import be.baes.hanselMinutesPlayer.adapters.PodCastAdapter;
import be.baes.hanselMinutesPlayer.model.FillListResult;
import be.baes.hanselMinutesPlayer.model.PodCast;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 10/12/11
 * Time: 10:52
 * To change this template use File | Settings | File Templates.
 */
public class FillListAsyncTask extends AsyncTask<Integer,Void,FillListResult>{
    private final static Integer PAGE_SIZE = 30;
    private Activity activity;
    private be.baes.hanselMinutesPlayer.dal.PodCastAdapter podCastAdapter;
    private ProgressDialog dialog;
    private PodCastList podCastList;

    public FillListAsyncTask(Activity activity,be.baes.hanselMinutesPlayer.dal.PodCastAdapter podCastAdapter,PodCastList podCastList) {
        this.activity = activity;
        this.podCastAdapter = podCastAdapter;
        this.podCastList = podCastList;
    }

    @Override
    protected void onPreExecute()
    {
        dialog = new ProgressDialog(activity);
        dialog.setMessage("Loading...");
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    protected void onPostExecute(FillListResult result)
    {
        dialog.dismiss();
        podCastList.updateList(result);
    }

    @Override
    protected FillListResult doInBackground(Integer... integers) {
        FillListResult fillListResult = new FillListResult(null,"", integers[0]*PAGE_SIZE - 1);
        List<PodCast> podCasts = podCastAdapter.getAllItems(0,PAGE_SIZE*(integers[0]+1));
        if(podCasts.size() > 0)
        {
            fillListResult.setNumberOfPodCasts("Total: " + podCastAdapter.numberOfPodcasts() + " Loaded: " + podCasts.size());
            PodCastAdapter adapter = new PodCastAdapter(activity, R.layout.row,podCasts);
            fillListResult.setPodCastAdapter(adapter);
        }
        else
        {
            fillListResult.setPodCastAdapter(null);
            fillListResult.setNumberOfPodCasts("No podcasts, click refreshlist to download the list");
        }
        return fillListResult;
    }
}
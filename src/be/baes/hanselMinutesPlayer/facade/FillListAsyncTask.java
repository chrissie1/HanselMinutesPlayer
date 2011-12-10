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
    private TextView numberOfPodCasts;
    private ListView listView;

    public FillListAsyncTask(Activity activity,be.baes.hanselMinutesPlayer.dal.PodCastAdapter podCastAdapter,TextView numberOfPodCasts, ListView listView) {
        this.activity = activity;
        this.podCastAdapter = podCastAdapter;
        this.listView = listView;
        this.numberOfPodCasts = numberOfPodCasts;
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
        numberOfPodCasts.setText(result.getNumberOfPodCasts());
        listView.setAdapter(result.getPodCastAdapter());
        listView.setSelection(result.getPage()*PAGE_SIZE - 1);
    }

    @Override
    protected FillListResult doInBackground(Integer... integers) {
        FillListResult fillListResult = new FillListResult(null,"", integers[0]);
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
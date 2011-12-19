package be.baes.hanselMinutesPlayer.controllers;

import android.util.Log;
import android.widget.AbsListView;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.facade.PodCastList;
import com.google.inject.Inject;

public class OnScrollPodCastListListener implements AbsListView.OnScrollListener {
    @Inject PodCastList podCastList;
    private int currentPage = 0;
    private int previousTotal = 0;
    private boolean loading = true;

    public OnScrollPodCastListListener() {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (loading)
        {
            if (totalItemCount > previousTotal && previousTotal > 0)
            {
                Log.d(Constants.LOG_ID, String.format("totalitemcount: %d", totalItemCount));
                Log.d(Constants.LOG_ID, String.format("loading: %s", loading));
                Log.d(Constants.LOG_ID, String.format("previoustotal: %d", previousTotal));
                loading = false;
                previousTotal = totalItemCount;
                currentPage++;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem))
        {
            Log.i(Constants.LOG_ID, "Loading podcasts on scroll");
            podCastList.load(currentPage, view.getResources());
            loading = true;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }
}

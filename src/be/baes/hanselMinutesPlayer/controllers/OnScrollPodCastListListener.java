package be.baes.hanselMinutesPlayer.controllers;

import android.widget.AbsListView;
import be.baes.hanselMinutesPlayer.facade.PodCastList;
import com.google.inject.Inject;

public class OnScrollPodCastListListener implements AbsListView.OnScrollListener {
    @Inject PodCastList podCastList;
    private int visibleThreshold = 0;
    private int currentPage = 0;
    private int previousTotal = 0;
    private boolean loading = true;

    public OnScrollPodCastListListener() {
    }

    public OnScrollPodCastListListener(int visibleThreshold) {
        this.visibleThreshold = visibleThreshold;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (loading)
        {
            if (totalItemCount > previousTotal)
            {
                loading = false;
                previousTotal = totalItemCount;
                currentPage++;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold))
        {
            podCastList.load(currentPage);
            loading = true;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }
}

package be.baes.hanselMinutesPlayer.controllers;

import android.util.Log;
import android.widget.AbsListView;
import be.baes.hanselMinutesPlayer.facade.PodCastList;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 10/12/11
 * Time: 10:44
 * To change this template use File | Settings | File Templates.
 */
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
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
                currentPage++;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            podCastList.load(currentPage);
            loading = true;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }
}

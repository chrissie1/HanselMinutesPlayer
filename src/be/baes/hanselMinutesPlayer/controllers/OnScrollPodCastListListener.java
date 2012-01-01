package be.baes.hanselMinutesPlayer.controllers;

import android.util.Log;
import android.widget.AbsListView;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.dal.PodCastAdapter;
import be.baes.hanselMinutesPlayer.facade.PodCastList;
import com.google.inject.Inject;

public class OnScrollPodCastListListener implements AbsListView.OnScrollListener {
    @Inject PodCastList podCastList;
    @Inject PodCastAdapter podCastAdapter;

    public OnScrollPodCastListListener() {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (view.isEnabled() && totalItemCount> 0 && (firstVisibleItem + visibleItemCount) == totalItemCount)
        {
            if(totalItemCount < podCastAdapter.numberOfPodcasts())
            {
                int currentPage = podCastList.getCurrentPage();
                Log.d(Constants.LOG_ID, String.format("totalItemCount: %d", totalItemCount));
                Log.d(Constants.LOG_ID, String.format("currentPage: %s", currentPage));
                Log.d(Constants.LOG_ID, String.format("visibleItemCount: %d", visibleItemCount));
                Log.d(Constants.LOG_ID, String.format("firstVisibleItem: %d", firstVisibleItem));
                Log.i(Constants.LOG_ID, "Loading podcasts on scroll");
                currentPage++;
                view.setEnabled(false);
                podCastList.load(currentPage);
            }
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }
}

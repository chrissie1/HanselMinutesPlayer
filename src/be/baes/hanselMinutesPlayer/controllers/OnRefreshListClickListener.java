package be.baes.hanselMinutesPlayer.controllers;

import be.baes.hanselMinutesPlayer.facade.PodCastList;
import be.baes.hanselMinutesPlayer.rss.HanselFeed;

import com.google.inject.Inject;

import be.baes.hanselMinutesPlayer.rss.RSSFeed;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class OnRefreshListClickListener implements OnClickListener {
	@Inject PodCastList podCastList;

	@Override
    public void onClick(View arg0) {
		podCastList.getListFromRssAndUpdateDatabase();
    }
}

package be.baes.hanselMinutesPlayer.controllers;

import be.baes.hanselMinutesPlayer.facade.PodCastList;
import com.google.inject.Inject;
import android.view.View;
import android.view.View.OnClickListener;

public class OnRefreshListClickListener implements OnClickListener {
	@Inject PodCastList podCastList;

	@Override
    public void onClick(View arg0) {
		podCastList.getListFromRssAndUpdateDatabase();
    }
}

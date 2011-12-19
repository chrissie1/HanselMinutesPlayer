package be.baes.hanselMinutesPlayer.controllers;

import android.util.Log;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.facade.PodCastList;
import com.google.inject.Inject;
import android.view.View;
import android.view.View.OnClickListener;

public class OnRefreshListClickListener implements OnClickListener {
	@Inject
    PodCastList podCastList;

	@Override
    public void onClick(View arg0) {
        Log.i(Constants.LOG_ID, "Clicked refresh list");
		podCastList.getListFromRssAndUpdateDatabase(arg0.getResources());
    }
}

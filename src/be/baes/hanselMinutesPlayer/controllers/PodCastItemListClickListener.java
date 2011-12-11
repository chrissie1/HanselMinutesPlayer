package be.baes.hanselMinutesPlayer.controllers;

import android.app.Activity;
import android.widget.*;
import be.baes.hanselMinutesPlayer.model.PodCast;
import com.google.inject.Inject;

import roboguice.inject.InjectView;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import be.baes.hanselMinutesPlayer.R;
import be.baes.hanselMinutesPlayer.facade.Player;
import be.baes.hanselMinutesPlayer.rss.RSSItem;

import java.io.IOException;

public class PodCastItemListClickListener implements OnItemClickListener {
	@Inject Player player;
	@Inject Activity activity;

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        player.setCurrentFile(((PodCast)arg0.getAdapter().getItem(arg2)));
    }
}

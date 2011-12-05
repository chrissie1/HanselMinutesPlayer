package be.baes.hanselMinutesPlayer.controllers;

import com.google.inject.Inject;

import roboguice.inject.InjectView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import be.baes.hanselMinutesPlayer.R;
import be.baes.hanselMinutesPlayer.facade.Player;
import be.baes.hanselMinutesPlayer.rss.RSSItem;

public class RssItemListClickListener implements OnItemClickListener {
	@InjectView(R.id.textView2) TextView textView;
	@Inject Player player;
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		textView.setText("Now playing: " + ((RSSItem)arg0.getAdapter().getItem(arg2)).getTitle());
		player.setCurrentFile(((RSSItem)arg0.getAdapter().getItem(arg2)).getMp3Link());
	}
}

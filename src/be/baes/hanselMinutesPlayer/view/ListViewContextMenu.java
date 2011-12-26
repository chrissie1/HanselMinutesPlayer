package be.baes.hanselMinutesPlayer.view;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.R;
import be.baes.hanselMinutesPlayer.controllers.OnDeleteDownloadedPodCastWithAlertDialogClickListener;
import be.baes.hanselMinutesPlayer.controllers.OnDownloadPodCastWithAlertDialogClickListener;
import be.baes.hanselMinutesPlayer.facade.Player;
import be.baes.hanselMinutesPlayer.facade.Settings;
import com.google.inject.Inject;
import roboguice.inject.InjectView;

import java.io.File;

public class ListViewContextMenu {
    @Inject
    OnDownloadPodCastWithAlertDialogClickListener onDownloadPodCastClickListener;
    @Inject
    OnDeleteDownloadedPodCastWithAlertDialogClickListener onDeleteDownloadedPodCastClickListener;
    @InjectView(R.id.podCastList) ListView listView;
    @Inject Player player;
    @Inject Settings settings;

    public void onCreate(ContextMenu menu, View v)
    {
        if (v.getId() == R.id.podCastList && player.getCurrentPodCast() != null) {
            File file = new File(settings.getCacheDirectory(), player.getCurrentPodCast().getPodCastName());
            if (!file.exists()) {
                menu.add(Menu.NONE, Constants.DOWNLOAD_PODCAST_OPTION, Constants.DOWNLOAD_PODCAST_OPTION, "Download podcast");
            } else {
                menu.add(Menu.NONE, Constants.DELETE_DOWNLOADED_PODCAST_OPTION, Constants.DELETE_DOWNLOADED_PODCAST_OPTION, "Delete downloaded podcast");
            }
        }
    }
    
    public void onItemSelected(MenuItem item)
    {
        switch(item.getItemId()) {
            case Constants.DOWNLOAD_PODCAST_OPTION:
                onDownloadPodCastClickListener.onClick(listView);
                break;
            case Constants.DELETE_DOWNLOADED_PODCAST_OPTION:
                onDeleteDownloadedPodCastClickListener.onClick(listView);
                break;
        }
    }
}

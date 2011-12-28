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
    @Inject OnDownloadPodCastWithAlertDialogClickListener onDownloadPodCastWithAlertDialogClickListener;
    @Inject OnDeleteDownloadedPodCastWithAlertDialogClickListener onDeleteDownloadedPodCastWithAlertDialogClickListener;
    @InjectView(R.id.podCastList) ListView listView;
    @Inject Player player;
    @Inject Settings settings;

    public void onCreate(ContextMenu menu, View v)
    {
        if (v.getId() == R.id.podCastList && player.getCurrentPodCast() != null) {
            if (!player.hasCurrentPodCastDownloadedMp3()) {
                menu.add(Menu.NONE, Constants.DOWNLOAD_PODCAST_OPTION, Constants.DOWNLOAD_PODCAST_OPTION, "Download podcast " + player.getCurrentPodCast().getTitle());
            } else {
                menu.add(Menu.NONE, Constants.DELETE_DOWNLOADED_PODCAST_OPTION, Constants.DELETE_DOWNLOADED_PODCAST_OPTION, "Delete downloaded podcast "  + player.getCurrentPodCast().getTitle());
            }                                                                                                                                                                                             }
    }
    
    public void onItemSelected(MenuItem item)
    {
        switch(item.getItemId()) {
            case Constants.DOWNLOAD_PODCAST_OPTION:
                onDownloadPodCastWithAlertDialogClickListener.onClick(listView);
                break;
            case Constants.DELETE_DOWNLOADED_PODCAST_OPTION:
                onDeleteDownloadedPodCastWithAlertDialogClickListener.onClick(listView);
                break;
        }
    }
}

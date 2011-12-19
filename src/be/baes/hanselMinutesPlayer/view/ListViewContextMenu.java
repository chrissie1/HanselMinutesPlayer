package be.baes.hanselMinutesPlayer.view;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.R;
import be.baes.hanselMinutesPlayer.controllers.OnDeleteAllClickListener;
import be.baes.hanselMinutesPlayer.controllers.OnDownloadPodCastClickListener;
import be.baes.hanselMinutesPlayer.controllers.OnRefreshListClickListener;
import be.baes.hanselMinutesPlayer.facade.Player;
import com.google.inject.Inject;
import roboguice.inject.InjectView;

public class ListViewContextMenu {
    @Inject OnRefreshListClickListener onRefreshListClickListener;
    @Inject OnDeleteAllClickListener onDeleteAllClickListener;
    @Inject OnDownloadPodCastClickListener onDownloadPodCastClickListener;
    @InjectView(R.id.podCastList) ListView listView;
    @Inject Player player;

    public void onCreate(ContextMenu menu, View v)
    {
        if (v.getId()== R.id.podCastList) {
            menu.add(Menu.NONE, Constants.DELETE_ALL_OPTION, Constants.DELETE_ALL_OPTION, Constants.DELETE_ALL);
            menu.add(Menu.NONE, Constants.REFRESH_LIST_OPTION, Constants.REFRESH_LIST_OPTION, Constants.REFRESH_LIST);
            if(player.getCurrentPodCast()!= null)
                menu.add(Menu.NONE, Constants.DOWNLOAD_PODCAST_OPTION, Constants.DOWNLOAD_PODCAST_OPTION, Constants.DOWNLOAD_PODCAST);
        }
    }
    
    public void onItemSelected(MenuItem item)
    {
        switch(item.getItemId()) {
            case Constants.DELETE_ALL_OPTION:
                onDeleteAllClickListener.onClick(listView);
                break;
            case Constants.REFRESH_LIST_OPTION:
                onRefreshListClickListener.onClick(listView);
                break;
            case Constants.DOWNLOAD_PODCAST_OPTION:
                onDownloadPodCastClickListener.onClick(listView);
                break;
        }
    }
}

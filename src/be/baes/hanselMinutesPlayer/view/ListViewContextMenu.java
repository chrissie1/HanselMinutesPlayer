package be.baes.hanselMinutesPlayer.view;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import be.baes.hanselMinutesPlayer.R;
import be.baes.hanselMinutesPlayer.controllers.OnDeleteAllClickListener;
import be.baes.hanselMinutesPlayer.controllers.OnRefreshListClickListener;
import com.google.inject.Inject;
import roboguice.inject.InjectView;

public class ListViewContextMenu {
    @Inject OnRefreshListClickListener onRefreshListClickListener;
    @Inject OnDeleteAllClickListener onDeleteAllClickListener;
    @InjectView(R.id.listView1) ListView listView;
    private static final String DELETE_ALL = "Delete All";
    private static final String REFRESH_LIST = "Refresh List";
    private static final int DELETE_ALL_OPTION = 0;
    private static final int REFRESH_LIST_OPTION = 1;
    
    public void onCreate(ContextMenu menu, View v)
    {
        if (v.getId()== R.id.listView1) {
            menu.add(Menu.NONE, DELETE_ALL_OPTION, DELETE_ALL_OPTION, DELETE_ALL);
            menu.add(Menu.NONE, REFRESH_LIST_OPTION, REFRESH_LIST_OPTION, REFRESH_LIST);
        }
    }
    
    public void onItemSelected(MenuItem item)
    {
        switch(item.getItemId()) {
            case DELETE_ALL_OPTION:
                onDeleteAllClickListener.onClick(listView);
                break;
            case REFRESH_LIST_OPTION:
                onRefreshListClickListener.onClick(listView);
                break;
        }
    }
}

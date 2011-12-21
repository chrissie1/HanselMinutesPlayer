package be.baes.hanselMinutesPlayer.controllers;

import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.facade.Player;
import be.baes.hanselMinutesPlayer.facade.Settings;
import be.baes.hanselMinutesPlayer.view.YesNoAlertDialog;
import com.google.inject.Inject;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 21/12/11
 * Time: 15:22
 */
public class OnDeleteDownloadedPodCastClickListener implements SearchView.OnClickListener{
    @Inject Settings settings;
    @Inject Player player;
    @Inject YesNoAlertDialog yesNoAlertDialog;

    @Override
    public void onClick(View view) {
        Log.i(Constants.LOG_ID, "PodCast item selected");
        if(!yesNoAlertDialog.show(view, "Delete download?", "Are you sure you want to delete the downloaded podcast?")) return;
        File file = new File(settings.getCacheDirectory(),player.getCurrentPodCast().getPodCastName());
        if(!file.exists())
        {
            Log.i(Constants.LOG_ID, "No file to delete");
            Toast.makeText(view.getContext(), settings.NoFileToDelete(), Toast.LENGTH_LONG).show();
        }
        else
        {
            file.delete();
        }
    }
}

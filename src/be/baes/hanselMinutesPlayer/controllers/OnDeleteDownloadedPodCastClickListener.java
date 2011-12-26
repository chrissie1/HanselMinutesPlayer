package be.baes.hanselMinutesPlayer.controllers;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.facade.Player;
import be.baes.hanselMinutesPlayer.facade.Settings;
import com.google.inject.Inject;

import java.io.File;

/**
* Created by IntelliJ IDEA.
* User: christiaan
* Date: 26/12/11
* Time: 9:08
* To change this template use File | Settings | File Templates.
*/
class OnDeleteDownloadedPodCastClickListener implements DialogInterface.OnClickListener {
    @Inject Context context;
    @Inject Settings settings;
    @Inject Player player;

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if(!player.hasCurrentPodCastDownloadedMp3())
        {
            Log.i(Constants.LOG_ID, "No file to delete");
            Toast.makeText(context, settings.NoFileToDelete(), Toast.LENGTH_LONG).show();
        }
        else
        {
            player.deleteCurrentPodCastDownLoadedMp3();
        }
    }
}

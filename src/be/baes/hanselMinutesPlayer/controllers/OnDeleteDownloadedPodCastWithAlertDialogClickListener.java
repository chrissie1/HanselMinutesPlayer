package be.baes.hanselMinutesPlayer.controllers;

import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.resources.StringResources;
import be.baes.hanselMinutesPlayer.view.YesNoAlertDialog;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 21/12/11
 * Time: 15:22
 */
public class OnDeleteDownloadedPodCastWithAlertDialogClickListener implements SearchView.OnClickListener{
    @Inject StringResources stringResources;
    @Inject YesNoAlertDialog yesNoAlertDialog;
    @Inject OnDeleteDownloadedPodCastClickListener onDeleteDownloadedPodCastClickListener;

    @Override
    public void onClick(View view) {
        Log.i(Constants.LOG_ID, "PodCast item selected");
        yesNoAlertDialog.show(view, stringResources.getDeleteDownloadTitle(), stringResources.getDeleteDownloadMessage(), onDeleteDownloadedPodCastClickListener, null);
    }

}

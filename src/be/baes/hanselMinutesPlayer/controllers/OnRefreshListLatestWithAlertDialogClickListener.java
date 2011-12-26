package be.baes.hanselMinutesPlayer.controllers;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.resources.StringResources;
import be.baes.hanselMinutesPlayer.view.YesNoAlertDialog;
import com.google.inject.Inject;

public class OnRefreshListLatestWithAlertDialogClickListener implements OnClickListener {
    @Inject StringResources stringResources;
    @Inject YesNoAlertDialog yesNoAlertDialog;
    @Inject OnRefreshListLatestClickListener onRefreshListLatestClickListener;

    @Override
    public void onClick(final View arg0) {
        Log.i(Constants.LOG_ID, "Clicked refresh list");
        yesNoAlertDialog.show(arg0, stringResources.getRefreshListTitle(), stringResources.getRefreshListMessage(), onRefreshListLatestClickListener, null);
    }

}

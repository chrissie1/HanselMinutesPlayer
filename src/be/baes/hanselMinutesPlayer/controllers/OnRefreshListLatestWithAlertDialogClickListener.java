package be.baes.hanselMinutesPlayer.controllers;

import android.util.Log;
import android.view.View;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 6/01/12
 * Time: 21:52
 */
public class OnRefreshListLatestWithAlertDialogClickListener implements View.OnClickListener {
    @Inject
    be.baes.hanselMinutesPlayer.resources.StringResources stringResources;
    @Inject
    be.baes.hanselMinutesPlayer.view.YesNoAlertDialog yesNoAlertDialog;
    @Inject
    OnRefreshListLatestClickListener onRefreshListLatestClickListener;

    @Override
    public void onClick(final View arg0) {
        Log.i(be.baes.hanselMinutesPlayer.Constants.LOG_ID, "Clicked refresh list");
        yesNoAlertDialog.show(arg0, stringResources.getRefreshListTitle(), stringResources.getRefreshListMessage(), onRefreshListLatestClickListener, null);
    }
}

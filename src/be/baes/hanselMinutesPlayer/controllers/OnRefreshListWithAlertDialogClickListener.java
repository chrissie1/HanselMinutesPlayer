package be.baes.hanselMinutesPlayer.controllers;

import android.util.Log;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.resources.StringResources;
import be.baes.hanselMinutesPlayer.view.YesNoAlertDialog;
import com.google.inject.Inject;
import android.view.View;
import android.view.View.OnClickListener;

public class OnRefreshListWithAlertDialogClickListener implements OnClickListener {
	@Inject StringResources stringResources;
    @Inject YesNoAlertDialog yesNoAlertDialog;
    @Inject OnRefreshListClickListener onRefreshListClickListener;

    @Override
    public void onClick(View arg0) {
        Log.i(Constants.LOG_ID, "Clicked refresh list");
        yesNoAlertDialog.show(arg0, stringResources.getRefreshListTitle(), stringResources.getRefreshListMessage(), onRefreshListClickListener, null);
    }

}

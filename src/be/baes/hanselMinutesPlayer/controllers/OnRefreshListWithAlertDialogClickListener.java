package be.baes.hanselMinutesPlayer.controllers;

import android.util.Log;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.facade.Settings;
import be.baes.hanselMinutesPlayer.view.YesNoAlertDialog;
import com.google.inject.Inject;
import android.view.View;
import android.view.View.OnClickListener;

public class OnRefreshListWithAlertDialogClickListener implements OnClickListener {
	@Inject Settings settings;
    @Inject YesNoAlertDialog yesNoAlertDialog;
    @Inject OnRefreshListClickListener onRefreshListClickListener;

    @Override
    public void onClick(View arg0) {
        Log.i(Constants.LOG_ID, "Clicked refresh list");
        yesNoAlertDialog.show(arg0, settings.getRefreshListTitle(), settings.getRefreshListMessage(), onRefreshListClickListener, null);
    }

}

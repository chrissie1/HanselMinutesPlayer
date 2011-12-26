package be.baes.hanselMinutesPlayer.controllers;

import android.util.Log;
import android.view.View;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.resources.StringResources;
import be.baes.hanselMinutesPlayer.view.YesNoAlertDialog;
import com.google.inject.Inject;

public class OnDeleteAllWithAlertDialogClickListener implements View.OnClickListener {
    @Inject YesNoAlertDialog yesNoAlertDialog;
    @Inject StringResources stringResources;
    @Inject OnDeleteAllClickListener onDeleteAllClickListener;

    @Override
    public void onClick(View view) {
        Log.i(Constants.LOG_ID, "Clicked delete all");
        yesNoAlertDialog.show(view, stringResources.getDeleteAllTitle(), stringResources.getDeleteAllMessage(), onDeleteAllClickListener, null);
    }

}

package be.baes.hanselMinutesPlayer.controllers;

import android.util.Log;
import android.view.View;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.dal.PodCastAdapter;
import be.baes.hanselMinutesPlayer.facade.Player;
import be.baes.hanselMinutesPlayer.facade.PodCastList;
import be.baes.hanselMinutesPlayer.facade.Settings;
import be.baes.hanselMinutesPlayer.view.YesNoAlertDialog;
import com.google.inject.Inject;

public class OnDeleteAllWithAlertDialogClickListener implements View.OnClickListener {
    @Inject YesNoAlertDialog yesNoAlertDialog;
    @Inject Settings settings;
    @Inject OnDeleteAllClickListener onDeleteAllClickListener;

    @Override
    public void onClick(View view) {
        Log.i(Constants.LOG_ID, "Clicked delete all");
        yesNoAlertDialog.show(view, settings.getDeleteAllTitle(), settings.getDeleteAllMessage(), onDeleteAllClickListener, null);
    }

}

package be.baes.hanselMinutesPlayer.controllers;

import android.util.Log;
import android.widget.CompoundButton;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.facade.Settings;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 14/05/12
 * Time: 10:39
 * To change this template use File | Settings | File Templates.
 */
public class OnRoamingCheckChangeListener implements CompoundButton.OnCheckedChangeListener {
    @Inject Settings settings;
            
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        be.baes.hanselMinutesPlayer.model.Settings result= new be.baes.hanselMinutesPlayer.model.Settings();
        result.setOnRoaming(b);
        Log.i(Constants.LOG_ID,"Checkedstatechanged to " + b);
        settings.UpdateOrInsertSettings(result);
    }
}

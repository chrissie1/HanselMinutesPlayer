package be.baes.hanselMinutesPlayer.controllers;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;
import be.baes.hanselMinutesPlayer.R;
import be.baes.hanselMinutesPlayer.dal.PodCastAdapter;
import be.baes.hanselMinutesPlayer.facade.Player;
import be.baes.hanselMinutesPlayer.facade.PodCastList;
import com.google.inject.Inject;
import roboguice.inject.InjectView;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 9/12/11
 * Time: 14:42
 * To change this template use File | Settings | File Templates.
 */
public class OnDeleteAllClickListener implements View.OnClickListener {
    @Inject PodCastAdapter podCastAdapter;
    @Inject PodCastList podCastList;
    @Inject Player player;
    @Inject Activity activity;
    
    @Override
    public void onClick(View view) {
        podCastAdapter.deleteAll();
        podCastList.load(0);
        player.setCurrentFile(null);
    }
}

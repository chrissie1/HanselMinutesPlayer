package be.baes.hanselMinutesPlayer.controllers;

import android.view.View;
import be.baes.hanselMinutesPlayer.dal.PodCastAdapter;
import be.baes.hanselMinutesPlayer.facade.Player;
import be.baes.hanselMinutesPlayer.facade.PodCastList;
import com.google.inject.Inject;

public class OnDeleteAllClickListener implements View.OnClickListener {
    @Inject PodCastAdapter podCastAdapter;
    @Inject
    PodCastList podCastList;
    @Inject Player player;

    @Override
    public void onClick(View view) {
        podCastAdapter.deleteAll();
        podCastList.load(0);
        player.setCurrentFile(null);
    }
}

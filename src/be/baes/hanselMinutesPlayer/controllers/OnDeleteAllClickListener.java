package be.baes.hanselMinutesPlayer.controllers;

import android.content.DialogInterface;
import be.baes.hanselMinutesPlayer.dal.PodCastAdapter;
import be.baes.hanselMinutesPlayer.facade.Player;
import be.baes.hanselMinutesPlayer.facade.PodCastList;
import com.google.inject.Inject;

/**
* Created by IntelliJ IDEA.
* User: christiaan
* Date: 26/12/11
* Time: 9:03
* To change this template use File | Settings | File Templates.
*/
class OnDeleteAllClickListener implements DialogInterface.OnClickListener {
    @Inject PodCastAdapter podCastAdapter;
    @Inject PodCastList podCastList;
    @Inject Player player;

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        podCastAdapter.deleteAll();
        podCastList.load(0);
        player.setCurrentFile(null);
    }
}

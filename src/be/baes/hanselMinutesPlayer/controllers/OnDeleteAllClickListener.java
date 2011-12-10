package be.baes.hanselMinutesPlayer.controllers;

import android.view.View;
import be.baes.hanselMinutesPlayer.dal.PodCastAdapter;
import be.baes.hanselMinutesPlayer.facade.PodCastList;
import com.google.inject.Inject;

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

    @Override
    public void onClick(View view) {
        podCastAdapter.deleteAll();
        podCastList.load(0);
    }
}

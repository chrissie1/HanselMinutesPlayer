package be.baes.hanselMinutesPlayer.controllers;

import android.view.GestureDetector;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 29/12/11
 * Time: 13:21
 */
public class OnFlingSearchGestureDetector extends GestureDetector {
    private OnFlingSearchListener listener;

    @Inject
    public OnFlingSearchGestureDetector(OnFlingSearchListener listener) {
        super(listener);
        this.listener = listener;
    }

    public void setDoDetail(boolean doDetail)
    {
        this.listener.setDoDetail(doDetail);
    }
}

package be.baes.hanselMinutesPlayer.controllers;

import android.view.GestureDetector;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 29/12/11
 * Time: 13:21
 */
public class OnFlingMainGestureDetector extends GestureDetector {

    @Inject
    public OnFlingMainGestureDetector(OnFlingMainListener listener) {
        super(listener);
    }
}

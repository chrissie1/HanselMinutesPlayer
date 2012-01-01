package be.baes.hanselMinutesPlayer.controllers;

import android.view.GestureDetector;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 29/12/11
 * Time: 13:19
 */
public class OnFlingDetailsOpenMainGestureDetector extends GestureDetector{

    @Inject
    public OnFlingDetailsOpenMainGestureDetector(OnFlingDetailsOpenMainListener listener) {
        super(listener);
    }
}

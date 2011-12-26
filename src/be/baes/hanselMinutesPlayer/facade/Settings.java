package be.baes.hanselMinutesPlayer.facade;

import android.content.res.Resources;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 20/12/11
 * Time: 21:47
 * To change this template use File | Settings | File Templates.
 */
public interface Settings {
    void initialize(File cacheDirectory);

    File getCacheDirectory();
}

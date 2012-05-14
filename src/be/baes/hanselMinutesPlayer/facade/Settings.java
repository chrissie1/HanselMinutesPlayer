package be.baes.hanselMinutesPlayer.facade;

import java.io.File;
import java.util.Observer;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 20/12/11
 * Time: 21:47
 */
public interface Settings {
    void initialize(File cacheDirectory);

    File getCacheDirectory();

    void UpdateOrInsertSettings(be.baes.hanselMinutesPlayer.model.Settings settings);

    void getSettings();

    void addObserver(Observer observer);

    void deleteObserver(Observer observer);

}

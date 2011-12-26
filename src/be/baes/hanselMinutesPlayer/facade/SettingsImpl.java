package be.baes.hanselMinutesPlayer.facade;

import com.google.inject.Singleton;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 19/12/11
 * Time: 21:15
 */
@Singleton
public class SettingsImpl implements Settings {
    private File cacheDirectory;

    @Override
    public void initialize(File cacheDirectory)
    {
        this.cacheDirectory = cacheDirectory;
    }

    @Override
    public File getCacheDirectory()
    {
        return cacheDirectory;
    }

}

package be.baes.hanselMinutesPlayer.facade;

import be.baes.hanselMinutesPlayer.dal.SettingsAdapter;
import be.baes.hanselMinutesPlayer.ioc.HanselMinutesPlayerModule;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.io.File;
import java.security.PublicKey;
import java.util.Observable;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 19/12/11
 * Time: 21:15
 */
@Singleton
public class SettingsImpl extends Observable implements Settings {
    @Inject SettingsAdapter settingsAdapter;
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
    
    @Override
    public void UpdateOrInsertSettings(be.baes.hanselMinutesPlayer.model.Settings settings)
    {
        settingsAdapter.updateOrInsertSettings(settings);
        setChanged();
        notifyObservers(settings);
    }
    
    @Override
    public void getSettings()
    {
        be.baes.hanselMinutesPlayer.model.Settings settings = settingsAdapter.getSettings();
        setChanged();
        notifyObservers(settings);
    }

}

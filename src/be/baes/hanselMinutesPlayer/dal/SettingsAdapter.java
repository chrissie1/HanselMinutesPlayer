package be.baes.hanselMinutesPlayer.dal;

import be.baes.hanselMinutesPlayer.model.PodCast;
import be.baes.hanselMinutesPlayer.model.Settings;

import java.util.List;

public interface SettingsAdapter {

    long updateOrInsertSettings(Settings settings);

    Settings getSettings();
}

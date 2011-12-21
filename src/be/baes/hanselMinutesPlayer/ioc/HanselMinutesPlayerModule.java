package be.baes.hanselMinutesPlayer.ioc;

import be.baes.hanselMinutesPlayer.dal.PodCastAdapter;
import be.baes.hanselMinutesPlayer.dal.sqliteandroid.PodCastSQLiteAdapter;
import be.baes.hanselMinutesPlayer.facade.*;
import be.baes.hanselMinutesPlayer.helpers.Network;
import be.baes.hanselMinutesPlayer.helpers.NetworkImpl;
import be.baes.hanselMinutesPlayer.view.ProgressReport;
import be.baes.hanselMinutesPlayer.view.ProgressReportAndroid;
import com.google.inject.AbstractModule;

public class HanselMinutesPlayerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PodCastAdapter.class).to(PodCastSQLiteAdapter.class);
        bind(ProgressReport.class).to(ProgressReportAndroid.class);
        bind(Player.class).to(PlayerImpl.class);
        bind(PodCastList.class).to(PodCastListImpl.class);
        bind(Settings.class).to(SettingsImpl.class);
        bind(Network.class).to(NetworkImpl.class);
    }
}

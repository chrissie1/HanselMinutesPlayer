package be.baes.hanselMinutesPlayer.ioc;

import be.baes.hanselMinutesPlayer.dal.PodCastAdapter;
import be.baes.hanselMinutesPlayer.dal.sqliteandroid.PodCastSQLiteAdapter;
import be.baes.hanselMinutesPlayer.facade.*;
import be.baes.hanselMinutesPlayer.helpers.Network;
import be.baes.hanselMinutesPlayer.helpers.NetworkImpl;
import be.baes.hanselMinutesPlayer.resources.ColorResources;
import be.baes.hanselMinutesPlayer.resources.ColorResourcesImpl;
import be.baes.hanselMinutesPlayer.resources.StringResources;
import be.baes.hanselMinutesPlayer.resources.StringResourcesImpl;
import be.baes.hanselMinutesPlayer.view.ProgressReport;
import be.baes.hanselMinutesPlayer.view.ProgressReportAndroid;
import be.baes.hanselMinutesPlayer.view.YesNoAlertDialog;
import be.baes.hanselMinutesPlayer.view.YesNoAlertDialogImpl;
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
        bind(YesNoAlertDialog.class).to(YesNoAlertDialogImpl.class);
        bind(StringResources.class).to(StringResourcesImpl.class);
        bind(ColorResources.class).to(ColorResourcesImpl.class);
    }
}

package be.baes.hanselMinutesPlayer.ioc;

import be.baes.hanselMinutesPlayer.dal.PodCastAdapter;
import be.baes.hanselMinutesPlayer.dal.PodCastSQLiteAdapter;
import com.google.inject.AbstractModule;

public class HanselMinutesPlayerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PodCastAdapter.class).to(PodCastSQLiteAdapter.class);
    }
}

package be.baes.hanselMinutesPlayer.ioc;

import be.baes.hanselMinutesPlayer.dal.PodCastAdapter;
import be.baes.hanselMinutesPlayer.dal.PodCastSQLiteAdapter;
import com.google.inject.AbstractModule;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 9/12/11
 * Time: 10:26
 * To change this template use File | Settings | File Templates.
 */
public class HanselMinutesPlayerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PodCastAdapter.class).to(PodCastSQLiteAdapter.class);
    }
}

package be.baes.hanselMinutesPlayer.controllers;

import android.test.InstrumentationTestCase;
import android.widget.ListView;
import be.baes.hanselMinutesPlayer.MockContext2;
import be.baes.hanselMinutesPlayer.R;
import be.baes.hanselMinutesPlayer.facade.Player;
import be.baes.hanselMinutesPlayer.facade.Settings;
import be.baes.hanselMinutesPlayer.helpers.Network;
import be.baes.hanselMinutesPlayer.helpers.NetworkImpl;
import be.baes.hanselMinutesPlayer.model.PodCast;
import be.baes.hanselMinutesPlayer.view.adapters.PodCastAdapterImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 12/12/11
 * Time: 14:43
 */
public class OnPodCastItemListClickListenerTest extends InstrumentationTestCase{
    private Player player;
    private PodCastItemListClickListener listener;
    private Settings settings;
    private Network network;

    public void setUp()
    {
        player = createMock(Player.class);
        settings = createMock(Settings.class);
        network = createMock(Network.class);
        listener = new PodCastItemListClickListener();
        listener.player = player;
        listener.settings = settings;
        listener.network = network;
    }

    public void testIfPlayerSetCurrentFileIsCalledOnClick()
    {
        MockContext2 context2 = new MockContext2(this);
        PodCast podCast = new PodCast("","","","aaaaaaaaaaaaaaaaaaaaaaaaa");
        List<PodCast> podCasts = new ArrayList<PodCast>();
        podCasts.add(podCast);
        expect(network.haveInternet(context2)).andReturn(true);
        replay(network);
        expect(settings.getCacheDirectory()).andStubReturn(new File("",""));
        expect(settings.NoInternetConnection()).andStubReturn("No internet connection.");
        replay(settings);
        player.setCurrentFile(podCast);
        replay(player);
        ListView adapterView = new ListView(context2);
        PodCastAdapterImpl podCastAdapter = new PodCastAdapterImpl(context2, R.layout.row, podCasts, settings);
        adapterView.setAdapter(podCastAdapter);
        listener.onItemClick(adapterView, null, 0, 0);
        verify(player);
    }

}

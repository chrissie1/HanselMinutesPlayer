package be.baes.hanselMinutesPlayer.controllers;

import android.content.Context;
import android.test.AndroidTestCase;
import be.baes.hanselMinutesPlayer.facade.Player;
import be.baes.hanselMinutesPlayer.helpers.Network;
import be.baes.hanselMinutesPlayer.resources.StringResources;

import static org.easymock.EasyMock.*;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 21/12/11
 * Time: 20:01
 */
public class OnDownloadPodCastClickListenerTest extends AndroidTestCase {
    private OnDownloadPodCastClickListener listener;
    private Network network;
    private Context context;
    private StringResources stringResources;
    private Player player;
    
    public void setUp()
    {
        context = getContext();
        player = createMock(Player.class);
        stringResources = createMock(StringResources.class);
        network = createMock(Network.class);
        listener = new OnDownloadPodCastClickListener();
        listener.network = network;
        listener.stringResources = stringResources;
        listener.player = player;
        listener.context = context;
    }

    public void testIfPlayerDownloadMp3IsCalledOnClickWhenInternet()
    {
        replay(stringResources);
        expect(network.haveInternet(context)).andReturn(true);
        replay(network);
        player.downloadMp3();
        replay(player);
        listener.onClick(null,0);
        verify(player);
    }

    public void testIfSettingsNoInternetConnectionIsCalledWhenNoInternet()
    {
        expect(stringResources.NoInternetConnection()).andReturn("No internet connection");
        replay(stringResources);
        expect(network.haveInternet(context)).andStubReturn(false);
        replay(network);
        listener.onClick(null,0);
        verify(stringResources);
    }

}

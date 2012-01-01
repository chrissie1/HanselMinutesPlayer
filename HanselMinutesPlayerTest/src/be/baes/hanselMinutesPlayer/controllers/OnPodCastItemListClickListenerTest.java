package be.baes.hanselMinutesPlayer.controllers;

import android.content.Context;
import android.test.AndroidTestCase;
import android.widget.ListView;
import be.baes.hanselMinutesPlayer.R;
import be.baes.hanselMinutesPlayer.facade.Player;
import be.baes.hanselMinutesPlayer.facade.Settings;
import be.baes.hanselMinutesPlayer.helpers.Network;
import be.baes.hanselMinutesPlayer.model.PodCast;
import be.baes.hanselMinutesPlayer.resources.ColorResources;
import be.baes.hanselMinutesPlayer.resources.StringResources;
import be.baes.hanselMinutesPlayer.view.adapters.PodCastAdapterImpl;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 12/12/11
 * Time: 14:43
 */
public class OnPodCastItemListClickListenerTest extends AndroidTestCase {
    private Player player;
    private OnPodCastItemListClickListener listener;
    private StringResources stringResources;
    private Network network;
    private Context context;
    private Settings settings;
    private ColorResources colorResources;

    public void setUp()
    {
        context = getContext();
        settings = createMock(Settings.class);
        player = createMock(Player.class);
        colorResources = createMock(ColorResources.class);
        stringResources = createMock(StringResources.class);
        network = createMock(Network.class);
        listener = new OnPodCastItemListClickListener();
        listener.player = player;
        listener.stringResources = stringResources;
        listener.network = network;
    }

    public void testIfPlayerSetCurrentFileIsCalledOnClickWhenHaveInternetAndDownloadedFile()
    {
        PodCast podCast = new PodCast("","","","aaaaaaaaaaaaaaaaaaaaaaaaa","");
        List<PodCast> podCasts = new ArrayList<PodCast>();
        podCasts.add(podCast);
        expect(network.haveInternet(context)).andReturn(true);
        replay(network);
        player.setCurrentFile(podCast);
        expect(player.hasPodCastDownloadedMp3(podCast)).andReturn(true);
        replay(player);
        ListView adapterView = new ListView(context);
        PodCastAdapterImpl podCastAdapter = new PodCastAdapterImpl(context, R.layout.row, podCasts, settings, stringResources, colorResources);
        adapterView.setAdapter(podCastAdapter);
        listener.onItemClick(adapterView, null, 0, 0);
        verify(player);
    }

    public void testIfPlayerSetCurrentFileIsCalledOnClickWhenHaveNoInternetAndHaveDownloadedFile()
    {
        PodCast podCast = new PodCast("","","","aaaaaaaaaaaaaaaaaaaaaaaaa","");
        List<PodCast> podCasts = new ArrayList<PodCast>();
        podCasts.add(podCast);
        expect(network.haveInternet(context)).andReturn(false);
        replay(network);
        player.setCurrentFile(podCast);
        expect(player.hasPodCastDownloadedMp3(podCast)).andReturn(true);
        replay(player);
        ListView adapterView = new ListView(context);
        PodCastAdapterImpl podCastAdapter = new PodCastAdapterImpl(context, R.layout.row, podCasts, settings, stringResources, colorResources);
        adapterView.setAdapter(podCastAdapter);
        listener.onItemClick(adapterView, null, 0, 0);
        verify(player);
    }

    public void testIfSettingsNoInternetConnectionIsCalledWhenNoInternetAndNoDownloadedFile()
    {
        PodCast podCast = new PodCast("","","","aaaaaaaaaaaaaaaaaaaaaaaaa","");
        List<PodCast> podCasts = new ArrayList<PodCast>();
        podCasts.add(podCast);
        expect(network.haveInternet(context)).andReturn(false);
        replay(network);
        expect(player.hasPodCastDownloadedMp3(podCast)).andReturn(false);
        replay(player);
        expect(stringResources.NoInternetConnection()).andStubReturn("No internet connection.");
        replay(stringResources);
        ListView adapterView = new ListView(context);
        PodCastAdapterImpl podCastAdapter = new PodCastAdapterImpl(context, R.layout.row, podCasts, settings, stringResources, colorResources);
        adapterView.setAdapter(podCastAdapter);
        listener.onItemClick(adapterView, null, 0, 0);
        verify(stringResources);
    }

}

package be.baes.hanselMinutesPlayer.controllers;

import android.content.Context;
import android.test.AndroidTestCase;
import android.widget.Button;
import be.baes.hanselMinutesPlayer.facade.Player;
import be.baes.hanselMinutesPlayer.facade.Settings;
import be.baes.hanselMinutesPlayer.helpers.Network;
import be.baes.hanselMinutesPlayer.view.YesNoAlertDialog;

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
    private Settings settings;
    private YesNoAlertDialog yesNoAlertDialog;
    private Player player;
    
    public void setUp()
    {
        context = getContext();
        player = createMock(Player.class);
        yesNoAlertDialog = createMock(YesNoAlertDialog.class);
        settings = createMock(Settings.class);
        network = createMock(Network.class);
        listener = new OnDownloadPodCastClickListener();
        listener.network = network;
        listener.settings = settings;
        listener.yesNoAlertDialog = yesNoAlertDialog;
        listener.player = player;
        expect(settings.getDownloadPodCastTitle()).andReturn("testTitle").atLeastOnce();
        expect(settings.getDownloadPodcastMessage()).andReturn("testMessage").atLeastOnce();
    }

    public void testIfPlayerDownloadMp3IsCalledOnClickWhenInternet()
    {
        Button button = new Button(context);
        replay(settings);
        expect(yesNoAlertDialog.show(button,settings.getDownloadPodCastTitle(), settings.getDownloadPodcastMessage())).andStubReturn(true);
        replay(yesNoAlertDialog);
        expect(network.haveInternet(context)).andReturn(true);
        replay(network);
        player.downloadMp3();
        replay(player);
        listener.onClick(button);
        verify(player);
    }

    public void testIfSettingsNoInternetConnectionIsCalledWhenNoInternet()
    {
        Button button = new Button(context);
        expect(settings.NoInternetConnection()).andReturn("No internet connection");
        replay(settings);
        expect(yesNoAlertDialog.show(button,settings.getDownloadPodCastTitle(), settings.getDownloadPodcastMessage())).andStubReturn(true);
        replay(yesNoAlertDialog);
        expect(network.haveInternet(context)).andStubReturn(false);
        replay(network);
        listener.onClick(button);
        verify(settings);
    }

    public void testIfYesNoAlertDialogGetsCalled()
    {
        Button button = new Button(context);
        replay(settings);
        expect(yesNoAlertDialog.show(button,settings.getDownloadPodCastTitle(), settings.getDownloadPodcastMessage())).andStubReturn(false);
        replay(yesNoAlertDialog);
        listener.onClick(button);
        verify(yesNoAlertDialog);
    }
    
}

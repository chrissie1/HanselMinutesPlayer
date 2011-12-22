package be.baes.hanselMinutesPlayer.controllers;

import android.test.AndroidTestCase;
import android.widget.Button;
import be.baes.hanselMinutesPlayer.facade.Player;
import be.baes.hanselMinutesPlayer.facade.Settings;
import be.baes.hanselMinutesPlayer.model.PodCast;
import be.baes.hanselMinutesPlayer.view.YesNoAlertDialog;

import java.io.File;

import static org.easymock.EasyMock.*;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 21/12/11
 * Time: 19:08
 */
public class OnDeleteDownloadPodCastClickListenerTest extends AndroidTestCase
{
    private Player player;
    private OnDeleteDownloadedPodCastClickListener listener;
    private YesNoAlertDialog yesNoAlertDialog;
    private Settings settings;            

    public void setUp()
    {
        player = createMock(Player.class);
        settings = createMock(Settings.class);
        yesNoAlertDialog = createMock(YesNoAlertDialog.class);
        listener = new OnDeleteDownloadedPodCastClickListener();
        listener.player = player;
        listener.yesNoAlertDialog = yesNoAlertDialog;
        listener.settings = settings;
    }

    public void testIfYesNoAlertDialogIsCalledOnClick()
    {
        Button button = new Button(getContext());
        expect(settings.getCacheDirectory()).andReturn(new File("",""));
        expect(settings.NoFileToDelete()).andReturn("No file to delete.");
        expect(settings.getDeleteDownloadTitle()).andReturn("testTitle").atLeastOnce();
        expect(settings.getDeleteDownloadMessage()).andReturn("testMessage").atLeastOnce();
        replay(settings);
        expect(player.getCurrentPodCast()).andReturn(new PodCast("","","","aaaaaaaaaaaaaaaaaaaaaaaa"));
        replay(player);
        expect(yesNoAlertDialog.show(button, settings.getDeleteDownloadTitle(), settings.getDeleteDownloadMessage())).andStubReturn(true);
        replay(yesNoAlertDialog);
        listener.onClick(button);
        verify(yesNoAlertDialog);
    }
}

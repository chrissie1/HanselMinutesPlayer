package be.baes.hanselMinutesPlayer.controllers;

import android.test.AndroidTestCase;
import android.widget.Button;
import be.baes.hanselMinutesPlayer.dal.PodCastAdapter;
import be.baes.hanselMinutesPlayer.facade.Player;
import be.baes.hanselMinutesPlayer.facade.PodCastList;
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
 * To change this template use File | Settings | File Templates.
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
        replay(settings);
        expect(player.getCurrentPodCast()).andReturn(new PodCast("","","","aaaaaaaaaaaaaaaaaaaaaaaa"));
        replay(player);
        expect(yesNoAlertDialog.show(button, "Delete download?", "Are you sure you want to delete the downloaded podcast?")).andStubReturn(true);
        replay(yesNoAlertDialog);
        listener.onClick(button);
        verify(yesNoAlertDialog);
    }
}

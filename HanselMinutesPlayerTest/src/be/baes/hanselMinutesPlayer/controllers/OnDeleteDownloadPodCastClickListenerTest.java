package be.baes.hanselMinutesPlayer.controllers;

import android.test.AndroidTestCase;
import be.baes.hanselMinutesPlayer.facade.Player;
import be.baes.hanselMinutesPlayer.resources.StringResources;

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
    private StringResources stringResources;

    public void setUp()
    {
        player = createMock(Player.class);
        stringResources = createMock(StringResources.class);
        listener = new OnDeleteDownloadedPodCastClickListener();
        listener.player = player;
        listener.stringResources = stringResources;
        listener.context = getContext();
    }

    public void testIfPlayerDeleteDownloadedMp3IsCalledWhenDownloadedMp3Exists()
    {
        expect(player.hasCurrentPodCastDownloadedMp3()).andReturn(true);
        player.deleteCurrentPodCastDownLoadedMp3();
        replay(player);
        listener.onClick(null,0);
        verify(player);
    }

    public void testIfToastIsShownWhenDownloadedMp3DoesNotExists()
    {
        expect(stringResources.NoFileToDelete()).andReturn("No file to delete.");
        replay(stringResources);
        expect(player.hasCurrentPodCastDownloadedMp3()).andReturn(false);
        replay(player);
        listener.onClick(null,0);
        verify(stringResources);
    }
}

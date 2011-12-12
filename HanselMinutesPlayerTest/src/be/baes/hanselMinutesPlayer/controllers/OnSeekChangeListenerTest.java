package be.baes.hanselMinutesPlayer.controllers;

import be.baes.hanselMinutesPlayer.facade.Player;
import junit.framework.TestCase;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 12/12/11
 * Time: 16:17
 */
public class OnSeekChangeListenerTest extends TestCase {

    private Player player;
    private OnSeekChangeListener listener;

    public void setUp()
    {
        player = createMock(Player.class);
        listener = new OnSeekChangeListener();
        listener.player = player;
    }

    public void testIfPlayerPlayIsCalledWhenOnStopTrackingTouch()
    {
        player.play();
        replay(player);
        listener.onStopTrackingTouch(null);
        verify(player);
    }

    public void testIfPlayerPauseIsCalledWhenOnStartTrackingTouch()
    {
        player.pause();
        replay(player);
        listener.onStartTrackingTouch(null);
        verify(player);
    }

    public void testIfPlayerSeekToIsCalledWhenOnProgressChanged()
    {
        player.pause();
        player.seekTo(0);
        replay(player);
        listener.onStartTrackingTouch(null);
        listener.onProgressChanged(null,0,true);
        verify(player);
    }
}

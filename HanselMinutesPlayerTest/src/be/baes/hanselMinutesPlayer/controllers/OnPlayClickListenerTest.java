package be.baes.hanselMinutesPlayer.controllers;

import be.baes.hanselMinutesPlayer.facade.Player;
import junit.framework.TestCase;

import static org.easymock.EasyMock.*;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 12/12/11
 * Time: 14:43
 */
public class OnPlayClickListenerTest extends TestCase{
    private Player player;
    private OnPlayClickListener listener;

    public void setUp()
    {
        player = createMock(Player.class);
        listener = new OnPlayClickListener();
        listener.player = player;
    }

    public void testIfPlayerPlayIsCalledOnClick()
    {
        player.play();
        replay(player);
        listener.onClick(null);
        verify(player);
    }
}

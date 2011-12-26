package be.baes.hanselMinutesPlayer.controllers;

import android.test.AndroidTestCase;
import be.baes.hanselMinutesPlayer.dal.PodCastAdapter;
import be.baes.hanselMinutesPlayer.facade.Player;
import be.baes.hanselMinutesPlayer.facade.PodCastList;

import static org.easymock.EasyMock.*;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 12/12/11
 * Time: 14:07
 */
public class OnDeleteAllClickListenerTest extends AndroidTestCase {

    private PodCastAdapter podCastAdapter;
    private PodCastList podCastList;
    private Player player;
    private OnDeleteAllClickListener listener;

    public void setUp()
    {
        podCastAdapter = createMock(PodCastAdapter.class);
        player = createMock(Player.class);
        podCastList = createMock(PodCastList.class);
        listener = new OnDeleteAllClickListener();
        listener.podCastAdapter = podCastAdapter;
        listener.player = player;
        listener.podCastList = podCastList;
   }

    public void testIfPodCastAdapterDeleteAllIsCalledOnClick()
    {
        expect(podCastAdapter.deleteAll()).andReturn(true);
        replay(podCastAdapter);
        listener.onClick(null,0);
        verify(podCastAdapter);
    }

    public void testIfPodCastListLoadIsCalledOnClick()
    {
        podCastList.load(0);
        replay(podCastList);
        listener.onClick(null,0);
        verify(podCastList);
    }

    public void testIfPlayerSetCurrentFileIsCalledOnClick()
    {
        player.setCurrentFile(null);
        replay(player);
        listener.onClick(null,0);
        verify(player);
    }
}

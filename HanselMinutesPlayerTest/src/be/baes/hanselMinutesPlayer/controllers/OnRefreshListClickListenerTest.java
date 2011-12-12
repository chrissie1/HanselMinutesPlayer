package be.baes.hanselMinutesPlayer.controllers;

import be.baes.hanselMinutesPlayer.facade.PodCastList;
import junit.framework.TestCase;

import static org.easymock.EasyMock.*;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 12/12/11
 * Time: 14:43
 */
public class OnRefreshListClickListenerTest extends TestCase{
    private PodCastList podCastList;
    private OnRefreshListClickListener listener;

    public void setUp()
    {
        podCastList = createMock(PodCastList.class);
        listener = new OnRefreshListClickListener();
        listener.podCastList = podCastList;
    }

    public void testIfPlayerPauseIsCalledOnClick()
    {
        podCastList.getListFromRssAndUpdateDatabase();
        replay(podCastList);
        listener.onClick(null);
        verify(podCastList);
    }
}

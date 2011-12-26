package be.baes.hanselMinutesPlayer.controllers;

import android.test.InstrumentationTestCase;
import android.widget.ListView;
import be.baes.hanselMinutesPlayer.MockContext2;
import be.baes.hanselMinutesPlayer.dal.PodCastAdapter;
import be.baes.hanselMinutesPlayer.facade.PodCastList;

import static org.easymock.EasyMock.*;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 12/12/11
 * Time: 22:46
 */
public class OnScrollPodCastListListenerTest extends InstrumentationTestCase {
    private PodCastList podCastList;
    private OnScrollPodCastListListener listener;
    private PodCastAdapter podCastAdapter;

    public void setUp()
    {
        podCastList = createMock(PodCastList.class);
        podCastAdapter = createMock(PodCastAdapter.class);
        listener = new OnScrollPodCastListListener();
        listener.podCastList = podCastList;
        listener.podCastAdapter = podCastAdapter;
    }

    public void testIfPodCastListLoadIsCalledWhenScrollPassesLastLoadedElement()
    {
        expect(podCastAdapter.numberOfPodcasts()).andStubReturn(200);
        replay(podCastAdapter);
        podCastList.load(1);
        expect(podCastList.getCurrentPage()).andStubReturn(0);
        expect(podCastList.getCurrentPage()).andStubReturn(1);
        replay(podCastList);
        ListView listView = new ListView(new MockContext2(this));
        listener.onScroll(listView, 0, 6, 10);
        listener.onScroll(listView,5,5,10);
        verify(podCastList);
    }

}

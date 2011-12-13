package be.baes.hanselMinutesPlayer.controllers;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.test.InstrumentationTestCase;
import android.test.mock.MockContext;
import android.widget.ListView;
import be.baes.hanselMinutesPlayer.facade.PodCastList;
import org.easymock.EasyMock;

import static org.easymock.EasyMock.*;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 12/12/11
 * Time: 22:46
 * To change this template use File | Settings | File Templates.
 */
public class OnScrollPodCastListListenerTest extends InstrumentationTestCase {
    private PodCastList podCastList;
    private OnScrollPodCastListListener listener;

    public void setUp()
    {
        podCastList = createMock(PodCastList.class);
        listener = new OnScrollPodCastListListener();
        listener.podCastList = podCastList;
    }

    public void testIfPodCastListLoadIsCalledWhenScrollPassesLastLoadedElement()
    {
        podCastList.load(1);
        replay(podCastList);
        ListView listView = new ListView(new MockContext2());
        listener.onScroll(listView, 0, 6, 10);
        listener.onScroll(listView,5,6,10);
        verify(podCastList);
    }

    private class MockContext2 extends MockContext {

        @Override
        public Resources getResources() {
            return getInstrumentation().getTargetContext().getResources();
        }

        @Override
        public Resources.Theme getTheme() {
            return getInstrumentation().getTargetContext().getTheme();
        }

        @Override
        public Object getSystemService(String name) {
            if (Context.LAYOUT_INFLATER_SERVICE.equals(name)) {
                return getInstrumentation().getTargetContext().getSystemService(name);
            }
            return super.getSystemService(name);
        }

        @Override
        public ApplicationInfo getApplicationInfo()
        {
            return new ApplicationInfo();
        }
    }
}

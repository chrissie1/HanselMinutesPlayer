package be.baes.hanselMinutesPlayer.controllers;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.test.InstrumentationTestCase;
import android.test.mock.MockContext;
import android.widget.ListView;
import be.baes.hanselMinutesPlayer.R;
import be.baes.hanselMinutesPlayer.facade.Player;
import be.baes.hanselMinutesPlayer.model.PodCast;
import be.baes.hanselMinutesPlayer.view.adapters.PodCastAdapterImpl;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 12/12/11
 * Time: 14:43
 */
public class OnPodCastItemListClickListenerTest extends InstrumentationTestCase{
    private Player player;
    private PodCastItemListClickListener listener;

    public void setUp()
    {
        player = createMock(Player.class);
        listener = new PodCastItemListClickListener();
        listener.player = player;
    }

    public void testIfPlayerSetCurrentFileIsCalledOnClick()
    {
        PodCast podCast = new PodCast("","","","");
        List<PodCast> podCasts = new ArrayList<PodCast>();
        podCasts.add(podCast);
        player.setCurrentFile(podCast);
        replay(player);
        ListView adapterView = new ListView(new MockContext2());
        PodCastAdapterImpl podCastAdapter = new PodCastAdapterImpl(new MockContext2(), R.layout.row,podCasts);
        adapterView.setAdapter(podCastAdapter);
        listener.onItemClick(adapterView, null, 0, 0);
        verify(player);
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

package be.baes.hanselMinutesPlayer.controllers;

import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;
import android.test.mock.MockContext;
import android.widget.Button;
import android.widget.ViewAnimator;
import be.baes.hanselMinutesPlayer.MockContext2;
import be.baes.hanselMinutesPlayer.dal.PodCastAdapter;
import be.baes.hanselMinutesPlayer.facade.Player;
import be.baes.hanselMinutesPlayer.facade.PodCastList;
import be.baes.hanselMinutesPlayer.facade.Settings;
import be.baes.hanselMinutesPlayer.view.YesNoAlertDialog;
import junit.framework.TestCase;
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
    private YesNoAlertDialog yesNoAlertDialog;
    private Settings settings;

    public void setUp()
    {
        podCastAdapter = createMock(PodCastAdapter.class);
        player = createMock(Player.class);
        settings = createMock(Settings.class);
        yesNoAlertDialog = createMock(YesNoAlertDialog.class);
        podCastList = createMock(PodCastList.class);
        listener = new OnDeleteAllClickListener();
        listener.podCastAdapter = podCastAdapter;
        listener.player = player;
        listener.settings = settings;
        listener.podCastList = podCastList;
        listener.yesNoAlertDialog = yesNoAlertDialog;
        expect(settings.getDeleteAllTitle()).andStubReturn("testTitle");
        expect(settings.getDeleteAllMessage()).andStubReturn("testMessage");
        replay(settings);
   }

    public void testIfPodCastAdapterDeleteAllIsCalledOnClick()
    {
        Button button = new Button(getContext());
        expect(yesNoAlertDialog.show(button,settings.getDeleteAllTitle(), settings.getDeleteAllMessage())).andStubReturn(true);
        replay(yesNoAlertDialog);
        expect(podCastAdapter.deleteAll()).andReturn(true);
        replay(podCastAdapter);
        listener.onClick(button);
        verify(podCastAdapter);
    }

    public void testIfPodCastListLoadIsCalledOnClick()
    {
        Button button = new Button(getContext());
        expect(yesNoAlertDialog.show(button,settings.getDeleteAllTitle(), settings.getDeleteAllMessage())).andStubReturn(true);
        replay(yesNoAlertDialog);
        podCastList.load(0);
        replay(podCastList);
        listener.onClick(button);
        verify(podCastList);
    }

    public void testIfPlayerSetCurrentFileIsCalledOnClick()
    {
        Button button = new Button(getContext());
        expect(yesNoAlertDialog.show(button,settings.getDeleteAllTitle(), settings.getDeleteAllMessage())).andStubReturn(true);
        replay(yesNoAlertDialog);
        player.setCurrentFile(null);
        replay(player);
        listener.onClick(button);
        verify(player);
    }

    public void testIfYesNoAlertDialogGetsCalled()
    {
        Button button = new Button(getContext());
        expect(yesNoAlertDialog.show(button,settings.getDeleteAllTitle(), settings.getDeleteAllMessage())).andStubReturn(false);
        replay(yesNoAlertDialog);
        listener.onClick(button);
        verify(yesNoAlertDialog);
    }


}

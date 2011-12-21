package be.baes.hanselMinutesPlayer.controllers;

import android.content.Context;
import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;
import android.widget.Button;
import be.baes.hanselMinutesPlayer.MockContext2;
import be.baes.hanselMinutesPlayer.facade.PodCastList;
import be.baes.hanselMinutesPlayer.facade.Settings;
import be.baes.hanselMinutesPlayer.helpers.Network;
import be.baes.hanselMinutesPlayer.view.YesNoAlertDialog;
import junit.framework.TestCase;
import org.junit.rules.ExpectedException;

import static org.easymock.EasyMock.*;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 12/12/11
 * Time: 14:43
 */
public class OnRefreshListClickListenerTest extends AndroidTestCase {
    private PodCastList podCastList;
    private OnRefreshListClickListener listener;
    private Network network;
    private Context context;
    private Settings settings;
    private YesNoAlertDialog yesNoAlertDialog;

    public void setUp()
    {
        context = getContext();
        yesNoAlertDialog = createMock(YesNoAlertDialog.class);
        podCastList = createMock(PodCastList.class);
        settings = createMock(Settings.class);
        network = createMock(Network.class);
        listener = new OnRefreshListClickListener();
        listener.podCastList = podCastList;
        listener.network = network;
        listener.settings = settings;
        listener.yesNoAlertDialog = yesNoAlertDialog;
    }

    public void testIfPodCastListgetListFromRssAndUpdateDatabaseIsCalledOnClickWhenInternet()
    {
        Button button = new Button(context);
        expect(yesNoAlertDialog.show(button,"Refresh list", "Are you sure you want to refresh the list? This could take a while.")).andStubReturn(true);
        replay(yesNoAlertDialog);
        expect(network.haveInternet(context)).andReturn(true);
        replay(network);
        podCastList.getListFromRssAndUpdateDatabase();
        replay(podCastList);
        listener.onClick(button);
        verify(podCastList);
    }

    public void testIfSettingsNoInternetConnectionIsCalledWhenNoInternet()
    {
        Button button = new Button(context);
        expect(yesNoAlertDialog.show(button,"Refresh list", "Are you sure you want to refresh the list? This could take a while.")).andStubReturn(true);
        replay(yesNoAlertDialog);
        expect(settings.NoInternetConnection()).andReturn("No internet connection");
        replay(settings);
        expect(network.haveInternet(context)).andStubReturn(false);
        replay(network);
        listener.onClick(button);
        verify(settings);
    }

    public void testIfYesNoAlertDialogGetsCalled()
    {
        Button button = new Button(context);
        expect(yesNoAlertDialog.show(button,"Refresh list", "Are you sure you want to refresh the list? This could take a while.")).andStubReturn(false);
        replay(yesNoAlertDialog);
        listener.onClick(button);
        verify(yesNoAlertDialog);
    }

}

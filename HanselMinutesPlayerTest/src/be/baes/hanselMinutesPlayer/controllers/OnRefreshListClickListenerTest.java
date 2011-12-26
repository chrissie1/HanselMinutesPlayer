package be.baes.hanselMinutesPlayer.controllers;

import android.content.Context;
import android.test.AndroidTestCase;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.facade.PodCastList;
import be.baes.hanselMinutesPlayer.facade.Settings;
import be.baes.hanselMinutesPlayer.helpers.Network;
import be.baes.hanselMinutesPlayer.resources.StringResources;

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
    private StringResources stringResources;

    public void setUp()
    {
        context = getContext();
        podCastList = createMock(PodCastList.class);
        stringResources = createMock(StringResources.class);
        network = createMock(Network.class);
        listener = new OnRefreshListClickListener();
        listener.podCastList = podCastList;
        listener.network = network;
        listener.stringResources = stringResources;
        listener.context = context;
    }

    public void testIfPodCastListgetListFromRssAndUpdateDatabaseIsCalledOnClickWhenInternet()
    {
        replay(stringResources);
        expect(network.haveInternet(context)).andReturn(true);
        replay(network);
        podCastList.getListFromRssAndUpdateDatabase(Constants.urlToRssFeedAll);
        replay(podCastList);
        listener.onClick(null,0);
        verify(podCastList);
    }

    public void testIfSettingsNoInternetConnectionIsCalledWhenNoInternet()
    {
        expect(stringResources.NoInternetConnection()).andReturn("No internet connection");
        replay(stringResources);
        expect(network.haveInternet(context)).andStubReturn(false);
        replay(network);
        listener.onClick(null,0);
        verify(stringResources);
    }
}

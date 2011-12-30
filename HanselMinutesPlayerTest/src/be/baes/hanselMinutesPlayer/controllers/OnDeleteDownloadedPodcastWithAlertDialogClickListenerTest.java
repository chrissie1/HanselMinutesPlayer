package be.baes.hanselMinutesPlayer.controllers;

import android.test.AndroidTestCase;
import android.widget.Button;
import be.baes.hanselMinutesPlayer.resources.StringResources;
import be.baes.hanselminutesplayer.view.YesNoAlertDialog;

import static org.easymock.EasyMock.*;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 12/12/11
 * Time: 14:07
 */
public class OnDeleteDownloadedPodcastWithAlertDialogClickListenerTest extends AndroidTestCase {

    private YesNoAlertDialog yesNoAlertDialog;
    private StringResources stringResources;
    private OnDeleteDownloadedPodCastWithAlertDialogClickListener listener;

    public void setUp()
    {
        yesNoAlertDialog = createMock(YesNoAlertDialog.class);
        stringResources = createMock(StringResources.class);
        listener = new OnDeleteDownloadedPodCastWithAlertDialogClickListener();
        listener.stringResources = stringResources;
        listener.yesNoAlertDialog = yesNoAlertDialog;
   }

    public void testIfYesNoAlertDialogIsCalledOnClick()
    {
        Button button = new Button(getContext());
        expect(stringResources.getDeleteDownloadMessage()).andReturn("message").atLeastOnce();
        expect(stringResources.getDeleteDownloadTitle()).andReturn("title").atLeastOnce();
        replay(stringResources);
        yesNoAlertDialog.show(button, stringResources.getDeleteDownloadTitle(), stringResources.getDeleteDownloadMessage(), null, null);
        replay(yesNoAlertDialog);
        listener.onClick(button);
        verify(yesNoAlertDialog);
    }

    public void testIfSettingsAreCalledOnClick()
    {
        Button button = new Button(getContext());
        expect(stringResources.getDeleteDownloadMessage()).andReturn("message").atLeastOnce();
        expect(stringResources.getDeleteDownloadTitle()).andReturn("title").atLeastOnce();
        replay(stringResources);
        yesNoAlertDialog.show(button, stringResources.getDeleteDownloadTitle(), stringResources.getDeleteDownloadMessage(), null, null);
        replay(yesNoAlertDialog);
        listener.onClick(button);
        verify(stringResources);
    }

}

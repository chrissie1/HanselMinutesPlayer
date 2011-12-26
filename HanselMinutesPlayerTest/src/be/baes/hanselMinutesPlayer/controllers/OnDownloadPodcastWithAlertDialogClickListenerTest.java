package be.baes.hanselMinutesPlayer.controllers;

import android.test.AndroidTestCase;
import android.widget.Button;
import be.baes.hanselMinutesPlayer.resources.StringResources;
import be.baes.hanselMinutesPlayer.view.YesNoAlertDialog;

import static org.easymock.EasyMock.*;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 12/12/11
 * Time: 14:07
 */
public class OnDownloadPodcastWithAlertDialogClickListenerTest extends AndroidTestCase {

    private YesNoAlertDialog yesNoAlertDialog;
    private StringResources stringResources;
    private OnDownloadPodCastWithAlertDialogClickListener listener;

    public void setUp()
    {
        yesNoAlertDialog = createMock(YesNoAlertDialog.class);
        stringResources = createMock(StringResources.class);
        listener = new OnDownloadPodCastWithAlertDialogClickListener();
        listener.stringResources = stringResources;
        listener.yesNoAlertDialog = yesNoAlertDialog;
   }

    public void testIfYesNoAlertDialogIsCalledOnClick()
    {
        Button button = new Button(getContext());
        expect(stringResources.getDownloadPodcastMessage()).andReturn("message").atLeastOnce();
        expect(stringResources.getDownloadPodCastTitle()).andReturn("title").atLeastOnce();
        replay(stringResources);
        yesNoAlertDialog.show(button, stringResources.getDownloadPodCastTitle(), stringResources.getDownloadPodcastMessage(), null, null);
        replay(yesNoAlertDialog);
        listener.onClick(button);
        verify(yesNoAlertDialog);
    }

    public void testIfSettingsAreCalledOnClick()
    {
        Button button = new Button(getContext());
        expect(stringResources.getDownloadPodcastMessage()).andReturn("message").atLeastOnce();
        expect(stringResources.getDownloadPodCastTitle()).andReturn("title").atLeastOnce();
        replay(stringResources);
        yesNoAlertDialog.show(button, stringResources.getDownloadPodCastTitle(), stringResources.getDownloadPodcastMessage(), null, null);
        replay(yesNoAlertDialog);
        listener.onClick(button);
        verify(stringResources);
    }

}

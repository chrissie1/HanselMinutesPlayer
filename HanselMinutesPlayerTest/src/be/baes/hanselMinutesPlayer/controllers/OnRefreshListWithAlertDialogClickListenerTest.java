package be.baes.hanselMinutesPlayer.controllers;

import android.test.AndroidTestCase;
import android.widget.Button;
import be.baes.hanselMinutesPlayer.facade.Settings;
import be.baes.hanselMinutesPlayer.view.YesNoAlertDialog;

import static org.easymock.EasyMock.*;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 12/12/11
 * Time: 14:07
 */
public class OnRefreshListWithAlertDialogClickListenerTest extends AndroidTestCase {

    private YesNoAlertDialog yesNoAlertDialog;
    private Settings settings;
    private OnRefreshListWithAlertDialogClickListener listener;

    public void setUp()
    {
        yesNoAlertDialog = createMock(YesNoAlertDialog.class);
        settings = createMock(Settings.class);
        listener = new OnRefreshListWithAlertDialogClickListener();
        listener.settings = settings;
        listener.yesNoAlertDialog = yesNoAlertDialog;
   }

    public void testIfYesNoAlertDialogIsCalledOnClick()
    {
        Button button = new Button(getContext());
        expect(settings.getRefreshListMessage()).andReturn("message").atLeastOnce();
        expect(settings.getRefreshListTitle()).andReturn("title").atLeastOnce();
        replay(settings);
        yesNoAlertDialog.show(button,settings.getRefreshListTitle(), settings.getRefreshListMessage(),null,null);
        replay(yesNoAlertDialog);
        listener.onClick(button);
        verify(yesNoAlertDialog);
    }

    public void testIfSettingsAreCalledOnClick()
    {
        Button button = new Button(getContext());
        expect(settings.getRefreshListMessage()).andReturn("message").atLeastOnce();
        expect(settings.getRefreshListTitle()).andReturn("title").atLeastOnce();
        replay(settings);
        yesNoAlertDialog.show(button,settings.getRefreshListTitle(), settings.getRefreshListMessage(),null,null);
        replay(yesNoAlertDialog);
        listener.onClick(button);
        verify(settings);
    }

}

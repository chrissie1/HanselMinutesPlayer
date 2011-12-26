package be.baes.hanselMinutesPlayer.controllers;

import android.test.AndroidTestCase;
import android.widget.Button;
import be.baes.hanselMinutesPlayer.dal.PodCastAdapter;
import be.baes.hanselMinutesPlayer.facade.Player;
import be.baes.hanselMinutesPlayer.facade.PodCastList;
import be.baes.hanselMinutesPlayer.facade.Settings;
import be.baes.hanselMinutesPlayer.view.YesNoAlertDialog;

import static org.easymock.EasyMock.*;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 12/12/11
 * Time: 14:07
 */
public class OnDeleteAllWithAlertDialogClickListenerTest extends AndroidTestCase {

    private YesNoAlertDialog yesNoAlertDialog;
    private Settings settings;
    private OnDeleteAllWithAlertDialogClickListener listener;

    public void setUp()
    {
        yesNoAlertDialog = createMock(YesNoAlertDialog.class);
        settings = createMock(Settings.class);
        listener = new OnDeleteAllWithAlertDialogClickListener();
        listener.settings = settings;
        listener.yesNoAlertDialog = yesNoAlertDialog;
   }

    public void testIfYesNoAlertDialogIsCalledOnClick()
    {
        Button button = new Button(getContext());
        expect(settings.getDeleteAllMessage()).andReturn("message").atLeastOnce();
        expect(settings.getDeleteAllTitle()).andReturn("title").atLeastOnce();
        replay(settings);
        yesNoAlertDialog.show(button,settings.getDeleteAllTitle(), settings.getDeleteAllMessage(),null,null);
        replay(yesNoAlertDialog);
        listener.onClick(button);
        verify(yesNoAlertDialog);
    }

    public void testIfSettingsAreCalledOnClick()
    {
        Button button = new Button(getContext());
        expect(settings.getDeleteAllMessage()).andReturn("message").atLeastOnce();
        expect(settings.getDeleteAllTitle()).andReturn("title").atLeastOnce();
        replay(settings);
        yesNoAlertDialog.show(button,settings.getDeleteAllTitle(), settings.getDeleteAllMessage(),null,null);
        replay(yesNoAlertDialog);
        listener.onClick(button);
        verify(settings);
    }

}

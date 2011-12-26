package be.baes.hanselMinutesPlayer.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 21/12/11
 * Time: 15:49
 * To change this template use File | Settings | File Templates.
 */
public class YesNoAlertDialogImpl implements YesNoAlertDialog {

    @Override
    public void show(View view, String title, String message, DialogInterface.OnClickListener positiveClickListener, DialogInterface.OnClickListener negativeClickListener)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());

        alert.setTitle(title);
        alert.setMessage(message);

        alert.setPositiveButton("Yes",positiveClickListener);

        alert.setNegativeButton("No", negativeClickListener);
        alert.show();
    }
    
}

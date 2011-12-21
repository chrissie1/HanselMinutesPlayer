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

    private boolean result = false;

    @Override
    public boolean show(View view, String title, String message)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());

        alert.setTitle(title);
        alert.setMessage(message);

        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                result = true;
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                result = false;
            }
        });
        alert.show();
        return result;
    }
    
}

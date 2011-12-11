package be.baes.hanselMinutesPlayer.view;

import android.app.Activity;
import android.app.ProgressDialog;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 11/12/11
 * Time: 15:56
 * To change this template use File | Settings | File Templates.
 */
public class ProgressReportAndroid implements ProgressReport {
    @Inject Activity activity;
    ProgressDialog progressDialog;

    @Override
    public void startProgress(String message)
    {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(message);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    @Override
    public void endProgress()
    {
        progressDialog.dismiss();
    }

    @Override
    public void updateProgess(String message) {
        progressDialog.setMessage(message);
    }
}

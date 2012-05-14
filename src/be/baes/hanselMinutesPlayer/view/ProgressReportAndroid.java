package be.baes.hanselMinutesPlayer.view;

import android.app.Activity;
import android.app.ProgressDialog;
import com.google.inject.Singleton;

@Singleton
public class ProgressReportAndroid implements ProgressReport {
    Activity activity;
    private ProgressDialog progressDialog;
    private Boolean inProgress;

    @Override
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void startProgress(String message)
    {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(message);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        inProgress = true;
    }

    @Override
    public void endProgress()
    {
        progressDialog.dismiss();
        inProgress = false;
    }

    @Override
    public void updateProgess(String message) {
        progressDialog.setMessage(message);
    }

    @Override
    public boolean IsInProgress()
    {
        return inProgress;
    }
}

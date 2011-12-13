package be.baes.hanselMinutesPlayer.view;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import roboguice.inject.ContextScoped;
import roboguice.inject.ContextScopedProvider;

@Singleton
public class ProgressReportAndroid implements ProgressReport {
    Activity activity;
    private ProgressDialog progressDialog;

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

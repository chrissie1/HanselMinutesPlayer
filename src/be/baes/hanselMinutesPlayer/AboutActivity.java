package be.baes.hanselMinutesPlayer;

import android.os.Bundle;
import android.widget.Button;
import be.baes.hanselMinutesPlayer.controllers.OnViewGithubInBrowserOnClickListener;
import com.google.inject.Inject;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 5/02/12
 * Time: 14:08
 */
public class AboutActivity extends RoboActivity {
    @InjectView(R.id.github) Button githubButton;
    @Inject OnViewGithubInBrowserOnClickListener onViewGithubInBrowserClickListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        githubButton.setOnClickListener(onViewGithubInBrowserClickListener);
    }


}

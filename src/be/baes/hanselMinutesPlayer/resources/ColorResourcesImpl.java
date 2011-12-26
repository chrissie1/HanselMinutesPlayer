package be.baes.hanselMinutesPlayer.resources;

import android.content.res.Resources;
import be.baes.hanselMinutesPlayer.R;
import com.google.inject.Singleton;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 26/12/11
 * Time: 11:02
 */
@Singleton
public class ColorResourcesImpl implements ColorResources {
    private Resources resources;

    @Override
    public void initialize(Resources resources)
    {
        this.resources = resources;
    }

    @Override
    public int getColorGreen() {
        return resources.getColor(R.color.green);
    }

    @Override
    public int getColorWhite() {
        return resources.getColor(R.color.white);
    }
}
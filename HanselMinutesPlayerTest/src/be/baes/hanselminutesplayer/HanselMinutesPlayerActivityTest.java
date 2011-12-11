package be.baes.hanselminutesplayer;

import android.test.ActivityInstrumentationTestCase2;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class be.baes.hanselminutesplayer.HanselMinutesPlayerActivityTest \
 * be.baes.hanselminutesplayer.tests/android.test.InstrumentationTestRunner
 */
public class HanselMinutesPlayerActivityTest extends ActivityInstrumentationTestCase2<HanselMinutesPlayerActivity> {

    public HanselMinutesPlayerActivityTest() {
        super("be.baes.hanselminutesplayer", HanselMinutesPlayerActivity.class);
    }

}

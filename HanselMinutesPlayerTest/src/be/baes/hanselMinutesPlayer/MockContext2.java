package be.baes.hanselMinutesPlayer;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.test.InstrumentationTestCase;
import android.test.mock.MockContext;

/**
* Created by IntelliJ IDEA.
* User: christiaan
* Date: 19/12/11
* Time: 21:04
*/
public class MockContext2 extends MockContext {

    private InstrumentationTestCase instrumentationTestCase;

    public MockContext2(InstrumentationTestCase instrumentationTestCase) {
        this.instrumentationTestCase = instrumentationTestCase;
    }

    @Override
    public Resources getResources() {
        return instrumentationTestCase.getInstrumentation().getTargetContext().getResources();
    }

    @Override
    public Resources.Theme getTheme() {
        return instrumentationTestCase.getInstrumentation().getTargetContext().getTheme();
    }

    @Override
    public Object getSystemService(String name) {
        if (Context.LAYOUT_INFLATER_SERVICE.equals(name)) {
            return instrumentationTestCase.getInstrumentation().getTargetContext().getSystemService(name);
        }
        return super.getSystemService(name);
    }

    @Override
    public ApplicationInfo getApplicationInfo()
    {
        return new ApplicationInfo();
    }
}

package imeng.tdemos_robolectric.service;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.fakes.RoboSharedPreferences;

import imeng.tdemos_robolectric.BuildConfig;

import static junit.framework.Assert.assertEquals;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class SampleIntentServiceTest {

    @Test
    public void addsDataToSharedPreference() {

        Application application = RuntimeEnvironment.application;
        RoboSharedPreferences preferences = (RoboSharedPreferences) application
                .getSharedPreferences("example", Context.MODE_PRIVATE);

        SampleIntentService registrationService = new SampleIntentService();

        registrationService.onHandleIntent(new Intent());

        assertEquals(preferences.getString("SAMPLE_DATA", ""), "sample data");
    }

}

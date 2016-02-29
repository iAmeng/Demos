package imeng.tdemos_robolectric.activity;

import android.app.Activity;
import android.view.Menu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import imeng.tdemos_robolectric.BuildConfig;
import imeng.tdemos_robolectric.R;

import static org.junit.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest {

  @Test
  public void onCreateShouldInflateTheMenu() {
    Activity activity = Robolectric.setupActivity(MainActivity.class);

    final Menu menu = shadowOf(activity).getOptionsMenu();
    assertEquals(menu.findItem(R.id.item1).getTitle(),"First menu item");
    assertEquals(menu.findItem(R.id.item2).getTitle(),"Second menu item");
  }
}

package imeng.tdemos;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


/**
 * @project: Demos
 * @Author : Administrator
 * @Date : 2016/2/26 13:36
 * @Version:
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class HelloWorldEspressoTest {
    @Rule
    public ActivityTestRule<MainActivity> mMainActivityActivityTestRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void listGoesOVerTheFold() {
        onView(withText("Hello World!")).check(matches(isDisplayed()));
    }

    @Test
    public void btClick() {
        onView(withId(R.id.bt_a)).perform(click());
    }


}

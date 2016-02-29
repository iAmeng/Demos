package imeng.tdemos;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.orhanobut.logger.AndroidLogTool;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * @project: Demos
 * @Author : Administrator
 * @Date : 2016/2/24 15:29
 * @Version:
 */
@RunWith(AndroidJUnit4.class) //没有这个注释, 你是跑步起来的
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity>{
    private static final String TAG = MainActivityTest.class.getSimpleName();

    private MainActivity mMainActivity;
    private TextView mTextView;
    public MainActivityTest() {
        // 所有的ActivityInstrumentationTestCase2子类都需要调用该父类的super(String)构造方法
        super(MainActivity.class);
    }

    @Before
    public void setup() throws Exception {
        super.setUp();
        // @Before注解表示在执行所有的testCase之前要做的事情
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mMainActivity = getActivity();

        if (mMainActivity == null) {
            Logger.t(TAG).e("mMainaty null:" );
        }

        mTextView = (TextView) mMainActivity.findViewById(R.id.content_tv);

        Logger.init(TAG)                 // default PRETTYLOGGER or use just init()
                .methodCount(3)                 // default 2
                .hideThreadInfo()               // default shown
                .logLevel(LogLevel.NONE)        // default LogLevel.FULL
                .methodOffset(2)                // default 0
                .logTool(new AndroidLogTool()); // custom log tool, optional

        Logger.t(TAG).e("fooo tag:" + TAG); //显示在Android monitor.

    }

    @Test
    public void testPreconditions() {
        //@Test 注解表示一个测试用例方法
        assertNotNull("MainActivity is null", mMainActivity);
        assertNotNull("ContentTxt is null", mTextView);
    }

    @Test
    public void testContentDisplayed() {
        //这就是测试目标了
        String content = mTextView.getText().toString();
        assertNotNull("MainActivity content is null", content);
    }

    @Test
    public void btClick() {
        onView(withId(R.id.bt_b)).perform(click());
    }


}

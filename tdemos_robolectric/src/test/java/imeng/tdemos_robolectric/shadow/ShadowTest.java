package imeng.tdemos_robolectric.shadow;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowBitmap;

import imeng.tdemos_robolectric.BuildConfig;
import imeng.tdemos_robolectric.activity.MainActivity;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.TestCase.assertEquals;

@RunWith(CustomShadowTestRunner.class)
@Config(constants = BuildConfig.class,shadows = {ShadowPerson.class})
public class ShadowTest {

    /**
     * 测试自定义的Shadow
     */
    @Test
    public void testCustomShadow(){
        Person person = new Person("genius");
        //getName()实际上调用的是ShadowPerson的方法
        assertEquals("genius", person.getName());

        //获取Person对象对应的Shadow对象
        //ShadowPerson shadowPerson = (ShadowPerson) ShadowExtractor.extract(person);
        //assertEquals("geniusmart", shadowPerson.getName());
    }

    /**
     * 测试框架提供的Shadow类
     */
    @Test
    public void testDefaultShadow(){

        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);

        //通过Shadows.shadowOf()可以获取很多Android对象的Shadow对象
        ShadowActivity shadowActivity = Shadows.shadowOf(mainActivity);
        ShadowApplication shadowApplication = Shadows.shadowOf(RuntimeEnvironment.application);

        Bitmap bitmap = BitmapFactory.decodeFile("Path");
        ShadowBitmap shadowBitmap = Shadows.shadowOf(bitmap);

        //Shadow对象提供方便我们用于模拟业务场景进行测试的api
        assertNull(shadowActivity.getNextStartedActivity());
        assertNull(shadowApplication.getNextStartedActivity());
        assertNotNull(shadowBitmap);

    }

}

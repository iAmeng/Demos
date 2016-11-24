package imeng.pagewidgetdemo;

import android.app.Application;

import imeng.pagewidgetdemo.utils.AppUtils;

/**
 * @author yuyh.
 * @date 2016/8/3.
 */
public class ReaderApplication extends Application {

    private static ReaderApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化 单例
        this.sInstance = this;
        AppUtils.init(this);
    }

    /**
     * 很好的方式。
     * @return
     */
    public static ReaderApplication getsInstance() {
        return sInstance;
    }

}

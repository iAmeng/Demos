package imeng.rxserialdemo.base;

import android.app.Application;

import imeng.rxserialdemo.common.utils.CrashHandler;

/**
 * @Author : Administrator
 * @Date : 2016/7/6 14:23
 * @Version:
 */
public class BaseApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this);
    }
}


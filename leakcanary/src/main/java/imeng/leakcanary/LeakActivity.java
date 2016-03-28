package imeng.leakcanary;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.RefWatcher;

/**
 * @project: Demos
 * @Author : Administrator
 * @Date : 2016/3/11 16:43
 * @Version:
 */
public class LeakActivity extends AppCompatActivity{
    private static final String TAG = "LeakActivity";
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.aty_leak);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = MyApplication.getmRefWatcher(this);
        refWatcher.watch(this);
        Logger.t(TAG).e("onDestroy" + "...");
    }
}

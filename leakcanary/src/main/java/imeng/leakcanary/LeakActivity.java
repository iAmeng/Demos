package imeng.leakcanary;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.RefWatcher;

/**
 * @project: Demos
 * @Author : Administrator
 * @Date : 2016/3/11 16:43
 * @Version:
 */
public class LeakActivity extends AppCompatActivity {
    private static final String TAG = "LeakActivity";

    private static Drawable sBackground; //会导致内存泄露的.

    TextView mTv;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        setContentView(R.layout.aty_leak);
        mHandler.sendEmptyMessage(1); //handler 制造内存泄露.
    }


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

//        TextView label = new TextView(this);
//        label.setTextSize(30);
//        label.setTextColor(0xff123345);
//        label.setText("Leaks are bad");
//        label.setVisibility(View.VISIBLE);
//        ImageView imageview = (ImageView) findViewById(R.id.image);
//        if (sBackground == null) {
//
//            sBackground = imageview.getDrawable();
//        }
//        label.setBackgroundDrawable(sBackground);
//
//        mTv = label;
//        mHandler.sendEmptyMessage(1);
//
//        View rv = getLayoutInflater().inflate(R.layout.aty_leak, null);

//        setContentView(rv);
        setContentView(R.layout.aty_leak);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (TextUtils.isEmpty(TAG)) {
            Logger.e("onDestroy" + "...");
        } else {
            Log.e(TAG, "ondestroy");
        }
        RefWatcher refWatcher = MyApplication.getmRefWatcher(this);
        refWatcher.watch(this);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            for (int i = 0; i < 1000000; i++) {
                Log.e("", "handleMessage :aaaaaaaaaaaaaaa ");
                //mTv.setText("" + i);
            }
        }
    };

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Leak Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://imeng.leakcanary/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Leak Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://imeng.leakcanary/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}

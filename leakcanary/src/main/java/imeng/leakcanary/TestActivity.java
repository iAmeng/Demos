package imeng.leakcanary;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.squareup.leakcanary.RefWatcher;


public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        TextView textView = (TextView) findViewById(R.id.test_text_view);

        TestDataModel.getInstance().setRetainedTextView(textView); //textview 传给静态类 会导致 内存泄露.
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = MyApplication.getmRefWatcher(this);
        refWatcher.watch(this);
    }
}
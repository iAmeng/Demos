package imeng.tdemos_robolectric.activity;

/**
 * @project: Demos
 * @Author : Administrator
 * @Date : 2016/2/29 12:49
 * @Version:
 */

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import imeng.tdemos_robolectric.R;

public class SampleActivity extends AppCompatActivity {

    private TextView lifecycleTextView;
    private CheckBox inverseCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        lifecycleTextView = (TextView) findViewById(R.id.tv_lifecycle_value);
        inverseCheckBox = (CheckBox) findViewById(R.id.checkbox);

        lifecycleTextView.setText(R.string.sample_lifecycle_oncreate);
    }

    public void forward(View view){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    public void showDialog(View view){
        AlertDialog alertDialog = new AlertDialog.Builder(this).setMessage(R.string.sample_dialog_message)
                .setTitle(R.string.sample_dialog_title).create();
        alertDialog.show();
    }

    public void showToast(View view){
        Toast.makeText(this, "we love UT", Toast.LENGTH_LONG).show();
    }

    public void inverse(View view) {
        inverseCheckBox.setChecked(!inverseCheckBox.isChecked());
    }

    public void showDemo(View view){
        forward(view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        lifecycleTextView.setText(R.string.sample_lifecycle_onResume);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lifecycleTextView.setText(R.string.sample_lifecycle_onDestroy);
    }
}

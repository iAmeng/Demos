package imeng.customviews;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.configuration_tv)
    TextView mConfigurationTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        /*
         * get Configuration
         */
        Configuration configuration = getResources().getConfiguration();
        //国家码
        int countryCode = configuration.mcc;
        //通信网络码
        int networkCode = configuration.mnc;
        //横竖屏
        String orationStr = null;
        if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            orationStr = "LANDSCAPE";
        } else if(configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            orationStr= "PORTRAIT";
        } else if(configuration.orientation == Configuration.ORIENTATION_UNDEFINED) {
            orationStr = "UNDEFINED";
        }
        mConfigurationTv.setText("" + "国家-" + countryCode + " " + "网络-" + " " + "屏幕-"+orationStr);





    }
}

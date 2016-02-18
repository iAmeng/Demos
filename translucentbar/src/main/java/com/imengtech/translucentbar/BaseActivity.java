package com.imengtech.translucentbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * 项目名称：Demos 类描述： 创建人：Administrator 创建时间：2016/2/17 9:01 修改人：Administrator 修改时间：2016/2/17 9:01 修改备注：
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    }
}

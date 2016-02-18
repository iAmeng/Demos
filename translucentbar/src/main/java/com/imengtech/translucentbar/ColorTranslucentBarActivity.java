package com.imengtech.translucentbar;

import android.os.Bundle;

public class ColorTranslucentBarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_color_translucent_bar);
        StatusBarCompat.compat(this, 0xFFFF0000);
    }

}

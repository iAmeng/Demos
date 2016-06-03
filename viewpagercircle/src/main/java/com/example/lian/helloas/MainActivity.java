package com.example.lian.helloas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    private String str;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置主界面
        setContentView(R.layout.activity_main);

    }

    public  void click(View v){
        Intent intent = new Intent("com.example.helloas.ACTION_START");
        startActivity(intent);
    }



}

package com.example.mryan.qfshopping.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.mryan.qfshopping.R;

/**
 * 欢迎页面
 * Created by 闫宏博 on 2019/5/6
 */
public class WelcomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcom_activity);


        //实现两秒的页面切换，切换到主页面
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //frome    to
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                //跳转之后关闭本身
                finish();
            }
        },2000);
    }
}

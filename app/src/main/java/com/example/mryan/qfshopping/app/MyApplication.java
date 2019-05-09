package com.example.mryan.qfshopping.app;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/*代表整个APP，优先级极高*/
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initOkHttpUtils();//初始化OKHTTP
    }
    //初始化网络请求配置
    private void initOkHttpUtils() {
        //设置网络请求参数
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1000,TimeUnit.MILLISECONDS)
                .readTimeout(1000,TimeUnit.MILLISECONDS)
                .build();
        //okhttp跟库网络参数发起请求，get/post
        OkHttpUtils.initClient(okHttpClient);
    }
}

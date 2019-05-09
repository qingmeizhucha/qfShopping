package com.example.mryan.qfshopping.type.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.mryan.qfshopping.base.BaseFragment;

import static android.content.ContentValues.TAG;
/**
 * Created by 闫宏博 on 2019/5/7
 */
public class TypeFragment extends BaseFragment {
    TextView textView;
    @Override
    public View initView() {
        Log.e(TAG, "分类视图初始化");
        textView = new TextView(context);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "分类数据初始化");
        textView.setText("分类");
    }
}

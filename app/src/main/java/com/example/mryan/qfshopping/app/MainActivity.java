package com.example.mryan.qfshopping.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.mryan.qfshopping.R;
import com.example.mryan.qfshopping.base.BaseFragment;
import com.example.mryan.qfshopping.community.fragment.*;
import com.example.mryan.qfshopping.home.fragment.HomeFragment;
import com.example.mryan.qfshopping.shoppingcars.fragment.ShoppingFragment;
import com.example.mryan.qfshopping.type.fragment.TypeFragment;
import com.example.mryan.qfshopping.user.fragment.UserFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 闫宏博 on 2019/05/07
 */
public class MainActivity extends FragmentActivity {
    //@Bind(R.id.frame_layout)
    // FrameLayout frameLayout;
    //  @Bind(R.id.rg_main)
    // RadioGroup rgMain;

    // 用于保存五个fragment的实例化对象
    ArrayList<BaseFragment> fragment_list;

    // fragment的的下标位置
    int position = 0;

    // 上次布局的缓存
    Fragment tempFragment;
    @Bind(R.id.frame_layout)
    FrameLayout frameLayout;
    @Bind(R.id.rb_home)
    RadioButton rbHome;
    @Bind(R.id.rb_type)
    RadioButton rbType;
    @Bind(R.id.rb_community)
    RadioButton rbCommunity;
    @Bind(R.id.rb_cars)
    RadioButton rbCars;
    @Bind(R.id.rb_user)
    RadioButton rbUser;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
       //  ButterKnife.bind(this);
        initFragment();
         initListener();

    }

    //设置监听
    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //通过判断点的按钮ID获取里面的fragment
                switch (checkedId) {
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_type:
                        position = 1;
                        break;
                    case R.id.rb_community:
                        position = 2;
                        break;
                    case R.id.rb_cars:
                        position = 3;
                        break;
                    case R.id.rb_user:
                        position = 4;
                        break;
                }
                BaseFragment fragment = getFragment(position);
                switchFragment(tempFragment, fragment);
            }


        });
        rgMain.check(R.id.rb_home);
    }

    //初始化fragment
    public void initFragment() {
        fragment_list = new ArrayList<>();
        fragment_list.add(new HomeFragment());
        fragment_list.add(new TypeFragment());
        fragment_list.add(new CommunityFragment());
        fragment_list.add(new ShoppingFragment());
        fragment_list.add(new UserFragment());


    }

    private BaseFragment getFragment(int position) {
        if (fragment_list != null && fragment_list.size() > 0) {
            //通过下表获取fragment对象
            BaseFragment baseFragment = fragment_list.get(position);
            return baseFragment;
        }
        return null;
    }
    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {

        // 判断当前缓存的fragment是否等与下一个
        if (tempFragment != nextFragment) {
            // 在不相等的情况进入，重新缓存fragment
            tempFragment = nextFragment;
            if (nextFragment != null) { // 判断是否为空
                // 实例化碎片管理类：负责隐藏显示等碎片操作
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                // 判断是否已经加载
                if (!nextFragment.isAdded()) {
                    // 隐藏上一级Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    // 在把下一级fragment加载到页面显示
                    transaction.add(R.id.frame_layout, nextFragment).commit();
                } else {
                    // 隐藏上一级Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    // 直接显示
                    transaction.show(nextFragment).commit();
                }
            }
        }

    }

    @OnClick({R.id.frame_layout, R.id.rb_home, R.id.rb_type, R.id.rb_community, R.id.rb_cars, R.id.rb_user, R.id.rg_main})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.frame_layout:
                break;
            case R.id.rb_home:
                break;
            case R.id.rb_type:
                break;
            case R.id.rb_community:
                break;
            case R.id.rb_cars:
                break;
            case R.id.rb_user:
                break;
            case R.id.rg_main:
                break;
        }
    }
}
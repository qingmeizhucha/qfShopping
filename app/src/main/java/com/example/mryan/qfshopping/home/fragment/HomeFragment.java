package com.example.mryan.qfshopping.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.mryan.qfshopping.R;
import com.example.mryan.qfshopping.base.BaseFragment;
import com.example.mryan.qfshopping.home.Adapter.HomeRecycleAdapter;
import com.example.mryan.qfshopping.home.bean.ResultBeanData;
import com.example.mryan.qfshopping.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by 闫宏博 on 2019/5/7
 */
public class HomeFragment extends BaseFragment {
    private static final String TAG =
            HomeFragment.class.getSimpleName();
    TextView tvSearchHome;
    TextView tvMessageHome;
    RecyclerView rvHome;
    ImageButton ibTop;
    LinearLayoutManager mLayoutManager;

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "主页面数据初始化");
        getDataFromNet();

    }

    public void getDataFromNet() {

        /*
        获取网络数据（get请求）
         */
        OkHttpUtils.get().url(Constants.HOME_URL).id(100).build().execute(new StringCallback() {
            @Override//网络请求错误回调
            public void onError(Call call, Exception e, int id) {
              //  Log.e(TAG,"onError"+e.getMessage());
            }

            @Override//网络请求成功回调
            public void onResponse(String response, int id) {
               // Log.e(TAG,"onss+++++++++++"+response);
               parseDara(response);
            }
        });
    }
    //解析json数据
    private void parseDara(String response){
        ResultBeanData resultBeanData = JSON.parseObject(response,ResultBeanData.class);
        ResultBeanData.ResultBean result = resultBeanData.getResult();
      //  System.out.println(result.getAct_info().get(0).getName());
        System.out.println("+++++++++++++++" + result);
        //创建适配器，传入适配器
        //rvHome.setAdapter(new HomeRecycleAdapter(getContext(),result));
        mLayoutManager = new LinearLayoutManager(getContext());
        rvHome.setLayoutManager(mLayoutManager);
        rvHome.setItemAnimator(new DefaultItemAnimator());
        rvHome.setAdapter(new HomeRecycleAdapter(context,result));

    }
    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_home, null);
        rvHome = view.findViewById(R.id.rv_home);
        ibTop = view.findViewById(R.id.ib_top);
        tvSearchHome = view.findViewById(R.id.tv_search_home);
        tvMessageHome = view.findViewById(R.id.tv_message_home);
        initListener();
        return view;
    }

    private void initListener() {
        //置顶的监听
        ibTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvHome.scrollToPosition(0);
            }
        });

        //搜索的监听
        tvSearchHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"搜索",Toast.LENGTH_SHORT).show();
                System.out.println("dianjile");
            }
        });

        //消息的监听
        tvMessageHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"进入消息中心",Toast.LENGTH_SHORT).show();
            }
        });
    }

}

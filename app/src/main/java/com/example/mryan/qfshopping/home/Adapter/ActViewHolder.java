package com.example.mryan.qfshopping.home.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.module.AppGlideModule;
import com.example.mryan.qfshopping.R;
import com.example.mryan.qfshopping.home.bean.ResultBeanData;
import com.example.mryan.qfshopping.utils.Constants;

import java.util.List;

class ActViewHolder extends RecyclerView.ViewHolder{
    public ViewPager viewPager;
    public Context mContext;
    public ActViewHolder(View inflate, Context mContext) {
        super(inflate);
        this.mContext = mContext;
        viewPager = inflate.findViewById(R.id.act_viewpager);
    }
    public void setData(final List<ResultBeanData.ResultBean.ActInfoBean> act_info) {

        System.out.println("qqqqqqqqqqqqqqqq" + act_info.size());
        //设置每个页面的间距
        viewPager.setPageMargin(20);
        //>=3
        viewPager.setOffscreenPageLimit(3);
        /*
        //设置动画
        viewPager.setPageTransformer(true,
                new AlphaPageTransformer(new ScaleInTransformer()));*/
        viewPager.setAdapter(new PagerAdapter() {
            //返回长度
            @Override
            public int getCount() {
                return act_info.size();
            }
            //判断当前的view是否等于obj
            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == o;
            }
            //设置组件和数据
            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {


                ImageView imageView = new ImageView(mContext);
                //拉伸图
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(mContext)
                        .load(Constants.Base_URl_IMAGE+act_info.get(position).getIcon_url())
                        .into(imageView);
                //吧ImageView添加到父容器
                container.addView(imageView);
                return imageView;
            }
            //结束时调用，
            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
               container.removeView((View)object);
            }

        });

        //设置viewpager的点击事件
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override//当页面出于选中就会回调该函数
            public void onPageSelected(int i) {
                Toast.makeText(mContext,"position" + i,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}

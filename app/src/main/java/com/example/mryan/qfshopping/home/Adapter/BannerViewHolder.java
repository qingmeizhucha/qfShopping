package com.example.mryan.qfshopping.home.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mryan.qfshopping.R;
import com.example.mryan.qfshopping.home.bean.ResultBeanData;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.mryan.qfshopping.utils.Constants.Base_URl_IMAGE;

/*
    设置适配器
     */
public class BannerViewHolder extends RecyclerView.ViewHolder implements OnBannerListener {
    public Banner banner;
    public Context mContext;
    public ResultBeanData.ResultBean resultBean;
    public BannerViewHolder(View itemView, Context mContext, ResultBeanData.ResultBean resultBean) {
        super(itemView);
        banner = itemView.findViewById(R.id.banner);
        this.mContext = mContext;
        this.resultBean = resultBean;
    }
    public void setData(final List<ResultBeanData.ResultBean.BannerInfoBean> banner_info) throws IOException {
        setBannerData(banner_info);
    }

    private void setBannerData(List<ResultBeanData.ResultBean.BannerInfoBean> banner_info) throws IOException {
        //设置循环指标点
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        ArrayList<String> imageUris = new ArrayList<>();
        //  ArrayList<ImageView> imageViews = new ArrayList<ImageView>();
        ArrayList<String> imageTitle = new ArrayList<>();
        //如果你想用自己项目的图片加载,那么----->自定义图片加载框架 List<String> imageUris = new ArrayList<>();
        for(int i=0; i<resultBean.getBanner_info().size(); i++){
            imageUris.add(Base_URl_IMAGE+resultBean.getBanner_info().get(i).getImage());
            imageTitle.add("第" + (i+1) + "张图");
        }
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setImageLoader(new MyLoader());
        banner.setBannerTitles(imageTitle);
        banner.setBannerAnimation(Transformer.Default);
        banner.setDelayTime(3000);
        banner.isAutoPlay(true);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImages(imageUris).setOnBannerListener(this).start();


    }

    @Override
    public void OnBannerClick(int position) {
        System.out.println("你点了第" + (position + 1) + "张轮播图");
    }
    private class MyLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load((String) path)
                    .into(imageView);
        }
    }
}
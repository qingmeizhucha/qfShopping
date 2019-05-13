package com.example.mryan.qfshopping.home.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.mryan.qfshopping.R;
import com.example.mryan.qfshopping.home.bean.ResultBeanData;


import java.io.IOException;
import java.util.ArrayList;


public class HomeRecycleAdapter extends RecyclerView.Adapter {
    /**
     * 五种类型
     * 横幅广告
     */
    public static final int BANNER = 0;//横幅
    public static final int CHANNEL = 1;//频道
    public static final int ACT = 2;//活动
    public static final int SECKILL = 3;//秒杀
    public static final int RECOMMEND = 4;//推荐
    public static final int HOT = 5;//热卖

    public int currentType = BANNER;//当前类型
    /**
     * 数据对象
     */
    private ResultBeanData.ResultBean resultBean;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //==getView获取定制的xml布局设置RecyclerView，可以获取布局
        if (viewType == BANNER) {
            return new BannerViewHolder(mLayoutInflater.inflate(R.layout.banner_viewpager, null)
                    , mContext, resultBean);
        } else if (viewType == CHANNEL) {
            return new ChannerViewHolder(mLayoutInflater.inflate(R.layout.channel_viewpager, null)
                    , mContext);
        } else if (viewType == ACT) {
            return new ActViewHolder(mLayoutInflater.inflate(R.layout.act_item, null), mContext);
        }/*else if (viewType == SECKILL) {
            return new
                    SeckillViewHolder(mLayoutInflater.inflate(R.layout.seckill_item, null),
                    mContext);
        } else if (viewType == RECOMMEND) { return new
                RecommendViewHolder(mLayoutInflater.inflate(R.layout.recommend_item, null), mContext);
        } else if (viewType == HOT) {
            return new
                    HotViewHolder(mLayoutInflater.inflate(R.layout.hot_item, null),
                    mContext);
        }*/
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //绑定ViewHolder视图的数据
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            try {
                bannerViewHolder.setData(resultBean.getBanner_info());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (getItemViewType(position) == CHANNEL) {
            ChannerViewHolder channelViewHolder = (ChannerViewHolder) holder;
            channelViewHolder.setData((ArrayList<ResultBeanData.ResultBean.ChannelInfoBean>) resultBean.getChannel_info());
        } else if (getItemViewType(position) == ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) holder;
           actViewHolder.setData(resultBean.getAct_info());
        }/* else if (getItemViewType(position) == SECKILL) {
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
            seckillViewHolder.setData(resultBean.getSeckill_info());
        } else if (getItemViewType(position) == RECOMMEND) {
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData(resultBean.getRecommend_info());
        } else if (getItemViewType(position) == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData(resultBean.getHot_info());
        }*/

    }

    public int getItemCount() {
        //决定当前容器RecyclerView存在几个item
        return 3;
    }

    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
        }
        return currentType;
    }

    public HomeRecycleAdapter(Context mContext, ResultBeanData.ResultBean resultBean) {
        this.mContext = mContext;
        this.resultBean = resultBean;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    class ChannerViewHolder extends RecyclerView.ViewHolder {
        public GridView gvChannel;
        public Context mContext;

        public ChannerViewHolder(@NonNull View itemView, Context mContext) {
            super(itemView);
            gvChannel = itemView.findViewById(R.id.gv_channel);
            this.mContext = mContext;
        }

        public void setData(ArrayList<ResultBeanData.ResultBean.ChannelInfoBean> channelInfoBeans) {
            gvChannel.setAdapter(new ChannelAdapter(mContext, channelInfoBeans));
            //设置点击事件
            gvChannel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //判断不能超时频道的范围，跳转活动
                    if(position<10){
                        /*Intent intent = new Intent(mContext,class);
                        intent.putExtra("posistion");
                        mContext.startActivities(intent);*/

                    }
                    Toast.makeText(mContext,"position" + position,Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    /*class ActViewHolder extends RecyclerView.ViewHolder{

    }*/
}

package com.example.mryan.qfshopping.home.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mryan.qfshopping.R;
import com.example.mryan.qfshopping.home.bean.ResultBeanData;
import com.example.mryan.qfshopping.utils.Constants;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChannelAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<ResultBeanData.ResultBean.ChannelInfoBean> channel_info;

    public ChannelAdapter(Context mContext, ArrayList<ResultBeanData.ResultBean.ChannelInfoBean> channelInfoBeans) {
        this.mContext = mContext;
        this.channel_info = channelInfoBeans;

    }

    @Override
    public int getCount() {
        return channel_info == null ? 0 : channel_info.size();
    }

    @Override
    public Object getItem(int position) {
        return channel_info.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_channel, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        ResultBeanData.ResultBean.ChannelInfoBean channelInfoBean = channel_info.get(position);

        holder.tvChannel.setText(channelInfoBean.getChannel_name());
        Glide.with(mContext).load(Constants.Base_URl_IMAGE + channelInfoBean.getImage())
                .into(holder.ivChannel);

        return convertView;
    }
    class ViewHolder {
        @Bind(R.id.iv_channel)
        ImageView ivChannel;
        @Bind(R.id.tv_channel)
        TextView tvChannel;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

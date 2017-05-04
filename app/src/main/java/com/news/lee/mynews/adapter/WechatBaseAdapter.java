package com.news.lee.mynews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.news.lee.mynews.R;
import com.news.lee.mynews.Utils.DateUtils;
import com.news.lee.mynews.bean.Weixinbean;
import com.news.lee.mynews.bean.ZhihuStories;
import com.news.lee.mynews.imageloader.ImageLoaderManager;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by lee on 17-4-19.
 */

public class WechatBaseAdapter extends BaseAdapter {

    private String currentDate;
    private Context mContext;
    private ArrayList<Weixinbean> newslist=new ArrayList<>();
    private LayoutInflater mInflater;
    private ImageLoaderManager imageLoaderManager;

    public WechatBaseAdapter(Context mContext) {
        this.mContext = mContext;
        imageLoaderManager=ImageLoaderManager.getInstance(mContext);
        mInflater=LayoutInflater.from(mContext);

    }

    public void addData(ArrayList<Weixinbean> newslist){
        for (int i=0;i<newslist.size();i++){
            this.newslist.add(newslist.get(i));
        }

    }

    public void setData(ArrayList<Weixinbean> newslist){
        this.newslist=newslist;
    }

    @Override
    public int getCount() {
        return newslist.size();
    }

    @Override
    public Object getItem(int position) {
        return newslist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Weixinbean weixinbean=newslist.get(position);
        ViewHolder holder;
        if (convertView==null){
            holder=new ViewHolder();
            convertView=mInflater.inflate(R.layout.wechat_list_item,null);

            holder.wechat_image= (ImageView) convertView.findViewById(R.id.wechat_image);
            holder.wechat_title= (TextView) convertView.findViewById(R.id.wechat_title);
            holder.wechat_ctime= (TextView) convertView.findViewById(R.id.wechat_ctime);
            holder.wechat_desc= (TextView) convertView.findViewById(R.id.wechat_desc);

            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }


        imageLoaderManager.displayImage(holder.wechat_image,weixinbean.getPicUrl());
        holder.wechat_desc.setText(weixinbean.getDescription());
        holder.wechat_title.setText(weixinbean.getTitle());
        Date date=DateUtils.stringtoDate(weixinbean.getCtime(),DateUtils.LONG_DATE_FORMAT__);
        holder.wechat_ctime.setText(DateUtils.dateToString(date,DateUtils.LONG_DATE_FORMAT));

        return convertView;
    }

    static class ViewHolder{
        ImageView wechat_image;
        TextView wechat_title;
        TextView wechat_ctime;
        TextView wechat_desc;
    }
}

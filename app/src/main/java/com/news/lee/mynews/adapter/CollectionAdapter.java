package com.news.lee.mynews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.news.lee.mynews.Favorites;
import com.news.lee.mynews.R;

import com.news.lee.mynews.imageloader.ImageLoaderManager;
import com.news.lee.mynews.viewholder.WechatViewHolder;
import com.news.lee.mynews.viewholder.ZhihuViewHolder;

import java.util.List;

/**
 * Created by lee on 17-4-26.
 */

public class CollectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {


    private static final int ZHIHU_TYPE=1;
    private static final int WECHAT_TYPE=2;

    private List<Favorites> data;
    private ImageLoaderManager imageLoaderManager;
    private LayoutInflater mInflate;
    private Context mContext;

    private OnItemClickListener itemClickListener;


    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }



    public static interface OnItemClickListener {

        void onClick(View view, int position);
    }


    public CollectionAdapter(Context mContext) {
        this.mContext = mContext;
        mInflate=LayoutInflater.from(mContext);
        imageLoaderManager=ImageLoaderManager.getInstance(mContext);
    }

    public void setData(List<Favorites> data){

        this.data=data;

    }


    @Override
    public int getItemViewType(int position) {
        Log.e("info",data.get(position).getType()+"");
        return data.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType){
            case ZHIHU_TYPE:
                View view=mInflate.inflate(R.layout.list_item_view,parent,false);
                view.setOnClickListener(this);
                return new ZhihuViewHolder(view);
            case WECHAT_TYPE:
                View view_wechat=mInflate.inflate(R.layout.wechat_list_item,parent,false);
                view_wechat.setOnClickListener(this);
                return new WechatViewHolder(view_wechat);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType=getItemViewType(position);
        holder.itemView.setTag(position);
        switch (viewType){
            case ZHIHU_TYPE:
                ((ZhihuViewHolder)holder).bindHolder(data.get(position),imageLoaderManager);
                break;
            case WECHAT_TYPE:
                ((WechatViewHolder)holder).bindHolder(data.get(position),imageLoaderManager);
                break;
        }



    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    @Override
    public void onClick(View v) {

        if (itemClickListener!=null){
            itemClickListener.onClick(v, (int) v.getTag());
        }

    }

}

package com.news.lee.mynews.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.news.lee.mynews.Favorites;
import com.news.lee.mynews.R;
import com.news.lee.mynews.imageloader.ImageLoaderManager;

/**
 * Created by lee on 17-4-26.
 */

public class WechatViewHolder extends RecyclerView.ViewHolder{

    private ImageView wechat_image;
    private TextView wechat_title;
    private TextView wechat_ctime;
    private TextView wechat_desc;

    private AdapterView.OnItemClickListener itemClickListener;


    public void setItemClickListener(AdapterView.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public WechatViewHolder(View itemView) {
        super(itemView);


        wechat_image= (ImageView) itemView.findViewById(R.id.wechat_image);
        wechat_title= (TextView) itemView.findViewById(R.id.wechat_title);
        wechat_ctime= (TextView) itemView.findViewById(R.id.wechat_ctime);
        wechat_desc= (TextView) itemView.findViewById(R.id.wechat_desc);
    }

    public void bindHolder(Favorites favorites, ImageLoaderManager imageLoaderManager){
        wechat_ctime.setText(favorites.getCtime());
        wechat_title.setText(favorites.getTitle());
        wechat_desc.setText(favorites.getDescription());
        imageLoaderManager.displayImage(wechat_image,favorites.getImage());

    }

}

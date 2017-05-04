package com.news.lee.mynews.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.news.lee.mynews.Favorites;
import com.news.lee.mynews.R;
import com.news.lee.mynews.imageloader.ImageLoaderManager;

/**
 * Created by lee on 17-4-26.
 */

public class ZhihuViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView title;

    public ZhihuViewHolder(View itemView) {
        super(itemView);

        imageView= (ImageView) itemView.findViewById(R.id.image);
        title= (TextView) itemView.findViewById(R.id.title_zhihu);
    }

    public void bindHolder(Favorites favorites, ImageLoaderManager imageLoaderManager){

        title.setText(favorites.getTitle());
        imageLoaderManager.displayImage(imageView,favorites.getImage());

    }
}

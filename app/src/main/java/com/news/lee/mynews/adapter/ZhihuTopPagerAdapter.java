package com.news.lee.mynews.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.news.lee.mynews.R;
import com.news.lee.mynews.bean.ZhihuHeader;
import com.news.lee.mynews.imageloader.ImageLoaderManager;

import java.util.ArrayList;

/**
 * Created by lee on 17-3-29.
 */

public class ZhihuTopPagerAdapter extends PagerAdapter {
    private ArrayList<ZhihuHeader> top_stories;
    private Context mContext;
    private LayoutInflater mInflater;
    private ImageLoaderManager imageLoaderManager;

    public ZhihuTopPagerAdapter(Context mContext, ArrayList<ZhihuHeader> top_stories) {
        this.top_stories = top_stories;
        this.mContext=mContext;
        mInflater=LayoutInflater.from(mContext);
        imageLoaderManager=ImageLoaderManager.getInstance(mContext);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ZhihuHeader zhihuHeader=top_stories.get(position);

        View root=mInflater.inflate(R.layout.toppager_item,null);
        ImageView top_image= (ImageView) root.findViewById(R.id.top_image);
        TextView top_title= (TextView) root.findViewById(R.id.top_title);

        top_title.setText(zhihuHeader.getTitle());

        imageLoaderManager.displayImage(top_image,zhihuHeader.getImages());

        container.addView(root,0);
        return container;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }



    @Override
    public int getCount() {
        return top_stories.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}

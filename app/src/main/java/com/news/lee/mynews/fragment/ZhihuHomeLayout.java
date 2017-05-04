package com.news.lee.mynews.fragment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;

import com.news.lee.mynews.R;
import com.news.lee.mynews.adapter.ZhihuTopPagerAdapter;
import com.news.lee.mynews.bean.ZhihuBody;
import com.news.lee.mynews.imageloader.ImageLoaderManager;

import java.util.ArrayList;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

/**
 * Created by lee on 17-4-2.
 */

public class ZhihuHomeLayout extends RelativeLayout {

    public AutoScrollViewPager scrollViewPager;
    public ArrayList<ZhihuBody> top_stories;
    public Context mContext;
    public LayoutInflater mInflater;
    public ZhihuTopPagerAdapter pagerAdapter;


    public ZhihuHomeLayout(Context context, AttributeSet attrs,ArrayList<ZhihuBody> top_stories) {
        super(context, attrs);
        mContext=context;
        mInflater=LayoutInflater.from(mContext);
        this.top_stories=top_stories;
        initView();
    }

    public ZhihuHomeLayout(Context context,ArrayList<ZhihuBody> top_stories) {
        this(context,null,top_stories);
    }


    private void initView(){

        View root=mInflater.inflate(R.layout.zhihu_top_view,this);
        scrollViewPager= (AutoScrollViewPager)root.findViewById(R.id.autopager);
        pagerAdapter=new ZhihuTopPagerAdapter(mContext);
        pagerAdapter.setData(top_stories);

        scrollViewPager.setAdapter(pagerAdapter);
        scrollViewPager.startAutoScroll(2000);




    }

}

package com.news.lee.mynews.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.news.lee.mynews.R;
import com.news.lee.mynews.activity.DetailActivity;
import com.news.lee.mynews.bean.ZhihuBody;
import com.news.lee.mynews.imageloader.ImageLoaderManager;

import java.util.ArrayList;

/**
 * Created by lee on 17-3-29.
 */

public class ZhihuTopPagerAdapter extends PagerAdapter {
    private ArrayList<ZhihuBody> top_stories;
    private Context mContext;
    private LayoutInflater mInflater;
    private ImageLoaderManager imageLoaderManager;
    private static final String TAG="ZHIHU";

    public ZhihuTopPagerAdapter(Context mContext){
        this.mContext=mContext;
    }

//    public void addData(ArrayList<Zhihubean> top_stories){
//        for (int i=0;i<top_stories.size();i++){
//            this.top_stories.add(top_stories.get(i));
//        }
//
//    }
//
    public void setData(ArrayList<ZhihuBody> top_stories){
        this.top_stories=top_stories;
    }
//
//
//    public void clearData(){
//        this.top_stories.clear();
//    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {


        mInflater=LayoutInflater.from(mContext);
        imageLoaderManager=ImageLoaderManager.getInstance(mContext);
        final ZhihuBody zhihubean=top_stories.get(position);

        View root=mInflater.inflate(R.layout.toppager_item,null);
        ImageView top_image= (ImageView) root.findViewById(R.id.top_image);
        TextView top_title= (TextView) root.findViewById(R.id.top_title);

        top_title.setText(zhihubean.getTitle());


        top_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(mContext, DetailActivity.class);
                intent.putExtra("type",TAG);
                intent.putExtra("title",zhihubean.getTitle());
                intent.putExtra("url",zhihubean.getId()+"");
                mContext.startActivity(intent);
            }
        });

        top_image.setAlpha(230);
        imageLoaderManager.displayImage(top_image,zhihubean.getImages());

        container.addView(root,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        return root;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }



    @Override
    public int getCount() {
        return top_stories==null?0:top_stories.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }


}

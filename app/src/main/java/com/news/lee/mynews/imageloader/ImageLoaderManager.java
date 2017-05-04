package com.news.lee.mynews.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.news.lee.mynews.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by lee on 17-3-29.
 */

public class ImageLoaderManager {

    private static final int THREAD_COUNT=4;//加载图片最大启用4条线程
    private static final int PROPRITY=2;//图片加载的优先级
    private static final int DISK_CACHE_SIZE=50*1024*50;//最多缓存的图片数。
    private static final int CONNECTION_TIME_OUT=5*1000;//超时时间
    private static final int READ_TIME_OUT=30*1000;//读取的超时时间。


    private static ImageLoader imageLoader=null;
    private static ImageLoaderManager mInstance=null;



    public static ImageLoaderManager getInstance(Context context){

        if (mInstance==null){
            synchronized (ImageLoaderManager.class){
                if (mInstance==null){
                    mInstance=new ImageLoaderManager(context);
                }
            }
        }

        return mInstance;
    }


    public ImageLoaderManager(Context context) {

        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(context)
                .threadPoolSize(THREAD_COUNT)
                .threadPriority(Thread.NORM_PRIORITY-PROPRITY)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new WeakMemoryCache())
                .diskCacheSize(DISK_CACHE_SIZE)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .defaultDisplayImageOptions(getDefaultOptions())
                .imageDownloader(new BaseImageDownloader(context,CONNECTION_TIME_OUT,READ_TIME_OUT))
                .writeDebugLogs()
                .build();

        ImageLoader.getInstance().init(configuration);
        imageLoader=ImageLoader.getInstance();
    }

    private DisplayImageOptions getDefaultOptions() {

        DisplayImageOptions options=new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .decodingOptions(new BitmapFactory.Options())
                .build();

        return options;
    }



    public void displayImage(ImageView imageview, String url, DisplayImageOptions options, ImageLoadingListener mListener){

        if (imageLoader!=null){
            imageLoader.displayImage(url,imageview,options,mListener);
        }
    }


    public void displayImage(ImageView imageview,String url,ImageLoadingListener mListener){
        if (imageLoader!=null){
            displayImage(imageview,url,null,mListener);
        }
    }

    public void displayImage(ImageView imageview,String url){
        if (mInstance!=null){
            displayImage(imageview,url,null,null);
        }
    }


}


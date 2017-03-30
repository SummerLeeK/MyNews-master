package com.news.lee.mynews.HTTP;

/**
 * Created by lee on 17-3-27.
 */

public class DisposeDataHandle {

    public DisposeDataListener mListener = null;
    public Class<?> mClasss =null;

    public DisposeDataHandle(DisposeDataListener mListener){ this.mListener=mListener;}

    public DisposeDataHandle(DisposeDataListener mListener,Class<?> mClasss){
        this.mClasss=mClasss;
        this.mListener=mListener;
    }
}

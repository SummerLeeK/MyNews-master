package com.news.lee.mynews.HTTP;

/**
 * Created by lee on 17-3-27.
 */

public class DisposeDataHandle {

    public DisposeDataListener mListener = null;
    public Class<?> mClass =null;

    public DisposeDataHandle(DisposeDataListener mListener){ this.mListener=mListener;}

    public DisposeDataHandle(DisposeDataListener mListener,Class<?> mClass){
        this.mClass=mClass;
        this.mListener=mListener;
    }
}

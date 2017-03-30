package com.news.lee.mynews.HTTP;

/**
 * Created by lee on 17-3-27.
 */

public class OkhttpException {

    private static final long serialVersionUID=1L;

    private int ecode;

    private Object emsg;

    public OkhttpException(int ecode,Object emsg){
        this.ecode=ecode;
        this.emsg=emsg;
    }
}

package com.news.lee.mynews.HTTP;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lee on 17-3-28.
 */

public class RequestParams {

    public ConcurrentHashMap<String,String> urlParams=new ConcurrentHashMap<>();
    public ConcurrentHashMap<String,Object> fileParams=new ConcurrentHashMap<>();

    public RequestParams() {
        this((Map<String,String>) null);
    }

    public RequestParams(Map<String, String> source) {

        if (source==null){
            for (Map.Entry<String,String> entry:source.entrySet()){
                put(entry.getKey(),entry.getValue());
            }
        }
    }


    public RequestParams(final String key,final String value){
        this(new HashMap<String, String>(){
            {
                put(key,value);
            }
        });
    }

    private void put(String key, String value) {

        if (key!=null&&value!=null){
            urlParams.put(key, value);
        }
    }

    public void put(String key,Object value) throws FileNotFoundException{
        if (key!=null){
            fileParams.put(key, value);
        }
    }


    public boolean hasParams(){
        if (urlParams.size()>0&&fileParams.size()>0){
            return true;
        }

        return false;
    }
}

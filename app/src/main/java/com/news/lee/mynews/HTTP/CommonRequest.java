package com.news.lee.mynews.HTTP;

import android.util.Log;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.Request;

/**
 * Created by lee on 17-3-28.
 */

public class CommonRequest {


    public static Request createPostRequest(String url,RequestParams params){
        FormBody.Builder mFormBodyBuilder=new FormBody.Builder();

        if (params!=null) {
            for (Map.Entry<String,String> entry :params.urlParams.entrySet()){
                mFormBodyBuilder.add(entry.getKey(),entry.getValue());

            }
        }
        FormBody formBody=mFormBodyBuilder.build();
        return new Request.Builder().url(url).post(formBody).build();

    }

    public static Request createGetRequest(String url,RequestParams params){
      StringBuilder urlBuilder=new StringBuilder(url);
        if (params!=null){
            urlBuilder=urlBuilder.append("?");
            for (Map.Entry<String,String> entry:params.urlParams.entrySet()){
                urlBuilder.append(entry.getKey()).append("=").
                        append(entry.getValue()).append("&");
            }
        }


        Log.e("info",urlBuilder.substring(0,urlBuilder.length()));
        return new Request.Builder().url(urlBuilder.substring(0,urlBuilder.length())).get().build();
    }
}

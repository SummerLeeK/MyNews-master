package com.news.lee.mynews.HTTP;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by lee on 17-3-26.
 */

public class OkhttpManager {
    private Request request;
    private static int TIME_OUT=30;
    private static OkHttpClient mOkhttpClient;

    static {
        OkHttpClient.Builder okhttpBuilder=new OkHttpClient.Builder();
        okhttpBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okhttpBuilder.readTimeout(TIME_OUT,TimeUnit.SECONDS);
        okhttpBuilder.writeTimeout(TIME_OUT,TimeUnit.SECONDS);

        okhttpBuilder.followRedirects(true);


        //https支持
        okhttpBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

        okhttpBuilder.sslSocketFactory(HttpsUtils.initSSLSocketFactory());
        mOkhttpClient=okhttpBuilder.build();

    }



    public static Call sendRequest(Request request,CommonJsonCallback commonJsonCallback){
        Call call=mOkhttpClient.newCall(request);
        call.enqueue(commonJsonCallback);

        return call;
    }


    public static Call get(Request request,DisposeDataHandle handle){
        Call call=mOkhttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));

        return call;
    }
}

package com.news.lee.mynews.HTTP;

import android.os.Handler;
import android.os.Looper;

import com.news.lee.mynews.bean.BaseZhihuBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by lee on 17-3-27.
 */

public class CommonJsonCallback implements Callback {

    protected final String DATE = "date";
    protected final int RESULT_CODE_VALUE = 0;
    protected final String ERROR_MSG = "emsg";
    protected final String EMPTY_MSG = "";

    protected final int NETWORK_ERROR = -1;
    protected final int JSON_ERROR = -2;
    private final int OTHER_ERROR = -3;

    private Handler mDeliverHandler;
    private DisposeDataListener mListener;

    private Class<?> mClass;

    public CommonJsonCallback(DisposeDataHandle mHandler) {
        this.mListener = mHandler.mListener;
        this.mClass = mHandler.mClass;
        this.mDeliverHandler = new Handler(Looper.getMainLooper());

    }

    @Override
    public void onFailure(Call call, final IOException e) {

        mDeliverHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(new OkhttpException(NETWORK_ERROR, e));
            }
        });

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {

        final String result = response.body().string();

        mDeliverHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(result);
            }
        });
    }

    private void handleResponse(String result) {

        if (result == null && result.toString().trim().equals("")) {
            mListener.onFailure(new OkhttpException(NETWORK_ERROR, EMPTY_MSG));

            return;
        } else {

            try {
                JSONObject jsonObject = new JSONObject(result.toString());

                Object object = ResponseEntityToModule.parseJsonObjectToModule(jsonObject, mClass);
                mListener.onSuccess(object);

            } catch (JSONException e) {
                mListener.onFailure(new OkhttpException(OTHER_ERROR, e.getMessage()));
                e.printStackTrace();
            }
        }
    }
}
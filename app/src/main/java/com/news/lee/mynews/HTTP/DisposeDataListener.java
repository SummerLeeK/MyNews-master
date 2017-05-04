package com.news.lee.mynews.HTTP;

import org.json.JSONException;

/**
 * Created by lee on 17-3-27.
 */

public interface DisposeDataListener {

    public void onSuccess(Object responseObj) throws JSONException;

    public void onFailure(Object reasonObj);
}

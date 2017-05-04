package com.news.lee.mynews.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by lee on 17-4-2.
 */

public abstract class BaseActivity extends AppCompatActivity{

    public String TAG;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        TAG=getComponentName().getClassName();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

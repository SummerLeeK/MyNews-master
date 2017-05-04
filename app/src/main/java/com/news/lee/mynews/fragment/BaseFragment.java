package com.news.lee.mynews.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;


/**
 * Created by lee on 17-4-2.
 */

public abstract class BaseFragment extends Fragment {

    private Activity mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}

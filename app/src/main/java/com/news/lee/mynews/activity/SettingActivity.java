package com.news.lee.mynews.activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.news.lee.mynews.R;
import com.news.lee.mynews.fragment.BaseFragment;
import com.news.lee.mynews.fragment.Settingfragment;

import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;

/**
 * Created by lee on 17-5-4.
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private Settingfragment settingfragment;

    private android.app.FragmentTransaction transaction;
    private android.app.FragmentManager fragmentManager;

    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);

        toolbar= (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.setting);
        toolbar.setNavigationOnClickListener(this);




        settingfragment=new Settingfragment();

        fragmentManager=getFragmentManager();

        transaction=fragmentManager.beginTransaction();

        transaction.replace(R.id.setting_frame,settingfragment);

        transaction.commit();

    }


    @Override
    public void onClick(View v) {

        onBackPressed();
    }

    @Override
    public void onBackPressed() {

        finish();
    }
}

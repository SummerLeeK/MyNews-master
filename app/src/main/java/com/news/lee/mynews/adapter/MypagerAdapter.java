package com.news.lee.mynews.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by u on 2017/3/17.
 */

public class MypagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments=new ArrayList<>();
    private List<String> title_fragment=new ArrayList<>();

    public MypagerAdapter(FragmentManager fm,List<Fragment> fragments,List<String> TITLE_FRAGMENT) {
        super(fm);
        this.fragments=fragments;
        title_fragment=TITLE_FRAGMENT;
    }


    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return fragments.get(position);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return title_fragment.get(position%title_fragment.size());
    }



    @Override
    public int getCount() {
        return fragments.size();
    }
}

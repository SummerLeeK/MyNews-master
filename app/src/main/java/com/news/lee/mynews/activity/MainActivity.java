package com.news.lee.mynews.activity;




import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.news.lee.mynews.HTTP.CommonJsonCallback;
import com.news.lee.mynews.HTTP.CommonRequest;
import com.news.lee.mynews.HTTP.DataUrl;
import com.news.lee.mynews.HTTP.DisposeDataHandle;
import com.news.lee.mynews.HTTP.DisposeDataListener;
import com.news.lee.mynews.HTTP.OkhttpManager;
import com.news.lee.mynews.R;
import com.news.lee.mynews.adapter.MypagerAdapter;
import com.news.lee.mynews.bean.BaseZhihuBean;
import com.news.lee.mynews.fragment.WeixinFragment;
import com.news.lee.mynews.fragment.ZhihuFragment;
import com.nostra13.universalimageloader.utils.L;

import java.util.ArrayList;

import okhttp3.Request;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    private TabLayout tabLayout;
    private ViewPager pager;
    private MypagerAdapter mypagerAdapter;
    private ZhihuFragment zhihuFragment;
    private WeixinFragment weixinFragment;
    private ArrayList<String> title_fragment;
    private ArrayList<Fragment> fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private TabLayout.Tab zhihu;
    private TabLayout.Tab weixin;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();



    }

    private void initView() {

        fragment=new ArrayList<>();
        title_fragment=new ArrayList<>();
        zhihuFragment=new ZhihuFragment();
        weixinFragment=new WeixinFragment();
        fragment.add(zhihuFragment);
        fragment.add(weixinFragment);
        title_fragment.add("知乎");
        title_fragment.add("微信");
        tabLayout= (TabLayout) findViewById(R.id.tab);
        pager= (ViewPager) findViewById(R.id.viewpager);


        tabLayout.setOnTabSelectedListener(this);

        fragmentManager=getSupportFragmentManager();

        mypagerAdapter=new MypagerAdapter(fragmentManager,fragment,title_fragment);

        pager.setAdapter(mypagerAdapter);
        tabLayout.setupWithViewPager(pager);

        System.out.println("main start... :");
        OkhttpManager.sendRequest(CommonRequest.createGetRequest(DataUrl.ZHIHU_TODAY,null),new CommonJsonCallback(new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {


                System.out.println("responseObj is :"+responseObj);
            }

            @Override
            public void onFailure(Object reasonObj) {

                System.out.println("reasonObj is :"+reasonObj);

            }
        })));


    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab==tabLayout.getTabAt(0)){
            pager.setCurrentItem(0);
        }
        else if (tab==tabLayout.getTabAt(1)){
            pager.setCurrentItem(1);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}

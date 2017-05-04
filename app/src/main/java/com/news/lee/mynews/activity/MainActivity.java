package com.news.lee.mynews.activity;




import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.news.lee.mynews.R;
import com.news.lee.mynews.Utils.PreferenceUtils;
import com.news.lee.mynews.adapter.MypagerAdapter;
import com.news.lee.mynews.fragment.CollectionFragment;
import com.news.lee.mynews.fragment.WeixinFragment;
import com.news.lee.mynews.fragment.ZhihuFragment;
import com.nostra13.universalimageloader.utils.L;

import java.util.ArrayList;


public class MainActivity extends BaseActivity implements TabLayout.OnTabSelectedListener, View.OnClickListener {

    private long current=0;

    private TabLayout tabLayout;
    private ViewPager pager;
    private MypagerAdapter mypagerAdapter;
    private ZhihuFragment zhihuFragment;
    private WeixinFragment weixinFragment;
    private CollectionFragment mineFragment;
    private ArrayList<String> title_fragment;
    private ArrayList<Fragment> fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private TabLayout.Tab zhihu;
    private TabLayout.Tab weixin;
    private TabLayout.Tab collection;


    private ImageView iv_setting;
    private ImageView iv_day_night;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();



    }

    private void initView() {

        iv_setting= (ImageView) findViewById(R.id.setting_view);
        iv_day_night= (ImageView) findViewById(R.id.day_night_view);

        iv_setting.setOnClickListener(this);
        iv_day_night.setOnClickListener(this);

        fragment=new ArrayList<>();
        title_fragment=new ArrayList<>();
        zhihuFragment=new ZhihuFragment();
        weixinFragment=new WeixinFragment();
        mineFragment=new CollectionFragment();
        fragment.add(zhihuFragment);
        fragment.add(weixinFragment);
        fragment.add(mineFragment);
        title_fragment.add("知乎");
        title_fragment.add("微信");
        title_fragment.add("收藏夹");
        tabLayout= (TabLayout) findViewById(R.id.tab);
        pager= (ViewPager) findViewById(R.id.viewpager);


        tabLayout.setOnTabSelectedListener(this);

        fragmentManager=getSupportFragmentManager();

        mypagerAdapter=new MypagerAdapter(fragmentManager,fragment,title_fragment);

        pager.setAdapter(mypagerAdapter);
        tabLayout.setupWithViewPager(pager);



    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab==tabLayout.getTabAt(0)){
            pager.setCurrentItem(0);
        }
        else if (tab==tabLayout.getTabAt(1)){
            pager.setCurrentItem(1);
        }
        else if (tab==tabLayout.getTabAt(2)){
            pager.setCurrentItem(2);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onClick(View v) {

        if (v.getId()==iv_setting.getId()){

            //设置页面的跳转。
            Intent intent=new Intent(MainActivity.this,SettingActivity.class);
            startActivity(intent);



        }else if (v.getId()==iv_day_night.getId()){

            if (PreferenceUtils.getPrefBoolean(this,"day_night",false)==false){
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                PreferenceUtils.setPrefBoolean(this,"day_night",true);
            }else {
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                PreferenceUtils.setPrefBoolean(this,"day_night",false);
            }


            recreate();

        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK && event.getAction()==KeyEvent.ACTION_DOWN){
            if (System.currentTimeMillis()-current>3000){
                current=System.currentTimeMillis();
                Toast.makeText(this,"再按一次退出应用",Toast.LENGTH_SHORT).show();
            }
            else {
                finish();
                System.exit(0);
            }

            return true;
        }

        return super.onKeyDown(keyCode,event);
    }

}

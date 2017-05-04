package com.news.lee.mynews.fragment;

import android.content.Context;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.news.lee.mynews.R;
import com.news.lee.mynews.Utils.PreferenceUtils;

import java.io.File;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;

/**
 * Created by lee on 17-5-4.
 */

public class Settingfragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {

    private String shengliuliang_key;
    private String day_night_key;
    private String clear_cacke_key;
    private String project_add_key;
    private String feedback_key;


    private CheckBoxPreference checkBoxPreference;
    private SwitchPreference switchPreference;
    private Preference clear_cache;
    private Preference project_add;
    private Preference feedback;
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        addPreferencesFromResource(R.xml.preferencescreen_setting);


        shengliuliang_key = getString(R.string.shengliuliang_key);
        day_night_key = getString(R.string.day_night_key);
        clear_cacke_key = getString(R.string.clear_cache_key);
        project_add_key = getString(R.string.project_key);
        feedback_key = getString(R.string.feedback_key);

        checkBoxPreference = (CheckBoxPreference) findPreference(shengliuliang_key);
        switchPreference = (SwitchPreference) findPreference(day_night_key);
        clear_cache= (Preference) findPreference(clear_cacke_key);
        project_add= (Preference) findPreference(project_add_key);
        feedback= (Preference) findPreference(feedback_key);
        checkBoxPreference.setOnPreferenceChangeListener(this);
        checkBoxPreference.setOnPreferenceClickListener(this);
        switchPreference.setOnPreferenceClickListener(this);
        switchPreference.setOnPreferenceChangeListener(this);

        clear_cache.setOnPreferenceClickListener(this);
        project_add.setOnPreferenceClickListener(this);
        feedback.setOnPreferenceClickListener(this);

        checkBoxPreference.setChecked(PreferenceUtils.getPrefBoolean(mContext,"shengliuliang",false));
        switchPreference.setChecked(PreferenceUtils.getPrefBoolean(mContext,"day_night",false));

        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {

        if (preference.getKey().equals(shengliuliang_key)) {

            if (checkBoxPreference.isChecked()==true){
                PreferenceUtils.setPrefBoolean(mContext,"shengliuliang",false);
                checkBoxPreference.setChecked(false);
            }
            else {
                PreferenceUtils.setPrefBoolean(mContext,"shengliuliang",true);
                checkBoxPreference.setChecked(true);
            }

           // Toast.makeText(mContext, "省流量模式改变", Toast.LENGTH_SHORT).show();
        } else if (preference.getKey().equals(day_night_key)) {

            if (switchPreference.isChecked()==true){
                PreferenceUtils.setPrefBoolean(mContext,"day_night",false);
                switchPreference.setChecked(false);
            }
            else {
                switchPreference.setChecked(true);
                PreferenceUtils.setPrefBoolean(mContext,"day_night",true);
            }
            
        }
        return false;
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {

        if (preference.getKey().equals(clear_cacke_key)) {

            File file=new File(String.valueOf(mContext.getCacheDir()));


            Toast.makeText(mContext, "缓存已经清除完毕", Toast.LENGTH_SHORT).show();

        }
        return false;
    }
}

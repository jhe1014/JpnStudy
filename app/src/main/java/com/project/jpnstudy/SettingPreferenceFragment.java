package com.project.jpnstudy;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

public class SettingPreferenceFragment extends PreferenceFragment {
    SharedPreferences prefs;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_preference);

       // prefs.registerOnSharedPreferenceChangeListener(prefListener);

    }

}
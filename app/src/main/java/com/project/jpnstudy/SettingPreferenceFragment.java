package com.project.jpnstudy;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class SettingPreferenceFragment extends PreferenceFragment {
    private Activity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_preference);

        mActivity = this.getActivity();

        final SwitchPreference lockStatus = (SwitchPreference) findPreference("lock");

        if(lockStatus != null) {
            lockStatus.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if(lockStatus.isChecked()) {
                        Intent intent = new Intent(mActivity, ScreenService.class);
                        mActivity.stopService(intent);
                        Toast.makeText(mActivity, "Unchecked", Toast.LENGTH_LONG).show();
                        lockStatus.setChecked(false);
                    } else {
                        Intent intent = new Intent(mActivity, ScreenService.class);
                        mActivity.startService(intent);
                        Toast.makeText(mActivity, "checked", Toast.LENGTH_LONG).show();
                        lockStatus.setChecked(true);
                    }
                    return false;
                }
            });
        }
    }
}

/*
 * Copyright (C) 2013 SlimRoms
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.slim;

import android.app.ActivityManager;
import android.content.Context;
import android.content.ContentResolver;
import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.Settings;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.preference.PreferenceCategory;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.SwitchPreference;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

import java.util.List;

public class SystemTools extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {

        private static final String PREF_HEADS_UP_FLOATING = "heads_up_floating";
        private SwitchPreference mHeadsUpFloatingWindow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.system_tools);

        mHeadsUpFloatingWindow = (SwitchPreference) findPreference(PREF_HEADS_UP_FLOATING);
        mHeadsUpFloatingWindow.setChecked(Settings.System.getIntForUser(getContentResolver(),
                Settings.System.HEADS_UP_FLOATING, 1, UserHandle.USER_CURRENT) == 1);
        mHeadsUpFloatingWindow.setOnPreferenceChangeListener(this);

    }

//    private boolean isPackageInstalled(String packageName) {
//        PackageManager pm = getPackageManager();
//        boolean installed = false;
//        try {
//           pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
//           installed = true;
//        } catch (PackageManager.NameNotFoundException e) {
//           installed = false;
//        }
//        return installed;
//    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference == mHeadsUpFloatingWindow) {
            Settings.System.putIntForUser(getContentResolver(),
                    Settings.System.HEADS_UP_FLOATING,
            (Boolean) newValue ? 1 : 0, UserHandle.USER_CURRENT);
            return true;
        }
        return false;
    }
}

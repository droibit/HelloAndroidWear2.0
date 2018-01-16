package com.github.droibit.hello_wear20

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceFragment
import android.preference.PreferenceGroup
import android.support.wearable.preference.PreferenceIconHelper
import android.support.wearable.preference.WearablePreferenceActivity
import android.view.View

class SettingsActivity : WearablePreferenceActivity() {

    class SettingsFragment : PreferenceFragment() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.settings)
        }

        override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            PreferenceIconHelper.wrapAllIconsInGroup(findPreference("screen") as PreferenceGroup)
        }
    }

    companion object {

        fun createIntent(context: Context): Intent {
            return Intent(context, SettingsActivity::class.java)
                    .putExtra(":android:show_fragment", SettingsFragment::class.java.name)
        }
    }
}

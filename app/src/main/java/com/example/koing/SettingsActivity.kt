package com.example.koing

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.text.TextUtils.replace
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.koing.databinding.ActivityFavoriteBinding
import com.example.koing.databinding.SettingsActivityBinding

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)

        setSupportActionBar(findViewById(R.id.settings_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment(preferenceChangeListener))
            .commit()
    }

    val preferenceChangeListener: SharedPreferences.OnSharedPreferenceChangeListener =
        SharedPreferences.OnSharedPreferenceChangeListener { var1, key ->
            when (key){
                "test1", "test2" -> {
                    val intent: Intent = getIntent()
                    finish()
                    startActivity(intent)
                }
                else -> {
                }
            }
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
            else -> {}
        }
        return super .onOptionsItemSelected(item)
    }

    class SettingsFragment(val listener: SharedPreferences.OnSharedPreferenceChangeListener) : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            val namePreference: EditTextPreference? = findPreference("username")
            namePreference?.summaryProvider = Preference.SummaryProvider<EditTextPreference>{
                preference ->
                val text = preference.text
                if(TextUtils.isEmpty(text)){
                    "닉네임 설정이 되지 않았습니다."
                }
                else{
                    "$text"
                }
            }

        }

        override fun onResume() {
            super.onResume()
            preferenceManager.sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
        }

        override fun onPause() {
            super.onPause()
            preferenceManager.sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
        }
    }
}
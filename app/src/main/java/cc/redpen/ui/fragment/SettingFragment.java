package cc.redpen.ui.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import cc.redpen.R;

import static cc.redpen.helper.ViewFindHelper.getPreference;

public class SettingFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceClickListener {

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    public SettingFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);
        setUpLayout();
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        setUpLayout();
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        switch (preference.getTitleRes()) {
            case R.string.label_setting_licence:

                break;
        }
        return false;
    }

    private void setUpLayout() {
        PreferenceScreen licenceScreen = getPreference(this, getString(R.string.label_setting_licence));
        licenceScreen.setOnPreferenceClickListener(this);
        ListPreference languagePref = getPreference(this, getString(R.string.preference_title_language));
        languagePref.setSummary(languagePref.getEntry());
    }

}
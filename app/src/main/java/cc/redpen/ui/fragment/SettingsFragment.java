package cc.redpen.ui.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import cc.redpen.R;
import cc.redpen.util.MetaDataUtil;
import de.psdev.licensesdialog.LicensesDialogFragment;

import static cc.redpen.Application.getContext;
import static cc.redpen.helper.IntentHelper.createIntentWithUrl;
import static cc.redpen.helper.ViewHelper.getPreference;

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceClickListener {

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    public SettingsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
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
            case R.string.label_setting_about:
                startActivity(createIntentWithUrl(getString(R.string.redpen_url)));
                break;
            case R.string.label_setting_feedback:
                startActivity(createIntentWithUrl(getString(R.string.github_issue_url)));
                break;
            case R.string.label_setting_licence:
                LicensesDialogFragment fragment = LicensesDialogFragment.newInstance(R.raw.notices, false, true);
                fragment.show(getSupportFragmentManager(), null);
                break;
        }
        return false;
    }

    private FragmentManager getSupportFragmentManager() {
        return ((ActionBarActivity) getActivity()).getSupportFragmentManager();
    }

    private void setUpLayout() {
        ListPreference languagePref = getPreference(this, getString(R.string.label_setting_language));
        languagePref.setSummary(languagePref.getEntry());
        PreferenceScreen aboutScreen = getPreference(this, getString(R.string.label_setting_about));
        aboutScreen.setOnPreferenceClickListener(this);
        PreferenceScreen feedbackScreen = getPreference(this, getString(R.string.label_setting_feedback));
        feedbackScreen.setOnPreferenceClickListener(this);
        PreferenceScreen licenceScreen = getPreference(this, getString(R.string.label_setting_licence));
        licenceScreen.setOnPreferenceClickListener(this);
        PreferenceScreen versionScreen = getPreference(this, getString(R.string.label_setting_version));
        versionScreen.setSummary(MetaDataUtil.getVersionName(getContext()));
    }

}
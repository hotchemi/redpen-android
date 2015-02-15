package cc.redpen.ui.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import cc.redpen.R;

public class SettingFragment extends PreferenceFragment {

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    public SettingFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);
    }

}
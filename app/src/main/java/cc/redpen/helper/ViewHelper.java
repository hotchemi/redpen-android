package cc.redpen.helper;

import android.preference.PreferenceFragment;

public final class ViewHelper {

    private ViewHelper() {
    }

    public static <T> T getPreference(PreferenceFragment fragment, CharSequence key) {
        return (T) fragment.getPreferenceScreen().findPreference(key);
    }
}
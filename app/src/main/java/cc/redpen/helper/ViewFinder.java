package cc.redpen.helper;

import android.preference.PreferenceFragment;

public final class ViewFinder {

    private ViewFinder() {
    }

    public static <T> T getPreference(PreferenceFragment fragment, CharSequence key) {
        return (T) fragment.getPreferenceScreen().findPreference(key);
    }

}
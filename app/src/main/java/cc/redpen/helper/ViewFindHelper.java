package cc.redpen.helper;

import android.preference.PreferenceFragment;

public final class ViewFindHelper {

    private ViewFindHelper(){
    }

    public static <T> T getPreference(PreferenceFragment fragment, CharSequence key) {
        return (T) fragment.getPreferenceScreen().findPreference(key);
    }

}
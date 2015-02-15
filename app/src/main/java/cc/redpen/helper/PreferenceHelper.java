package cc.redpen.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import cc.redpen.R;

public final class PreferenceHelper {

    private PreferenceHelper() {
    }

    private static SharedPreferences getDefaultPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setLanguage(Context context, String locale) {
        SharedPreferences.Editor editor = getDefaultPreference(context).edit();
        editor.putString(context.getString(R.string.label_setting_language), locale).apply();
    }

    public static String getLanguage(Context context) {
        SharedPreferences preferences = getDefaultPreference(context);
        return preferences.getString(context.getString(R.string.label_setting_language), null);
    }

}
package cc.redpen.helper;

import android.content.Context;

import java.util.Locale;

public final class LocaleHelper {

    private LocaleHelper(){
    }

    public static Locale getDefaultLocale(Context context) {
        return context.getResources().getConfiguration().locale;
    }

    public static String getLocaleValueForPreference(Context context) {
        return Locale.JAPAN.equals(getDefaultLocale(context)) ? Locale.JAPANESE.toString() : Locale.ENGLISH.toString();
    }

}
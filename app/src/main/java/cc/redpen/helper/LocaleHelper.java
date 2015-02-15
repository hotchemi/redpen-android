package cc.redpen.helper;

import android.content.Context;
import cc.redpen.R;

import java.util.Locale;

public final class LocaleHelper {

    private LocaleHelper() {
    }

    public static String getLanguage(Context context) {
        return isJapanese(context) ? Locale.JAPANESE.toString() : Locale.ENGLISH.toString();
    }

    public static int getSampleResourceId(Context context) {
        if (Locale.JAPANESE.toString().equals(PreferenceHelper.getLanguage(context))) {
            return R.string.sample_text_ja;
        } else {
            return R.string.sample_text_en;
        }
    }

    private static boolean isJapanese(Context context) {
        return Locale.JAPAN.equals(getDefaultLocale(context));
    }

    private static Locale getDefaultLocale(Context context) {
        return context.getResources().getConfiguration().locale;
    }

}
package cc.redpen;

import android.content.Context;
import cc.redpen.helper.LocaleHelper;
import cc.redpen.helper.PreferenceHelper;

public class Application extends android.app.Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        String locale = LocaleHelper.getLocaleValueForPreference(context);
        PreferenceHelper.setDefaultLocale(context, locale);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}

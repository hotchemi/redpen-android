package cc.redpen.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

public final class MetaDataUtil {

    private static final String TAG = MetaDataUtil.class.getSimpleName();

    private MetaDataUtil() {
    }

    public static String getVersionName(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.w(TAG, e);
        }
        return "";
    }

}
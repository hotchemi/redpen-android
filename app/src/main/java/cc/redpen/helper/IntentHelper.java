package cc.redpen.helper;

import android.content.Intent;
import android.os.Bundle;

public final class IntentHelper {

    private IntentHelper() {
    }

    public static String getExtraText(Intent intent) {
        if (!Intent.ACTION_SEND.equals(intent.getAction())) {
            return null;
        }
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return null;
        }
        return extras.getCharSequence(Intent.EXTRA_TEXT).toString();
    }

}
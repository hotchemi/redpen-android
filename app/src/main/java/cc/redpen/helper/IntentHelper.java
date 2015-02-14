package cc.redpen.helper;

import android.content.Intent;
import android.os.Bundle;

public final class IntentHelper {

    private IntentHelper() {
    }

    public static Intent createIntentWithText(CharSequence text) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        return intent;
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
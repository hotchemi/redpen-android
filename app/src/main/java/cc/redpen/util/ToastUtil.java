package cc.redpen.util;

import android.widget.Toast;

import static cc.redpen.Application.getContext;

public final class ToastUtil {

    private ToastUtil() {
    }

    public static void show(int messageId) {
        Toast.makeText(getContext(), messageId, Toast.LENGTH_SHORT).show();
    }

}
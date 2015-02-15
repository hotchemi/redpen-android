package cc.redpen.helper;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import static cc.redpen.Application.getContext;

public final class ClipboardHelper {

    private ClipboardHelper() {
    }

    private static ClipboardManager getClipboardManger(Context context) {
       return (android.content.ClipboardManager) context.getSystemService(getContext().CLIPBOARD_SERVICE);
    }

    public static void copy(Context context, CharSequence text) {
        ClipboardManager clipboard =getClipboardManger(context);
        ClipData clip = ClipData.newPlainText("text", text);
        clipboard.setPrimaryClip(clip);
    }

    public static CharSequence getText(Context context) {
        ClipData cd = getClipboardManger(context).getPrimaryClip();
        if (cd == null) {
            return "";
        }
        ClipData.Item item = cd.getItemAt(0);
        return item.getText();
    }

}
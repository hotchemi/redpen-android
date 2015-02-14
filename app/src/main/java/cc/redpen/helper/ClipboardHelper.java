package cc.redpen.helper;

import android.content.ClipData;
import android.content.ClipboardManager;

import static cc.redpen.Application.getContext;

public final class ClipboardHelper {

    private static final String EMPTY = "";

    private ClipboardHelper() {
    }

    private static ClipboardManager getClipboardManger() {
       return (android.content.ClipboardManager) getContext().getSystemService(getContext().CLIPBOARD_SERVICE);
    }

    public static void copy(CharSequence text) {
        ClipboardManager clipboard =getClipboardManger();
        ClipData clip = ClipData.newPlainText("text", text);
        clipboard.setPrimaryClip(clip);
    }

    public static CharSequence getText() {
        ClipData cd = getClipboardManger().getPrimaryClip();
        if (cd == null) {
            return EMPTY;
        }
        ClipData.Item item = cd.getItemAt(0);
        return item.getText();
    }

}
package cc.redpen.helper;

import android.content.ClipData;

import static cc.redpen.Application.getContext;

public final class ClipboardHelper {

    private static final String EMPTY = "";

    private static final android.content.ClipboardManager CLIPBOARD =
            (android.content.ClipboardManager) getContext().getSystemService(getContext().CLIPBOARD_SERVICE);

    private ClipboardHelper() {
    }

    public static CharSequence getText() {
        ClipData cd = CLIPBOARD.getPrimaryClip();
        if (cd == null) {
            return EMPTY;
        }
        ClipData.Item item = cd.getItemAt(0);
        return item.getText();
    }

}
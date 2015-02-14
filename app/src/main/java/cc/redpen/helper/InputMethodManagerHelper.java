package cc.redpen.helper;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public final class InputMethodManagerHelper {

    private InputMethodManagerHelper() {
    }

    public static void hideSoftInputFromWindow(EditText editText) {
        InputMethodManager in = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(editText.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

}

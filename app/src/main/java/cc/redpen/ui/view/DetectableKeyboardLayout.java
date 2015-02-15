package cc.redpen.ui.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Display;
import android.widget.LinearLayout;

public class DetectableKeyboardLayout extends LinearLayout {

    public interface KeyboardListener {
        void onKeyboardShown();
        void onKeyboardHidden();
    }

    private static final int MIN_KEYBOARD_HEIGHT = 100;

    private static final Rect RECT = new Rect();

    private static final Point POINT = new Point();

    private KeyboardListener listener;

    public DetectableKeyboardLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DetectableKeyboardLayout(Context context) {
        super(context);
    }

    public void setKeyboardListener(KeyboardListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int viewHeight = MeasureSpec.getSize(heightMeasureSpec);
        Activity activity = (Activity) getContext();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(RECT);
        int statusBarHeight = RECT.top;
        Display display = activity.getWindowManager().getDefaultDisplay();
        display.getSize(POINT);
        int screenHeight = POINT.y;
        int diff = (screenHeight - statusBarHeight) - viewHeight;
        if (diff > MIN_KEYBOARD_HEIGHT) {
            listener.onKeyboardShown();
        } else {
            listener.onKeyboardHidden();
        }
    }

}
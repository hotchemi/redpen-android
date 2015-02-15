package cc.redpen.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import butterknife.ButterKnife;
import cc.redpen.R;
import cc.redpen.event.BusProvider;
import cc.redpen.event.KeyboardHiddenEvent;
import cc.redpen.event.KeyboardShownEvent;
import cc.redpen.ui.fragment.MainFragment;
import cc.redpen.ui.view.DetectableKeyboardLayout;

import static cc.redpen.helper.IntentHelper.getExtraText;

public class MainActivity extends ActionBarActivity implements DetectableKeyboardLayout.KeyboardListener {

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpLayout();
        String extraText = getExtraText(getIntent());
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, MainFragment.newInstance(extraText))
                    .commit();
        }
    }

    @Override
    public void onKeyboardShown() {
        actionBar.hide();
        BusProvider.getInstance().post(new KeyboardShownEvent());
    }

    @Override
    public void onKeyboardHidden() {
        actionBar.show();
        BusProvider.getInstance().post(new KeyboardHiddenEvent());
    }

    private void setUpLayout() {
        Toolbar toolBar = ButterKnife.findById(this, R.id.toolbar);
        setSupportActionBar(toolBar);
        actionBar = getSupportActionBar();
        actionBar.setElevation(0);
        DetectableKeyboardLayout root = ButterKnife.findById(this, R.id.detectable_keyboard);
        root.setKeyboardListener(this);
    }

}
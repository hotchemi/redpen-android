package cc.redpen.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import butterknife.ButterKnife;
import cc.redpen.R;
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
    }

    @Override
    public void onKeyboardHidden() {
        actionBar.show();
    }

    private void setUpLayout() {
        Toolbar toolBar = ButterKnife.findById(this, R.id.tool_bar);
        setSupportActionBar(toolBar);
        actionBar = getSupportActionBar();
        actionBar.setElevation(0);
        DetectableKeyboardLayout root = ButterKnife.findById(this, R.id.detectable_keyboard);
        root.setKeyboardListener(this);
    }

}
package cc.redpen.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import butterknife.ButterKnife;
import cc.redpen.R;
import cc.redpen.ui.fragment.SettingFragment;
import cc.redpen.ui.view.DetectableKeyboardLayout;

public class SettingActivity extends ActionBarActivity {

    public static Intent createIntent(Context context) {
        return new Intent(context, SettingActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Toolbar toolBar = ButterKnife.findById(this, R.id.toolbar);
        setSupportActionBar(toolBar);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, SettingFragment.newInstance()).commit();
        }
    }

}
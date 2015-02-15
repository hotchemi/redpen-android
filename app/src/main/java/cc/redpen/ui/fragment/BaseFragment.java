package cc.redpen.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import butterknife.ButterKnife;

abstract class BaseFragment extends Fragment {

    protected ActionBarActivity activity;

    protected ActionBar actionBar;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (ActionBarActivity) activity;
        actionBar = this.activity.getSupportActionBar();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    protected Context getApplicationContext() {
        return activity.getApplicationContext();
    }

}
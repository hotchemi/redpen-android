package cc.redpen.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cc.redpen.R;
import cc.redpen.entity.ValidateResult;
import cc.redpen.loader.ValidateLoader;

import static cc.redpen.Application.getContext;

public class MainFragment extends Fragment implements LoaderManager.LoaderCallbacks<ValidateResult>, View.OnKeyListener {

    private static final String LOADER_ARGS_INPUT = "LOADER_ARGS_INPUT";

    private static final int LOADER_ID = 21;

    @InjectView(R.id.input_edittext)
    EditText inputEditText;

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, rootView);
        inputEditText.setOnKeyListener(this);
        return rootView;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
            String document = inputEditText.getText().toString();
            startLoader(document);
            return true;
        }
        return false;
    }

    @Override
    public Loader<ValidateResult> onCreateLoader(int id, Bundle args) {
        String document = args.getString(LOADER_ARGS_INPUT);
        return new ValidateLoader(getContext(), document);
    }

    @Override
    public void onLoadFinished(Loader<ValidateResult> loader, ValidateResult data) {
    }

    @Override
    public void onLoaderReset(Loader<ValidateResult> loader) {
    }

    private void startLoader(String text) {
        Bundle args = new Bundle();
        args.putString(LOADER_ARGS_INPUT, text);
        getLoaderManager().initLoader(LOADER_ID, args, this);
    }

}
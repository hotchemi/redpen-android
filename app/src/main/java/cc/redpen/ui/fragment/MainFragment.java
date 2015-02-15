package cc.redpen.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.*;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cc.redpen.R;
import cc.redpen.ui.activity.SettingActivity;
import cc.redpen.ui.adapter.ValidateResultAdapter;
import cc.redpen.helper.ClipboardHelper;
import cc.redpen.helper.InputMethodManagerHelper;
import cc.redpen.model.entity.ValidateResult;
import cc.redpen.model.loader.ValidateLoader;
import cc.redpen.util.ToastUtil;
import com.melnykov.fab.FloatingActionButton;

import static cc.redpen.Application.getContext;
import static cc.redpen.helper.IntentHelper.createIntentWithText;

public class MainFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<ValidateResult>, View.OnClickListener, TextView.OnEditorActionListener {

    private static final String KEY_EXTRA_TEXT = "key_extra_text";

    private static final String LOADER_ARGS_INPUT = "LOADER_ARGS_INPUT";

    private static final int LOADER_ID = 21;

    private RecyclerView.LayoutManager layoutManager;

    private RecyclerView.Adapter adapter;

    @InjectView(R.id.recycler_view)
    RecyclerView recyclerView;

    @InjectView(R.id.document_edittext)
    EditText documentEditText;

    @InjectView(R.id.fab)
    FloatingActionButton floatingActionButton;

    @InjectView(R.id.swipe_refreshlayout)
    SwipeRefreshLayout swipeRefreshLayout;

    public MainFragment() {
    }

    public static MainFragment newInstance(String text) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_EXTRA_TEXT, text);
        MainFragment fragment = new MainFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, rootView);
        setUpLayout();
        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String extraText = getArguments().getString(KEY_EXTRA_TEXT);
        if (TextUtils.isEmpty(extraText)) {
            return;
        }
        documentEditText.setText(extraText);
        startLoader(extraText);
    }

    @Override
    public Loader<ValidateResult> onCreateLoader(int id, Bundle args) {
        String document = args.getString(LOADER_ARGS_INPUT);
        return new ValidateLoader(getContext(), document);
    }

    @Override
    public void onLoadFinished(Loader<ValidateResult> loader, ValidateResult data) {
        adapter = new ValidateResultAdapter(data.getErrors());
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoaderReset(Loader<ValidateResult> loader) {
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
            String document = documentEditText.getText().toString();
            startLoader(document);
            InputMethodManagerHelper.hideSoftInputFromWindow(documentEditText);
            actionBar.show();
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                documentEditText.setText(ClipboardHelper.getText());
                break;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_copy:
                ClipboardHelper.copy(documentEditText.getText());
                ToastUtil.show(R.string.message_copy);
                break;
            case R.id.action_clear:
                documentEditText.setText("");
                break;
            case R.id.action_share:
                startActivity(createIntentWithText(documentEditText.getText()));
                break;
            case R.id.action_add_sample:
                documentEditText.setText(getString(R.string.sample_text));
                break;
            case R.id.action_settings:
                startActivity(SettingActivity.createIntent(activity));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpLayout() {
        floatingActionButton.setOnClickListener(this);
        documentEditText.setOnEditorActionListener(this);
        layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }

    private void startLoader(String text) {
        Bundle bundle = new Bundle();
        bundle.putString(LOADER_ARGS_INPUT, text);
        getLoaderManager().initLoader(LOADER_ID, bundle, this);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setColorSchemeResources(R.color.accent, R.color.accent_material_light);
    }

}
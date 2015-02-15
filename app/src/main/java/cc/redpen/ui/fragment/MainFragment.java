package cc.redpen.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
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
import cc.redpen.event.BusProvider;
import cc.redpen.event.KeyboardHiddenEvent;
import cc.redpen.event.KeyboardShownEvent;
import cc.redpen.helper.ClipboardHelper;
import cc.redpen.helper.InputMethodManagerHelper;
import cc.redpen.model.entity.ValidateResult;
import cc.redpen.model.loader.ValidateLoader;
import cc.redpen.ui.activity.SettingsActivity;
import cc.redpen.ui.adapter.ValidateResultAdapter;
import cc.redpen.util.ToastUtil;
import com.melnykov.fab.FloatingActionButton;
import com.squareup.otto.Subscribe;

import static cc.redpen.Application.getContext;
import static cc.redpen.helper.IntentHelper.createIntentWithText;
import static cc.redpen.helper.LocaleHelper.getSampleResourceId;

public class MainFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<ValidateResult>, View.OnClickListener, TextView.OnEditorActionListener, Runnable {

    private static final String KEY_EXTRA_TEXT = "key_extra_text";

    private static final String LOADER_ARGS_INPUT = "LOADER_ARGS_INPUT";

    private static final int LOADER_ID = 21;

    private static final Handler HANDLER = new Handler();

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, rootView);
        setUpLayout();
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        BusProvider.getInstance().register(this);
        String extraText = getArguments().getString(KEY_EXTRA_TEXT);
        if (TextUtils.isEmpty(extraText)) {
            return;
        }
        documentEditText.setText(extraText);
        startLoader(extraText);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BusProvider.getInstance().unregister(this);
        ButterKnife.reset(this);
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
        String document = documentEditText.getText().toString();
        startLoader(document);
        InputMethodManagerHelper.hideSoftInputFromWindow(documentEditText);
        actionBar.show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Subscribe
    public void onKeyboardShown(KeyboardShownEvent event) {
        floatingActionButton.hide();
    }

    @Subscribe
    public void onKeyboardHidden(KeyboardHiddenEvent event) {
        HANDLER.postDelayed(this, 100);
    }

    @Override
    public void run() {
        if (floatingActionButton == null) {
            return;
        }
        floatingActionButton.requestLayout();
        floatingActionButton.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_copy:
                ClipboardHelper.copy(getApplicationContext(), documentEditText.getText());
                ToastUtil.show(R.string.message_copy);
                break;
            case R.id.action_clear:
                documentEditText.setText("");
                break;
            case R.id.action_share:
                startActivity(createIntentWithText(documentEditText.getText()));
                break;
            case R.id.action_add_sample:
                documentEditText.setText(getSampleResourceId(getApplicationContext()));
                break;
            case R.id.action_settings:
                startActivity(SettingsActivity.createIntent(activity));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpLayout() {
        swipeRefreshLayout.setEnabled(false);
        documentEditText.setOnEditorActionListener(this);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        floatingActionButton.setOnClickListener(this);
        floatingActionButton.attachToRecyclerView(recyclerView);
        floatingActionButton.hide();
    }

    private void startLoader(String text) {
        Bundle bundle = new Bundle();
        bundle.putString(LOADER_ARGS_INPUT, text);
        getLoaderManager().initLoader(LOADER_ID, bundle, this);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setColorSchemeResources(R.color.accent, R.color.accent_material_light);
    }

}
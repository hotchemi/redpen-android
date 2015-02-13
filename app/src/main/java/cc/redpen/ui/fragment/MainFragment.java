package cc.redpen.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.melnykov.fab.FloatingActionButton;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cc.redpen.R;
import cc.redpen.adapter.ValidateResultAdapter;
import cc.redpen.helper.ClipboardManager;
import cc.redpen.model.entity.ValidateResult;
import cc.redpen.model.loader.ValidateLoader;

import static cc.redpen.Application.getContext;

public class MainFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<ValidateResult>, View.OnKeyListener, View.OnClickListener {

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

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, rootView);
        setUpLayout();
        return rootView;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
            String document = documentEditText.getText().toString();
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
        adapter = new ValidateResultAdapter(data.getErrors());
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoaderReset(Loader<ValidateResult> loader) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.document_edittext:
                break;
            case R.id.fab:
                documentEditText.setText(ClipboardManager.getText());
                break;
        }
    }

    private void setUpLayout() {
        floatingActionButton.setOnClickListener(this);
        documentEditText.setOnKeyListener(this);
        documentEditText.setOnClickListener(this);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }

    private void startLoader(String text) {
        Bundle args = new Bundle();
        args.putString(LOADER_ARGS_INPUT, text);
        getLoaderManager().initLoader(LOADER_ID, args, this);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setColorSchemeResources(R.color.accent, R.color.tetal_500);
    }

}
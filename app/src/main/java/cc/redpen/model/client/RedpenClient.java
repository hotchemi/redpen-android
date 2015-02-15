package cc.redpen.model.client;

import cc.redpen.R;
import cc.redpen.model.entity.ValidateResult;
import cc.redpen.model.service.RedpenService;
import retrofit.Callback;
import retrofit.RestAdapter;

import java.util.HashMap;
import java.util.Map;

import static cc.redpen.Application.getContext;
import static cc.redpen.helper.PreferenceHelper.getLanguage;

public final class RedpenClient {

    private static final RedpenService INSTANCE;

    static {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(getContext().getString(R.string.api_url))
                .build();
        INSTANCE = restAdapter.create(RedpenService.class);
    }

    private RedpenClient() {
    }

    public static void validate(String document, Callback<ValidateResult> callback) {
        Map<String, String> queries = new HashMap<>();
        queries.put("lang", getLanguage(getContext()));
        queries.put("document", document);
        INSTANCE.validate(queries, callback);
    }

}
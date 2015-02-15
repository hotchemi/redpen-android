package cc.redpen.model.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import cc.redpen.model.client.ValidateClient;
import cc.redpen.model.converter.GsonConverter;
import cc.redpen.model.entity.ValidateResult;

public class ValidateLoader extends AsyncTaskLoader<ValidateResult> {

    private String document;

    public ValidateLoader(Context context, String document) {
        super(context);
        this.document = document;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ValidateResult loadInBackground() {
        String body = ValidateClient.request("en", document);
        return GsonConverter.convert(body);
    }

}
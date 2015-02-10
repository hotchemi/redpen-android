package cc.redpen.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import cc.redpen.client.ValidateClient;
import cc.redpen.entity.ValidateResult;
import cc.redpen.helper.GsonConverter;

public class ValidateLoader extends AsyncTaskLoader<ValidateResult> {

    private String document;

    public ValidateLoader(Context context, String document) {
        super(context);
        this.document = document;
    }

    @Override
    public ValidateResult loadInBackground() {
        String body = ValidateClient.request(null, document);
        return GsonConverter.convert(body);
    }

}
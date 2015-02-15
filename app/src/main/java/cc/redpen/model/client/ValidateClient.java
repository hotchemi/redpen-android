package cc.redpen.model.client;

import android.util.Log;
import cc.redpen.R;
import com.squareup.okhttp.*;

import java.io.IOException;

import static cc.redpen.Application.getContext;

public final class ValidateClient {

    private static final String TAG = ValidateClient.class.getSimpleName();

    private ValidateClient() {
    }

    public static String request(String lang, String document) {
        RequestBody requestBody = new FormEncodingBuilder()
                .add("lang", lang).add("document", document).build();
        Request request = new Request.Builder()
                .url(getContext().getString(R.string.api_url)).post(requestBody).build();
        return execute(request);
    }

    private static String execute(Request request) {
        try {
            Response response = new OkHttpClient().newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            Log.w(TAG, e);
        }
        return null;
    }

}
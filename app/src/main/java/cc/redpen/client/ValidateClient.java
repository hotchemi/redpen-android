package cc.redpen.client;

import android.util.Log;
import com.squareup.okhttp.*;

import java.io.IOException;

public class ValidateClient {
    
    private static final String TAG = ValidateClient.class.getSimpleName();

    private static final OkHttpClient CLIENT = new OkHttpClient();

    private ValidateClient() {
    }

    public static String request(String lang, String document) {
        RequestBody requestBody = new FormEncodingBuilder()
                .add("lang", lang)
                .add("document", document)
                .build();
        Request request = new Request.Builder()
                .url("http://httpbin.org/post")
                .post(requestBody)
                .build();
        String result = null;
        try {
            Response response = CLIENT.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            Log.w(TAG, e);
        }
        return result;
    }

}
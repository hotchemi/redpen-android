package cc.redpen.model.converter;

import cc.redpen.model.entity.ValidateResult;
import com.google.gson.Gson;

public class GsonConverter {

    private static final Gson GSON = new Gson();

    private GsonConverter() {
    }

    public static ValidateResult convert(String body) {
        return GSON.fromJson(body, ValidateResult.class);
    }

}
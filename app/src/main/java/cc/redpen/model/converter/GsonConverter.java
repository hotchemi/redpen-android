package cc.redpen.model.converter;

import cc.redpen.model.entity.ValidateResult;
import com.google.gson.Gson;

public final class GsonConverter {

    private GsonConverter() {
    }

    public static ValidateResult convert(String body) {
        return new Gson().fromJson(body, ValidateResult.class);
    }

}
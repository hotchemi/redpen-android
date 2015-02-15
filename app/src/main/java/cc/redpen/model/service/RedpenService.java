package cc.redpen.model.service;

import cc.redpen.model.entity.ValidateResult;
import retrofit.Callback;
import retrofit.http.POST;
import retrofit.http.QueryMap;

import java.util.Map;

public interface RedpenService {
    @POST("/rest/document/validate")
    void validate(@QueryMap Map<String, String> queries, Callback<ValidateResult> callback);
}
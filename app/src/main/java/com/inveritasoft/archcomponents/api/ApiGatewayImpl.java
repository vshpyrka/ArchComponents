package com.inveritasoft.archcomponents.api;

import org.json.JSONObject;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Oleksandr Kryvoruchko on 23.04.2018.
 */
public class ApiGatewayImpl implements ApiGateway {

    private static final String URL = "";

    public static final MediaType MEDIA_TYPE =
            MediaType.parse("application/json");

    private ApiGatewayImpl instance;

    private OkHttpClient okHttpClient = new OkHttpClient();

    private JSONObject postdata = new JSONObject();

    private RequestBody body = RequestBody.create(MEDIA_TYPE,
            postdata.toString());

    public ApiGatewayImpl getInstance() {
        if (instance == null) {
            instance = new ApiGatewayImpl();
        }
        return instance;
    }

    @Override
    public void doLogin(String username, Callback callback) {
        final Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Your Token")
                .addHeader("cache-control", "no-cache")
                .build();
        makeCall(request, callback);
    }

    private void makeCall(Request request, Callback callback) {
        okHttpClient.newCall(request).enqueue(callback);
    }
}

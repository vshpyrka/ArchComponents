package com.inveritasoft.archcomponents.api;

import android.content.Context;
import android.content.SharedPreferences;

import com.inveritasoft.archcomponents.presentation.App;
import com.inveritasoft.archcomponents.presentation.main.utils.Keys;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Oleksandr Kryvoruchko on 23.04.2018.
 */
public class ApiGatewayImpl implements ApiGateway {

    private static final String URL = "";

    public static final MediaType MEDIA_TYPE =
            MediaType.parse("application/json");

    private static ApiGatewayImpl instance;

    private OkHttpClient okHttpClient;

    private JSONObject postData = new JSONObject();

    private RequestBody body;

    private SharedPreferences sharedPreferences;

    public static ApiGatewayImpl getInstance() {
        if (instance == null) {
            instance = new ApiGatewayImpl();
        }
        return instance;
    }

    public ApiGatewayImpl() {
        initClient();
        sharedPreferences = App.getInstance().getApplicationContext().getSharedPreferences(Keys.SHARED_PREFS_KEY, Context.MODE_PRIVATE);
    }

    private void initClient() {
        okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        final Request original = chain.request();
                        final Request authorized = original.newBuilder()
                                .addHeader("Cookie", "cookie-name=cookie-value")
                                .build();
                        return chain.proceed(authorized);
                    }
                })
                .build();
    }

    @Override
    public void doLogin(String userName, Callback callback) {
        RequestBody formBody = new FormBody.Builder()
                .add("name", userName)
                .add("push_token", sharedPreferences.getString(Keys.PUSH_TOKEN, null))
                .add("setCookie", "true")
                .build();
        final Request request = new Request.Builder()
                .url(URL)
                .post(formBody)
                .addHeader("Content-Type", "application/json")
                .build();
        makeCall(request, callback);
    }

    private void makeCall(Request request, Callback callback) {
        okHttpClient.newCall(request).enqueue(callback);
    }
}

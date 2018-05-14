package com.inveritasoft.archcomponents.api;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.inveritasoft.archcomponents.BuildConfig;
import com.inveritasoft.archcomponents.presentation.main.adapter.BookModel;
import com.inveritasoft.archcomponents.presentation.main.utils.Keys;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Callback;
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

    private static final String TAG = "ApiGatewayImpl";

    private static final String API_URL = BuildConfig.API_URL;

    private static final String SIGNUP_URL = API_URL + "/signup";

    private static final String GET_BOOK_URL = API_URL + "/books";

    private static final String UPDATE_BOOKS_URL = API_URL + "/books";

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static final String HEADER_COOKIE = "Cookie";

    private static final String HEADER_SET_COOKIE = "Set-Cookie";

    private OkHttpClient okHttpClient;

    private SharedPreferences sharedPreferences;

    public ApiGatewayImpl(final SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        initClient();
    }

    private void initClient() {
        okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(@NonNull final Chain chain) throws IOException {
                        final Request original = chain.request();
                        final Request authorized = original.newBuilder()
                                .addHeader("Content-type", "application/json")
                                .build();
                        return chain.proceed(authorized);
                    }
                })
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(@NonNull final Chain chain) throws IOException {
                        final Request original = chain.request();
                        if (sharedPreferences.contains(Keys.COOKIE)) {
                            final String cookies = sharedPreferences.getString(Keys.COOKIE, null);
                            final Request withCookies = original.newBuilder()
                                    .addHeader(HEADER_COOKIE, cookies)
                                    .build();
                            return chain.proceed(withCookies);
                        }
                        return chain.proceed(original);
                    }
                })
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(@NonNull final Chain chain) throws IOException {
                        final Request original = chain.request();
                        final Response response = chain.proceed(original);
                        if (original.url().toString().equals(SIGNUP_URL)) {
                            sharedPreferences.edit().putString(Keys.COOKIE, response.headers().get(HEADER_SET_COOKIE)).apply();
                        }
                        return response;
                    }
                })
                .build();
    }

    @Override
    public void login(final String userName, final Callback callback) {
        if (sharedPreferences.contains(Keys.PUSH_TOKEN)) {
            try {
                final JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", userName);
                jsonObject.put("push_token", sharedPreferences.getString(Keys.PUSH_TOKEN, null));
                final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
                final Request request = new Request.Builder()
                        .url(SIGNUP_URL)
                        .post(body)
                        .build();
                makeCall(request, callback);
            } catch (final JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.d(TAG, "login: no Token");
        }
    }

    @Override
    public void getBooks(final Callback callback) {
        final Request request = new Request.Builder()
                .url(GET_BOOK_URL)
                .get()
                .build();
        makeCall(request, callback);
    }

    @Override
    public void updateBooks(final List<BookModel> books, final Callback callback) {
        try {
            final RequestBody body = RequestBody.create(JSON, prepareBookResponse(books));
            final Request request = new Request.Builder()
                    .url(UPDATE_BOOKS_URL)
                    .put(body).build();
            makeCall(request, callback);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String prepareBookResponse(final List<BookModel> books) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        final JSONArray jsonArray = new JSONArray();
        for (final BookModel book : books) {
            final JSONObject json = new JSONObject();
            json.put("name", book.getName());
            json.put("order", book.getOrder());
            jsonArray.put(json);
        }
        jsonObject.put("books", jsonArray);
        return jsonObject.toString();
    }

    private void makeCall(final Request request, final Callback callback) {
        okHttpClient.newCall(request).enqueue(callback);
    }
}

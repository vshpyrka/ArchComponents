package com.inveritasoft.archcomponents.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.inveritasoft.archcomponents.App;
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

    public static final String API_URL = "http://192.168.88.188:9000";

    private static final String SIGNUP_URL = API_URL + "/signup";

    private static final String GET_BOOK_URL = API_URL + "/books";

    private static final String UPDATE_BOOKS_URL = API_URL + "/books";

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static ApiGatewayImpl instance;

    private OkHttpClient okHttpClient;

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
                                .addHeader("Content-type", "application/json")
                                .build();
                        return chain.proceed(authorized);
                    }
                })
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        final Request original = chain.request();
                        if (sharedPreferences.contains(Keys.COOKIE)) {
                            final Request withCookies = original.newBuilder()
                                    .addHeader("Cookies", sharedPreferences.getString(Keys.COOKIE, null))
                                    .build();
                            return chain.proceed(withCookies);
                        }
                        return chain.proceed(original);
                    }
                })
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        final Request original = chain.request();
                        Response response = chain.proceed(original);
                        if (original.url().toString().equals(SIGNUP_URL)) {
                            sharedPreferences.edit().putString(Keys.COOKIE, response.headers().get("Set-Cookie")).apply();
                        }
                        return response;
                    }
                })
                .build();
    }

    @Override
    public void doLogin(String userName, Callback callback) {
        if (sharedPreferences.contains(Keys.PUSH_TOKEN)) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("name", userName);
                jsonObject.put("push_token", sharedPreferences.getString(Keys.PUSH_TOKEN, null));
                RequestBody body = RequestBody.create(JSON, jsonObject.toString());
                final Request request = new Request.Builder()
                        .url(SIGNUP_URL)
                        .post(body)
                        .build();
                makeCall(request, callback);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.d(TAG, "doLogin: no Token");
        }
    }

    @Override
    public void getBooks(final int userId, Callback callback) {
        final Request request = new Request.Builder()
                .url(GET_BOOK_URL + "/" + userId)
                .get()
                .build();
        makeCall(request, callback);
    }

    @Override
    public void updateBooks(final int userId, final List<BookModel> books, final Callback callback) {
        try {
            RequestBody body = RequestBody.create(JSON, prepareBookResponse(books));
            final Request request = new Request.Builder()
                    .url(UPDATE_BOOKS_URL + "/" + userId)
                    .addHeader("Content-type", "application/json")
                    .put(body).build();
            makeCall(request, callback);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String prepareBookResponse(final List<BookModel> books) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (final BookModel book : books) {
            JSONObject json = new JSONObject();
            json.put("name", book.getName());
            json.put("order", book.getOrder());
            jsonArray.put(json);
        }
        jsonObject.put("books", jsonArray);
        return jsonObject.toString();
    }

    private void makeCall(Request request, Callback callback) {
        okHttpClient.newCall(request).enqueue(callback);
    }
}

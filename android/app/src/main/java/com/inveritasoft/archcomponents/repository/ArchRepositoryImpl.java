package com.inveritasoft.archcomponents.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.inveritasoft.archcomponents.App;
import com.inveritasoft.archcomponents.AppExecutors;
import com.inveritasoft.archcomponents.api.ApiGateway;
import com.inveritasoft.archcomponents.api.model.Book;
import com.inveritasoft.archcomponents.api.model.BookList;
import com.inveritasoft.archcomponents.db.AbstractAppDatabase;
import com.inveritasoft.archcomponents.db.entities.BookEntity;
import com.inveritasoft.archcomponents.presentation.main.adapter.BookModel;
import com.inveritasoft.archcomponents.presentation.main.utils.Keys;

import org.json.JSONException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Oleksandr Kryvoruchko on 24.04.2018.
 */
public class ArchRepositoryImpl implements ArchRepository {

    private static final String TAG = "ArchRepositoryImpl";

    private static ArchRepositoryImpl sInstance;

    private final AbstractAppDatabase roomDatabase;

    private final ApiGateway apiGateway;

    private AppExecutors appExecutors;

    private MediatorLiveData<Boolean> isLoggedIn;

    private SharedPreferences sharedPreferences;

    private ArchRepositoryImpl(final AbstractAppDatabase roomDatabase, final AppExecutors appExecutors, final ApiGateway apiGateway) {
        this.appExecutors = appExecutors;
        this.roomDatabase = roomDatabase;
        this.apiGateway = apiGateway;
        isLoggedIn = new MediatorLiveData<>();
        sharedPreferences = App.getInstance().getApplicationContext().getSharedPreferences(Keys.SHARED_PREFS_KEY, Context.MODE_PRIVATE);
    }

    public static ArchRepositoryImpl getInstance(final AbstractAppDatabase database, AppExecutors appExecutors, ApiGateway apiGateway) {
        if (sInstance == null) {
            synchronized (ArchRepositoryImpl.class) {
                if (sInstance == null) {
                    sInstance = new ArchRepositoryImpl(database, appExecutors, apiGateway);
                }
            }
        }
        return sInstance;
    }

    @Override
    public void doApiLogin(String user) {
        apiGateway.doLogin(user, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    isLoggedIn.postValue(true);
                    String body = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(body);
                        int userId = jsonObject.getInt("user_id");
                        sharedPreferences.edit().putInt(Keys.USER_ID, userId).apply();
                        sharedPreferences.edit().putBoolean(Keys.IS_LOGGED_IN, true).apply();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void getBooksFromApi() {
        int userId = sharedPreferences.getInt(Keys.USER_ID, -1);
        apiGateway.getBooks(userId, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                List<Book> books = parseFromJson(response.body().string());
                insertBooksToDb(books);
                Log.d(TAG, "onResponse: ");
            }
        });
    }

    private Object fromJson(String jsonString, Type type) {
        return new Gson().fromJson(jsonString, type);
    }

    private List<Book> parseFromJson(String json) {
        BookList books = (BookList) fromJson(json, BookList.class);
        return books.getBooks();
    }

    @Override
    public LiveData<List<BookEntity>> getBooks() {
        return roomDatabase.bookDao().getBooks();
    }

    public void insertBooksToDb(List<Book> books) {
        Type listType = new TypeToken<List<BookEntity>>() {
        }.getType();
        ModelMapper modelMapper = new ModelMapper();
        appExecutors.diskIO().execute(() -> roomDatabase.bookDao().insertBooks(modelMapper.map(books, listType)));
    }

    @Override
    public MediatorLiveData<Boolean> getIsLoggedIn() {
        if (isLoggedIn.getValue() == null && sharedPreferences.contains(Keys.IS_LOGGED_IN)) {
            isLoggedIn.postValue(sharedPreferences.getBoolean(Keys.IS_LOGGED_IN, false));
        }
        return isLoggedIn;
    }

    @Override
    public void updateBooks(final List<BookModel> books) {
        List<BookModel> newBooks = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            BookModel book = books.get(i);
            book.setOrder(i);
            newBooks.add(book);
        }
        int userId = sharedPreferences.getInt(Keys.USER_ID, -1);
        apiGateway.updateBooks(userId, newBooks, new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                Log.e(TAG, "onFailure: ", e);
            }

            @Override
            public void onResponse(final Call call, final Response response) throws IOException {
                Log.d(TAG, "onResponse: ");
            }
        });
    }
}

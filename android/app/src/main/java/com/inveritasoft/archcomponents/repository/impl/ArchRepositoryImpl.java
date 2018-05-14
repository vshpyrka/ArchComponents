package com.inveritasoft.archcomponents.repository.impl;

import android.arch.lifecycle.LiveData;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.inveritasoft.archcomponents.AppExecutors;
import com.inveritasoft.archcomponents.api.ApiGateway;
import com.inveritasoft.archcomponents.api.model.Book;
import com.inveritasoft.archcomponents.api.model.BookList;
import com.inveritasoft.archcomponents.db.AbstractAppDatabase;
import com.inveritasoft.archcomponents.db.entities.BookEntity;
import com.inveritasoft.archcomponents.presentation.main.adapter.BookModel;
import com.inveritasoft.archcomponents.presentation.main.utils.Keys;
import com.inveritasoft.archcomponents.repository.ArchRepository;
import com.inveritasoft.archcomponents.repository.LoginCallback;

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

    private final AbstractAppDatabase roomDatabase;

    private final ApiGateway apiGateway;

    private final AppExecutors appExecutors;

    private final SharedPreferences sharedPreferences;

    public ArchRepositoryImpl(final AbstractAppDatabase roomDatabase,
                              final AppExecutors appExecutors,
                              final ApiGateway apiGateway,
                              final SharedPreferences sharedPreferences) {
        this.appExecutors = appExecutors;
        this.roomDatabase = roomDatabase;
        this.apiGateway = apiGateway;
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public boolean isLoggedIn() {
        return sharedPreferences.contains(Keys.COOKIE);
    }

    @Override
    public void login(final String user, final LoginCallback callback) {
        apiGateway.login(user, new Callback() {

            @Override
            public void onResponse(@NonNull final Call call, @NonNull final Response response) throws IOException {
                if (response.isSuccessful()) {
                    callback.onSuccess();
                }
            }

            @Override
            public void onFailure(@NonNull final Call call, @NonNull final IOException e) {
                callback.onFail(e);
            }
        });
    }

    @Override
    public LiveData<List<BookEntity>> getBooks() {
        getBooksFromApi();
        return roomDatabase.bookDao().getBooks();
    }

    @Override
    public void getBooksFromApi() {
        apiGateway.getBooks(new Callback() {
            @Override
            public void onResponse(@NonNull final Call call, @NonNull final Response response) throws IOException {
                final String body = response.body().string();
                Log.d(TAG, "getBooksFromApi onResponse: " + body);
                final List<Book> books = parseFromJson(body);
                if (books != null) {
                    saveBooks(books);
                }
            }

            @Override
            public void onFailure(@NonNull final Call call, @NonNull final IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Nullable
    private List<Book> parseFromJson(final String json) {
        final BookList books = (BookList) fromJson(json, BookList.class);
        return books.getBooks();
    }

    private Object fromJson(final String jsonString, final Type type) {
        return new Gson().fromJson(jsonString, type);
    }

    private void saveBooks(@Nullable final List<Book> books) {
        Type listType = new TypeToken<List<BookEntity>>() {
        }.getType();
        final ModelMapper modelMapper = new ModelMapper();
        appExecutors.diskIO().execute(() -> roomDatabase.bookDao().insertBooks(modelMapper.map(books, listType)));
    }

    @Override
    public void updateBooks(final List<BookModel> books) {
        final List<BookModel> newBooks = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            final BookModel book = books.get(i);
            book.setOrder(i);
            newBooks.add(book);
        }
        apiGateway.updateBooks(newBooks, new Callback() {
            @Override
            public void onFailure(@NonNull final Call call, @NonNull final IOException e) {
                Log.e(TAG, "onFailure: ", e);
            }

            @Override
            public void onResponse(@NonNull final Call call, @NonNull final Response response) throws IOException {
                Log.d(TAG, "onResponse: ");
            }
        });
    }
}

package com.inveritasoft.archcomponents.repository;

import android.arch.lifecycle.MediatorLiveData;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.inveritasoft.archcomponents.App;
import com.inveritasoft.archcomponents.AppExecutors;
import com.inveritasoft.archcomponents.api.ApiGateway;
import com.inveritasoft.archcomponents.api.model.CategoriesList;
import com.inveritasoft.archcomponents.api.model.Category;
import com.inveritasoft.archcomponents.db.AbstractAppDatabase;
import com.inveritasoft.archcomponents.db.entities.BookEntity;
import com.inveritasoft.archcomponents.db.entities.CategoryEntity;
import com.inveritasoft.archcomponents.db.entities.CategoryWithBooks;
import com.inveritasoft.archcomponents.presentation.main.utils.Keys;

import org.json.JSONException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Oleksandr Kryvoruchko on 24.04.2018.
 */
public class ArchRepositoryImpl implements ArchRepository {

    private static ArchRepositoryImpl sInstance;

    private final AbstractAppDatabase roomDatabase;

    private final ApiGateway apiGateway;

    private AppExecutors appExecutors;

    private MediatorLiveData<List<CategoryWithBooks>> observableCategory;

    private MediatorLiveData<Boolean> isLoggedIn;

    private SharedPreferences sharedPreferences;

    private ArchRepositoryImpl(final AbstractAppDatabase roomDatabase, final AppExecutors appExecutors, final ApiGateway apiGateway) {
        this.appExecutors = appExecutors;
        this.roomDatabase = roomDatabase;
        this.apiGateway = apiGateway;
        observableCategory = new MediatorLiveData<>();
        isLoggedIn = new MediatorLiveData<>();
        sharedPreferences = App.getInstance().getApplicationContext().getSharedPreferences(Keys.SHARED_PREFS_KEY, Context.MODE_PRIVATE);
        observableCategory.addSource(roomDatabase.categoryDao().getCategories(),
                productEntities -> {
                    if (roomDatabase.getIsDatabaseCreated().getValue() != null) {
                        observableCategory.postValue(productEntities);
                    }
                });
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

            }
        });
    }

    public static Object fromJson(String jsonString, Type type) {
        return new Gson().fromJson(jsonString, type);
    }

    private List<Category> parseFromJson(String json) {
        CategoriesList categories = (CategoriesList) fromJson(json, CategoriesList.class);
        return categories.getCategories();
    }

    @Override
    public MediatorLiveData<List<CategoryWithBooks>> getCategoriesWithBooks() {
        return observableCategory;
    }

    public void insertCategoriesToDb(List<Category> categories) {
        Type listType = new TypeToken<List<BookEntity>>() {
        }.getType();
        ModelMapper modelMapper = new ModelMapper();
        for (Category category : categories) {
            appExecutors.diskIO().execute(() -> {
                roomDatabase.categoryDao().replaceCategory(modelMapper.map(category, CategoryEntity.class));
                roomDatabase.bookDao().instertBooks(modelMapper.map(category.getBooks(), listType));
            });
        }
    }

    @Override
    public MediatorLiveData<Boolean> getIsLoggedIn() {
        if (isLoggedIn.getValue() == null && sharedPreferences.contains(Keys.IS_LOGGED_IN)) {
            isLoggedIn.postValue(sharedPreferences.getBoolean(Keys.IS_LOGGED_IN, false));
        }
        return isLoggedIn;
    }
}

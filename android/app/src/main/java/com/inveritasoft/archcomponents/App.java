package com.inveritasoft.archcomponents;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.inveritasoft.archcomponents.api.ApiGateway;
import com.inveritasoft.archcomponents.api.ApiGatewayImpl;
import com.inveritasoft.archcomponents.db.AbstractAppDatabase;
import com.inveritasoft.archcomponents.presentation.main.utils.Keys;
import com.inveritasoft.archcomponents.repository.ArchRepository;
import com.inveritasoft.archcomponents.repository.impl.ArchRepositoryImpl;

/**
 * Created by Oleksandr Kryvoruchko on 23.04.2018.
 */
public class App extends Application {

    private static volatile App instance;

    private AppExecutors appExecutors;
    private AbstractAppDatabase database;
    private SharedPreferences sharedPreferences;
    private ApiGatewayImpl apiGateway;
    private ArchRepositoryImpl archRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        App.setInstance(this);
        appExecutors = new AppExecutors();
    }

    /**
     * Set static application instance.
     *
     * @param instance Application instance
     */
    public static void setInstance(final App instance) {
        App.instance = instance;
    }

    /**
     * Instance of Application.
     *
     * @return App.instance.
     */
    public static App getInstance() {
        return instance;
    }

    /**
     * Provide application database instance.
     *
     * @return Database instance
     */
    public AbstractAppDatabase getDatabase() {
        if (database == null) {
            database = AbstractAppDatabase.getInstance(this);
        }
        return database;
    }

    /**
     * Provide application shared preferences.
     *
     * @return Instance of app shared prefs.
     */
    public SharedPreferences getSharedPrefs() {
        if (sharedPreferences == null) {
            sharedPreferences = App.getInstance().getApplicationContext().getSharedPreferences(Keys.SHARED_PREFS_KEY,
                    Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    /**
     * Provide application API gateway instance.
     *
     * @return API gateway instance
     */
    public ApiGateway getApiGateway() {
        if (apiGateway == null) {
            apiGateway = new ApiGatewayImpl(getSharedPrefs());
        }
        return apiGateway;
    }

    /**
     * Provide application repository instance.
     *
     * @return Repository instance
     */
    public ArchRepository getRepository() {
        if (archRepository == null) {
            archRepository = new ArchRepositoryImpl(getDatabase(), appExecutors, getApiGateway(), getSharedPrefs());
        }
        return archRepository;
    }
}

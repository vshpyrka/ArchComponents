package com.inveritasoft.archcomponents;

import android.app.Application;

import com.inveritasoft.archcomponents.api.ApiGateway;
import com.inveritasoft.archcomponents.api.ApiGatewayImpl;
import com.inveritasoft.archcomponents.db.AbstractAppDatabase;
import com.inveritasoft.archcomponents.repository.ArchRepository;
import com.inveritasoft.archcomponents.repository.ArchRepositoryImpl;

/**
 * Created by Oleksandr Kryvoruchko on 23.04.2018.
 */
public class App extends Application {

    private AppExecutors appExecutors;

    private static volatile App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        App.setInstance(this);
        appExecutors = new AppExecutors();
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
        return AbstractAppDatabase.getInstance(this);
    }

    /**
     * Provide application repository instance.
     *
     * @return Repository instance
     */
    public ArchRepository getRepository() {
        return ArchRepositoryImpl.getInstance(getDatabase(), appExecutors, getApiGateway());
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
     * Provide application API gateway instance.
     *
     * @return API gateway instance
     */
    public ApiGateway getApiGateway() {
        return ApiGatewayImpl.getInstance();
    }
}

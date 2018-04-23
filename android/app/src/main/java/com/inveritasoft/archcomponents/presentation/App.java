package com.inveritasoft.archcomponents.presentation;

import android.app.Application;
import android.content.Context;

import com.google.firebase.iid.FirebaseInstanceId;
import com.inveritasoft.archcomponents.presentation.main.utils.Keys;

/**
 * Created by Oleksandr Kryvoruchko on 23.04.2018.
 */
public class App extends Application {

    private static volatile App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        App.setInstance(this);
        savePushToken();
    }

    private void savePushToken() {
        String pushToken = FirebaseInstanceId.getInstance().getToken();
        if (!(pushToken != null && pushToken.isEmpty())) {
            getSharedPreferences(Keys.SHARED_PREFS_KEY, MODE_PRIVATE).edit().putString(Keys.PUSH_TOKEN, pushToken).apply();
        }
    }

    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
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
}

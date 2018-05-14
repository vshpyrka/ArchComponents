package com.inveritasoft.archcomponents.firebase;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.inveritasoft.archcomponents.App;
import com.inveritasoft.archcomponents.presentation.main.utils.Keys;

/**
 * Wrapper for Firebase service, that refreshing token.
 */
public class ArchFirebaseIDService extends FirebaseInstanceIdService {

    private static final String TAG = "BchatFirebaseIDService";

    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        sharedPreferences = App.getInstance().getSharedPrefs();
    }

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        Log.d(TAG, "onTokenRefresh");
        final String token = FirebaseInstanceId.getInstance().getToken();
        sharedPreferences.edit().putString(Keys.PUSH_TOKEN, token).apply();
    }
}

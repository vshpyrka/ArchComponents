package com.inveritasoft.archcomponents.firebase;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Oleksandr Kryvoruchko on 23.04.2018.
 */
public class ArchFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}

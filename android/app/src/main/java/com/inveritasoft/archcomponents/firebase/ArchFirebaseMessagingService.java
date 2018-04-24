package com.inveritasoft.archcomponents.firebase;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.inveritasoft.archcomponents.App;
import com.inveritasoft.archcomponents.repository.ArchRepository;

/**
 * Created by Oleksandr Kryvoruchko on 23.04.2018.
 */
public class ArchFirebaseMessagingService extends FirebaseMessagingService {

    ArchRepository repository = App.getInstance().getRepository();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);


    }


}

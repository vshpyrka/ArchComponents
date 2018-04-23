package service.impl;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.typesafe.config.Config;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import repositories.Repository;
import service.PushNotificationService;

public class PushNotificationServiceImpl implements PushNotificationService {

    private static final String FCM_PUSH_SERVICE = "https://fcm.googleapis.com/fcm/send";

    private final Config config;
    private final WSClient wsClient;
    private final Repository repository;

    public PushNotificationServiceImpl(final Config config,
                                       final WSClient wsClient,
                                       final Repository repository) {
        this.config = config;
        this.wsClient = wsClient;
        this.repository = repository;
    }

    @Override
    public void notifyUser(final long userId) {
        final String pushToken = repository.getUserPushToken(userId);
        if (pushToken == null || pushToken.isEmpty()) {
            return;
        }

        final WSRequest request = wsClient.url(FCM_PUSH_SERVICE);
        final String serverApiKey = config.getString("firebase.server_api_key");
        request.addHeader("Authorization", String.format("key=%s", serverApiKey));
        request.addHeader("Content-type", "application/json");

        final ObjectNode body = Json.newObject();
        body.put("to", pushToken);
        body.put("priority", "high");

        request.post(body.toString());
    }

}

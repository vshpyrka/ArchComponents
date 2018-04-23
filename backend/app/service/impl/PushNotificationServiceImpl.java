package service.impl;

import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import repositories.Repository;
import service.PushNotificationService;

public class PushNotificationServiceImpl implements PushNotificationService {

    private final WSClient wsClient;
    private final Repository repository;

    public PushNotificationServiceImpl(final WSClient wsClient, final Repository repository) {
        this.wsClient = wsClient;
        this.repository = repository;
    }

    @Override
    public void notifyUser(final long userId) {
        String pushToken = repository.getUserPushToken(userId);
        if (pushToken == null || pushToken.isEmpty()) {
            return;
        }
        final WSRequest request = wsClient.url("https://fcm.googleapis.com/fcm/send")
                .addHeader("Content-type", "application/json")
                .addHeader("Authorization", "key=AIzaSyBSxxxxsXevRq0trDbA9mhnY_2jqMoeChA");
        final ObjectNode message = Json.newObject();
        message.put("to", "dBbB2BFT-VY:APA91bHrvgfXbZa-K5eg9vVdUkIsHbMxxxxxc8dBAvoH_3ZtaahVVeMXP7Bm0iera5s37ChHmAVh29P8aAVa8HF0I0goZKPYdGT6lNl4MXN0na7xbmvF25c4ZLl0JkCDm_saXb51Vrte");
        message.put("priority", "high");

        final ObjectNode notification = Json.newObject();
        notification.put("title", "Java");
        notification.put("body", "Notification");

        message.set("notification", notification);

        request.post(message);
    }
}

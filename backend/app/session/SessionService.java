package session;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Singleton;
import play.libs.Json;
import play.mvc.Http;

@Singleton
public class SessionService {

    private static String SESSION_MODEL = "sessionModel";

    public SessionModel getSessionModel(final Http.Session session) {
        final String model = session.get(SESSION_MODEL);
        if (model != null) {
            final JsonNode json = Json.parse(model);
            return Json.fromJson(json, SessionModel.class);
        } else {
            return new SessionModel();
        }
    }

    public void setSessionModel(final Http.Session session, final SessionModel sessionModel) {
        String model = null;
        try {
            model = new ObjectMapper().writeValueAsString(Json.toJson(sessionModel));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (model == null) {
            throw new RuntimeException("unable to parse json");
        }
        session.put(SESSION_MODEL, model);
    }
}

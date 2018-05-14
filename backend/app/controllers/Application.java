package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import controllers.actions.UserCheckAction;
import models.Book;
import models.BookResult;
import models.ResultStatus;
import models.User;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import service.AppService;
import service.PushNotificationService;
import session.SessionModel;
import session.SessionService;

public class Application extends Controller {

    private final AppService appService;
    private final SessionService sessionService;
    private final PushNotificationService pushService;

    @Inject
    public Application(final AppService appService,
                       final SessionService sessionService,
                       final PushNotificationService pushService) {
        this.appService = appService;
        this.sessionService = sessionService;
        this.pushService = pushService;
    }

    public Result index() {
        return ok();
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result signup() {
        final JsonNode jsonNode = request().body().asJson();
        final User user = Json.fromJson(jsonNode, User.class);
        final Http.Session session = session();
        final SessionModel sessionModel = sessionService.getSessionModel(session);
        sessionModel.userId = appService.singnup(user);
        sessionService.setSessionModel(session, sessionModel);
        return ok(Json.newObject().put("user_id", sessionModel.userId));
    }

    @UserCheckAction.UserCheck
    public Result getBooks() {
        final Http.Session session = session();
        final SessionModel sessionModel = sessionService.getSessionModel(session);
        final BookResult books = appService.getBooks(sessionModel.userId);
        String result;
        try {
            result = new ObjectMapper().writeValueAsString(books);
        } catch (JsonProcessingException e) {
            result = Json.newObject().toString();
        }
        return ok(result);
    }

    @UserCheckAction.UserCheck
    @BodyParser.Of(BodyParser.Json.class)
    public Result addBook() {
        final JsonNode jsonNode = request().body().asJson();
        final Book book = Json.fromJson(jsonNode, Book.class);
        final SessionModel sessionModel = sessionService.getSessionModel(session());
        appService.addBook(sessionModel.userId, book);
        return ok(ResultStatus.getResultStatus(ResultStatus.STATUS_SUCCESS));
    }

    @UserCheckAction.UserCheck
    @BodyParser.Of(BodyParser.Json.class)
    public Result updateBooks() {
        final SessionModel sessionModel = sessionService.getSessionModel(session());
        final JsonNode jsonNode = request().body().asJson();
        final BookResult bookResult = Json.fromJson(jsonNode, BookResult.class);
        final long userId = sessionModel.userId;
        appService.updateBooks(userId, bookResult);
        pushService.notifyUser(userId);
        return ok();
    }
}

package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import controllers.actions.UserCheckAction;
import models.*;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import service.AppService;
import service.PushNotificationService;
import session.SessionModel;
import session.SessionService;

import java.util.List;

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
        final SessionModel sessionModel = sessionService.getSessionModel(session());
        if (sessionModel.userId != -1) {
            if (appService.isUserExists(sessionModel.userId)) {
                return badRequest(ResultStatus.getResultStatus(ResultStatus.STATUS_FAILURE, "User already exists"));
            }
        } else if (appService.isUserExists(user.name)) {
            return badRequest(ResultStatus.getResultStatus(ResultStatus.STATUS_FAILURE, "User with name " + user.name + " already exists"));
        } else {
            sessionModel.userId = appService.singnup(user);
            sessionService.setSessionModel(session(), sessionModel);
        }
        return ok(ResultStatus.getResultStatus(ResultStatus.STATUS_SUCCESS));
    }

    @UserCheckAction.UserCheck()
    public Result getBooks() {
        final SessionModel sessionModel = sessionService.getSessionModel(session());
        final BookResult books = appService.getBooks(sessionModel.userId);
        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(books);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ok(result);
    }

    @UserCheckAction.UserCheck()
    @BodyParser.Of(BodyParser.Json.class)
    public Result addBook() {
        final JsonNode jsonNode = request().body().asJson();
        final Book book = Json.fromJson(jsonNode, Book.class);
        final SessionModel sessionModel = sessionService.getSessionModel(session());
        appService.addBook(sessionModel.userId, book);
        return ok(ResultStatus.getResultStatus(ResultStatus.STATUS_SUCCESS));
    }

    @UserCheckAction.UserCheck()
    public Result getCategories() {
        String result = null;
        try {
            SessionModel sessionModel = sessionService.getSessionModel(session());
            final List<Category> categories = appService.getCategories(sessionModel.userId);
            result = new ObjectMapper().writeValueAsString(categories);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ok(result);
    }

    @UserCheckAction.UserCheck()
    @BodyParser.Of(BodyParser.Json.class)
    public Result addCategory() {
        final JsonNode jsonNode = request().body().asJson();
        final Category category = Json.fromJson(jsonNode, Category.class);
        final SessionModel sessionModel = sessionService.getSessionModel(session());
        appService.addCategory(sessionModel.userId, category);
        return ok(ResultStatus.getResultStatus(ResultStatus.STATUS_SUCCESS));
    }

    public Result updateBooks() {
        final SessionModel sessionModel = sessionService.getSessionModel(session());
        final JsonNode jsonNode = request().body().asJson();
        final BookResult bookResult = Json.fromJson(jsonNode, BookResult.class);
        appService.updateBooks(sessionModel.userId, bookResult);
        pushService.notifyUser(sessionModel.userId);
        return ok();
    }
}

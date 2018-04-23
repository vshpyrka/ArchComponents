package controllers.actions;

import com.google.inject.Inject;
import play.Logger;
import play.libs.Json;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.With;
import service.AppService;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class UserCheckAction {

    @With(AuthorizedAction.class)
    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface UserCheck {
    }

    private static class AuthorizedAction extends Action<UserCheck> {

        private Logger.ALogger logger = Logger.of(getClass());

        private static Result unauthorizedResult = unauthorized(Json.toJson("access not authorized"));

        @Inject
        AppService appService;

        @Override
        public CompletionStage<Result> call(final Http.Context ctx) {
            final long userId = (long) ctx.args.get("userId");
            logger.debug("Attempt to login with userId " + userId);
            if (appService.isUserExists(userId)) {
                return delegate.call(ctx);
            } else {
                return CompletableFuture.completedFuture(unauthorizedResult);
            }
        }
    }
}

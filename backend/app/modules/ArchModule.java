package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import play.Logger;
import play.db.Database;
import play.libs.ws.WSClient;
import repositories.Repository;
import repositories.impl.RepositoryImpl;
import service.AppService;
import service.PushNotificationService;
import service.impl.AppServiceImpl;
import service.impl.PushNotificationServiceImpl;

public class ArchModule extends AbstractModule {

    private final Logger.ALogger logger = Logger.of(getClass());

    @Override
    protected void configure() {
        logger.info("Startup of Arch backend.");

        bind(JsonMapperModule.class).asEagerSingleton();
    }

    @Provides
    public DSLContext gePSQLContext(final Database db) {
        return DSL.using(db.getDataSource(), SQLDialect.POSTGRES);
    }

    @Provides
    @Singleton
    public Repository provideRepository(final DSLContext dslContext) {
        return new RepositoryImpl(dslContext);
    }

    @Provides
    @Singleton
    public AppService provideAuthService(final Repository repository) {
        return new AppServiceImpl(repository);
    }

    @Provides
    @Singleton
    public PushNotificationService providePushService(final WSClient wsClient, final Repository repository) {
        return new PushNotificationServiceImpl(wsClient, repository);
    }
}

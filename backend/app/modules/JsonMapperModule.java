package modules;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.inject.Singleton;
import play.libs.Json;

@Singleton
public class JsonMapperModule {

    public JsonMapperModule() {
        Json.setObjectMapper(new ObjectMapper()
                .registerModules(new Jdk8Module(), new JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        );
    }
}

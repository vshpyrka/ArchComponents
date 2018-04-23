package db.jooq;

import org.jooq.util.DefaultGeneratorStrategy;
import org.jooq.util.Definition;

public class JooqNamingStrategy extends DefaultGeneratorStrategy {
    @Override
    public String getJavaIdentifier(Definition definition) {

        String defaultName = super.getJavaIdentifier(definition);

        if (defaultName.matches("^arch_.*")) {
            return defaultName.substring(4);
        }

        return defaultName;
    }

    @Override
    public String getJavaClassName(Definition definition, Mode mode) {
        String defaultName = super.getJavaClassName(definition, mode);

        if (defaultName.matches("^arch.*")) {
            return defaultName.substring(3);
        }

        return defaultName;
    }
}

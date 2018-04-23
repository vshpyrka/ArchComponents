package db.jooq;


import com.fasterxml.jackson.databind.JsonNode;
import org.jooq.*;
import org.jooq.impl.DSL;
import play.libs.Json;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Types;
import java.util.Objects;

public class JsonBinder implements Binding<Object, JsonNode> {

    @Override
    public Converter<Object, JsonNode> converter() {
        return new Converter<Object, JsonNode>() {
            @Override
            public JsonNode from(Object t) {
                return t == null ? Json.newObject() : Json.parse("" + t);
            }

            @Override
            public Object to(JsonNode u) {
                return u == null ? null : Json.stringify(u);
            }

            @Override
            public Class<Object> fromType() {
                return Object.class;
            }

            @Override
            public Class<JsonNode> toType() {
                return JsonNode.class;
            }
        };
    }

    @Override
    public void sql(final BindingSQLContext<JsonNode> ctx) throws SQLException {
        ctx.render().visit(DSL.val(ctx.convert(converter()).value())).sql("::json");
    }

    @Override
    public void register(final BindingRegisterContext<JsonNode> ctx) throws SQLException {
        ctx.statement()
                .registerOutParameter(ctx.index(),
                        Types.VARCHAR);
    }

    @Override
    public void set(final BindingSetStatementContext<JsonNode> ctx) throws SQLException {
        ctx.statement()
                .setString(ctx.index(),
                        Objects.toString(ctx.convert(converter()).value(),
                                null));
    }

    @Override
    public void set(final BindingSetSQLOutputContext<JsonNode> ctx) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public void get(final BindingGetResultSetContext<JsonNode> ctx) throws SQLException {
        ctx.convert(converter()).value(ctx.resultSet().getString(ctx.index()));
    }

    @Override
    public void get(final BindingGetStatementContext<JsonNode> ctx) throws SQLException {
        ctx.convert(converter()).value(ctx.statement().getString(ctx.index()));
    }

    @Override
    public void get(final BindingGetSQLInputContext<JsonNode> ctx) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
}
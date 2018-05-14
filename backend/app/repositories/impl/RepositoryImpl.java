package repositories.impl;

import db.jooq.arch.tables.ArchBooks;
import db.jooq.arch.tables.ArchUsers;
import db.jooq.arch.tables.records.ArchBooksRecord;
import db.jooq.arch.tables.records.ArchUsersRecord;
import models.Book;
import org.jooq.DSLContext;
import repositories.Repository;

import java.util.List;

public class RepositoryImpl implements Repository {

    private final DSLContext dslContext;

    public RepositoryImpl(final DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public long createUser(final String name, final String pushToken) {
        return dslContext.insertInto(ArchUsers.ARCH_USERS, ArchUsers.ARCH_USERS.NAME, ArchUsers.ARCH_USERS.PUSH_TOKEN)
                .values(name, pushToken)
                .returning(ArchUsers.ARCH_USERS.ID)
                .fetchOne()
                .getId();
    }

    @Override
    public boolean isUserExists(final long userId) {
        return dslContext.fetchExists(dslContext.select()
                .from(ArchUsers.ARCH_USERS)
                .where(ArchUsers.ARCH_USERS.ID.eq((int) userId)));
    }

    @Override
    public boolean isUserExists(final String userName) {
        return dslContext.fetchExists(dslContext.select()
                .from(ArchUsers.ARCH_USERS)
                .where(ArchUsers.ARCH_USERS.NAME.eq(userName)));
    }

    @Override
    public ArchUsersRecord getUserByName(final String name) {
        return dslContext.selectFrom(ArchUsers.ARCH_USERS)
                .where(ArchUsers.ARCH_USERS.NAME.eq(name))
                .fetchOne();
    }

    @Override
    public void updatePushToken(final String name, final String pushToken) {
        dslContext.update(ArchUsers.ARCH_USERS)
                .set(ArchUsers.ARCH_USERS.PUSH_TOKEN, pushToken)
                .where(ArchUsers.ARCH_USERS.NAME.eq(name)).execute();
    }

    @Override
    public String getUserPushToken(final long userId) {
        return dslContext.select(ArchUsers.ARCH_USERS.PUSH_TOKEN)
                .from(ArchUsers.ARCH_USERS)
                .where(ArchUsers.ARCH_USERS.ID.eq((int) userId)).fetchOne().value1();
    }

    @Override
    public List<ArchBooksRecord> getBooks(final long userId) {
        return dslContext.selectFrom(ArchBooks.ARCH_BOOKS)
                .where(ArchBooks.ARCH_BOOKS.USER_ID.eq((int) userId))
                .fetch();
    }

    @Override
    public void addBook(final long userId, final Book book) {
        ArchBooksRecord record = dslContext.newRecord(ArchBooks.ARCH_BOOKS);
        record.setName(book.name);
        record.setBookOrder(book.order);
        record.setUserId((int) userId);
        record.store();
    }

    @Override
    public void deleteUserBooks(final long userId, final long categoryId) {
        dslContext.deleteFrom(ArchBooks.ARCH_BOOKS)
                .where(ArchBooks.ARCH_BOOKS.USER_ID.eq((int) userId));
    }

    @Override
    public void deleteUserBooks(final long userId) {
        dslContext.deleteFrom(ArchBooks.ARCH_BOOKS)
                .where(ArchBooks.ARCH_BOOKS.USER_ID.eq((int) userId)).execute();
    }
}

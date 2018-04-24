package repositories.impl;

import db.jooq.arch.tables.ArchBooks;
import db.jooq.arch.tables.ArchCategories;
import db.jooq.arch.tables.ArchUsers;
import db.jooq.arch.tables.records.ArchBooksRecord;
import db.jooq.arch.tables.records.ArchCategoriesRecord;
import db.models.BookResultRecord;
import models.Book;
import models.BookResult;
import models.Category;
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
    public String getUserPushToken(final long userId) {
        return dslContext.select(ArchUsers.ARCH_USERS.PUSH_TOKEN)
                .from(ArchUsers.ARCH_USERS)
                .where(ArchUsers.ARCH_USERS.ID.eq((int) userId)).fetchOne().value1();
    }

    @Override
    public List<BookResultRecord> getBooks(final long userId) {
        return dslContext.select(
                ArchCategories.ARCH_CATEGORIES.ID.as(BookResultRecord.ALIAS_CATEGORY_ID),
                ArchCategories.ARCH_CATEGORIES.NAME.as(BookResultRecord.ALIAS_CATEGORY_NAME),
                ArchBooks.ARCH_BOOKS.NAME.as(BookResultRecord.ALIAS_BOOK_NAME),
                ArchBooks.ARCH_BOOKS.BOOK_ORDER.as(BookResultRecord.ALIAS_BOOK_ORDER))
                .from(ArchCategories.ARCH_CATEGORIES)
                .innerJoin(ArchBooks.ARCH_BOOKS).on(ArchBooks.ARCH_BOOKS.CATEGORY_ID.eq(ArchCategories.ARCH_CATEGORIES.ID))
                .fetchInto(BookResultRecord.class);
    }

    @Override
    public void addBook(final long userId, final Book book) {
        ArchBooksRecord record = dslContext.newRecord(ArchBooks.ARCH_BOOKS);
        record.setName(book.name);
        record.setBookOrder(book.order);
        record.setCategoryId((int) book.categoryId);
        record.store();
    }

    @Override
    public List<ArchCategoriesRecord> getCategories(final long userId) {
        return dslContext.selectFrom(ArchCategories.ARCH_CATEGORIES)
                .where(ArchCategories.ARCH_CATEGORIES.USER_ID.eq((int) userId)).fetch();
    }

    @Override
    public void addCategory(final long userId, final Category category) {
        ArchCategoriesRecord record = dslContext.newRecord(ArchCategories.ARCH_CATEGORIES);
        record.setName(category.name);
        record.store();
    }

    @Override
    public void deleteCategoryBooks(final long userId, final long categoryId) {
        dslContext.deleteFrom(ArchBooks.ARCH_BOOKS)
                .where(ArchBooks.ARCH_BOOKS.USER_ID.eq((int) userId)
                        .and(ArchBooks.ARCH_BOOKS.CATEGORY_ID.eq((int) categoryId)));
    }
}

package repositories;

import db.jooq.arch.tables.records.ArchBooksRecord;
import db.jooq.arch.tables.records.ArchCategoriesRecord;
import db.jooq.arch.tables.records.ArchUsersRecord;
import models.Book;

import java.util.List;

public interface Repository {

    long createUser(String name, String pushToken);

    boolean isUserExists(long userId);

    boolean isUserExists(String userName);

    void updatePushToken(final String name, String pushToken);

    String getUserPushToken(long userId);

    List<ArchBooksRecord> getBooks(long userId);

    void addBook(long userId, Book book);

    void deleteUserBooks(long userId, long categoryId);

    void deleteUserBooks(long userId);

    ArchUsersRecord getUserByName(String name);
}

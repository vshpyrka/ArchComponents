package repositories;

import db.jooq.arch.tables.records.ArchCategoriesRecord;
import db.models.BookResultRecord;
import models.Book;
import models.Category;

import java.util.List;

public interface Repository {

    long createUser(String name, String pushToken);

    boolean isUserExists(long userId);

    boolean isUserExists(String userName);

    String getUserPushToken(long userId);

    List<BookResultRecord> getBooks(long userId);

    void addBook(long userId, Book book);

    List<ArchCategoriesRecord> getCategories(long userId);

    void addCategory(long userId, Category category);
}

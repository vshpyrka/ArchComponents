package service;

import models.Book;
import models.BookResult;
import models.Category;
import models.User;
import play.mvc.Result;

import java.util.List;

public interface AppService {

    long singnup(final User user);

    boolean isUserExists(long userId);

    boolean isUserExists(String userName);

    BookResult getBooks(long userId);

    void addBook(long userId, Book book);

    List<Category> getCategories(long userId);

    void addCategory(long userId, Category category);

    void updateBooks(final long userId, BookResult bookResult);
}

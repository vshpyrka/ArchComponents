package service;

import models.Book;
import models.BookResult;
import models.User;

import java.util.List;

public interface AppService {

    long singnup(final User user);

    boolean isUserExists(long userId);

    boolean isUserExists(String userName);

    BookResult getBooks(long userId);

    void addBook(long userId, Book book);

    void updateBooks(final long userId, BookResult bookResult);
}

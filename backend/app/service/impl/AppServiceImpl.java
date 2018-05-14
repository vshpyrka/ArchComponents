package service.impl;

import db.jooq.arch.tables.records.ArchBooksRecord;
import db.jooq.arch.tables.records.ArchUsersRecord;
import models.Book;
import models.BookResult;
import models.User;
import repositories.Repository;
import service.AppService;

import java.util.List;

public class AppServiceImpl implements AppService {

    private final Repository repository;

    public AppServiceImpl(final Repository repository) {
        this.repository = repository;
    }

    @Override
    public long singnup(final User user) {
        if (repository.isUserExists(user.name)) {
            if (user.pushToken != null && !user.pushToken.isEmpty()) {
                repository.updatePushToken(user.name, user.pushToken);
            }
            final ArchUsersRecord userRecord = repository.getUserByName(user.name);
            return userRecord.getId();
        } else {
            return repository.createUser(user.name, user.pushToken);
        }
    }

    @Override
    public boolean isUserExists(final long userId) {
        return repository.isUserExists(userId);
    }

    @Override
    public boolean isUserExists(final String userName) {
        return repository.isUserExists(userName);
    }

    @Override
    public BookResult getBooks(final long userId) {
        final BookResult bookResult = new BookResult();
        final List<ArchBooksRecord> books = repository.getBooks(userId);
        books.forEach(bookResultRecord -> {
            final BookResult.Book book = new BookResult.Book();
            book.setName(bookResultRecord.getName());
            book.setOrder(bookResultRecord.getBookOrder());
            bookResult.addBook(book);
        });
        return bookResult;
    }

    @Override
    public void addBook(final long userId, final Book book) {
        repository.addBook(userId, book);
    }

    @Override
    public void updateBooks(final long userId, final BookResult bookResult) {
        repository.deleteUserBooks(userId);
        bookResult.getBooks().forEach(newBook -> {
            final Book book = new Book();
            book.order = newBook.getOrder();
            book.name = newBook.getName();
            addBook(userId, book);
        });
    }
}

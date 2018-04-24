package service.impl;

import db.models.BookResultRecord;
import models.Book;
import models.BookResult;
import models.Category;
import models.User;
import repositories.Repository;
import service.AppService;

import java.util.List;
import java.util.stream.Collectors;

public class AppServiceImpl implements AppService {

    private final Repository repository;

    public AppServiceImpl(final Repository repository) {
        this.repository = repository;
    }

    @Override
    public long singnup(final User user) {
        return repository.createUser(user.name, user.pushToken);
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
        BookResult bookResult = new BookResult();
        final List<BookResultRecord> books = repository.getBooks(userId);
        books.forEach(bookResultRecord -> {

            final long categoryId = bookResultRecord.categoryId;
            BookResult.Category category;
            if (!bookResult.hasCategory(categoryId)) {
                category = new BookResult.Category();
                category.setCategoryId(categoryId);
                category.setCategoryName(bookResultRecord.categoryName);
                bookResult.addCategory(category);
            } else {
                category = bookResult.getCategories(categoryId);
            }
            BookResult.Book book = new BookResult.Book();
            book.setCategoryId(categoryId);
            book.setName(bookResultRecord.bookName);
            book.setOrder(bookResultRecord.bookOrder);
            category.addBook(book);
        });
        return bookResult;
    }

    @Override
    public void addBook(final long userId, final Book book) {
        repository.addBook(userId, book);
    }

    @Override
    public List<Category> getCategories(final long userId) {
        return repository.getCategories(userId).stream().map(archCategoriesRecord -> {
            Category category = new Category();
            category.id = archCategoriesRecord.getId();
            category.name = archCategoriesRecord.getName();
            return category;
        }).collect(Collectors.toList());
    }

    @Override
    public void addCategory(final long userId, final Category category) {
        repository.addCategory(userId, category);
    }

    @Override
    public void updateBooks(final long userId, final BookResult bookResult) {
        final List<BookResult.Category> categories = bookResult.getCategories();
        categories.forEach(category -> {
            int categoryId = (int) category.getCategoryId().longValue();
            repository.deleteCategoryBooks(userId, categoryId);
            category.getBooks().forEach(newBook -> {
                Book book = new Book();
                book.categoryId = categoryId;
                book.name = newBook.getName();
                book.order = newBook.getOrder();
                addBook(userId, book);
            });
        });
    }
}

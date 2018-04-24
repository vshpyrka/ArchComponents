package models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "category"
})
public class BookResult {

    @JsonProperty("categories")
    private List<Category> categories = null;

    @JsonProperty("categories")
    public List<Category> getCategories() {
        return categories;
    }

    @JsonProperty("categories")
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category category) {
        if (this.categories == null) {
            categories = new ArrayList<>();
        }
        categories.add(category);
    }

    public boolean hasCategory(final long categoryId) {
        if (categories == null) {
            return false;
        }
        for (final Category item : categories) {
            if (item.categoryId == categoryId) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public Category getCategories(final long categoryId) {
        if (categories == null) {
            return null;
        }
        for (final Category item : categories) {
            if (item.categoryId == categoryId) {
                return item;
            }
        }
        return null;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "name",
            "order"
    })
    public static class Book {

        @JsonProperty("name")
        private String name;
        @JsonProperty("order")
        private Integer order;
        @JsonProperty("category_id")
        private Long categoryId;

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("order")
        public Integer getOrder() {
            return order;
        }

        @JsonProperty("order")
        public void setOrder(Integer order) {
            this.order = order;
        }

        @JsonProperty("category_id")
        public Long getCategoryId() {
            return categoryId;
        }

        @JsonProperty("category_id")
        public void setCategoryId(final Long categoryId) {
            this.categoryId = categoryId;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "category",
            "books"
    })
    public static class Category {

        @JsonProperty("category_id")
        private Long categoryId;
        @JsonProperty("category_name")
        private String categoryName;
        @JsonProperty("books")
        private List<Book> books = null;

        @JsonProperty("category_id")
        public Long getCategoryId() {
            return categoryId;
        }

        @JsonProperty("category_id")
        public void setCategoryId(final Long categoryId) {
            this.categoryId = categoryId;
        }

        @JsonProperty("category_name")
        public String getCategoryName() {
            return categoryName;
        }

        @JsonProperty("category_name")
        public void setCategoryName(String name) {
            this.categoryName = name;
        }

        @JsonProperty("books")
        public List<Book> getBooks() {
            return books;
        }

        @JsonProperty("books")
        public void setBooks(List<Book> books) {
            this.books = books;
        }

        public void addBook(final Book book) {
            if (books == null) {
                books = new ArrayList<>();
            }
            books.add(book);
        }
    }
}

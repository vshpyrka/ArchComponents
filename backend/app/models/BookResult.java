package models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "books"
})
public class BookResult {

    @JsonProperty("books")
    private List<Book> books = null;

    @JsonProperty("books")
    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        if (books == null) {
            books = new ArrayList<>();
        }
        books.add(book);
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
    }
}

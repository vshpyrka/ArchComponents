package db.models;

import javax.persistence.Column;

public class BookResultRecord {

    public static final String ALIAS_CATEGORY_ID = "category_id";
    public static final String ALIAS_CATEGORY_NAME = "category_name";
    public static final String ALIAS_BOOK_NAME = "book_name";
    public static final String ALIAS_BOOK_ORDER = "book_order";

    @Column(name = ALIAS_CATEGORY_ID)
    public long categoryId;

    @Column(name = ALIAS_CATEGORY_NAME)
    public String categoryName;

    @Column(name = ALIAS_BOOK_NAME)
    public String bookName;

    @Column(name = ALIAS_BOOK_ORDER)
    public int bookOrder;

}

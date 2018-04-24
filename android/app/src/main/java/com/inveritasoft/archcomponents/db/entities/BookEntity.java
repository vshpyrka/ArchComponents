package com.inveritasoft.archcomponents.db.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Oleksandr Kryvoruchko on 23.04.2018.
 */
@Entity(tableName = "tblBooks",
        foreignKeys = @ForeignKey(entity = CategoryEntity.class,
                parentColumns = "categoryId",
                childColumns = "categoryId",
                onDelete = CASCADE))
public class BookEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private Integer bookOrder;
    private Integer categoryId;

    public BookEntity() {
    }

    @Ignore
    public BookEntity(String name, Integer order, Integer categoryId) {
        super();
        this.name = name;
        this.bookOrder = order;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBookOrder() {
        return bookOrder;
    }

    public void setBookOrder(Integer bookOrder) {
        this.bookOrder = bookOrder;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

}

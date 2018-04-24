package com.inveritasoft.archcomponents.db.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Oleksandr Kryvoruchko on 23.04.2018.
 */
@Entity(tableName = "tblBooks")
public class BookEntity {

    private String name;
    @PrimaryKey
    private Integer bookOrder;

    public BookEntity() {
    }

    @Ignore
    public BookEntity(String name, Integer order) {
        super();
        this.name = name;
        this.bookOrder = order;
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

}

package com.inveritasoft.archcomponents.db.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Oleksandr Kryvoruchko on 23.04.2018.
 */
@Entity (foreignKeys = @ForeignKey(entity = CategoryEntity.class,
        parentColumns = "categoryId",
        childColumns = "categoryId",
        onDelete = CASCADE))
public class BookEntity {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private Integer order;
    private Integer categoryId;

    public BookEntity() {
    }

    @Ignore
    public BookEntity(String name, Integer order, Integer categoryId) {
        super();
        this.name = name;
        this.order = order;
        this.categoryId = categoryId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

}

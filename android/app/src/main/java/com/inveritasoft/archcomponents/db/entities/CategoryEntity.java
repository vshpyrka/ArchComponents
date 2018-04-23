package com.inveritasoft.archcomponents.db.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Oleksandr Kryvoruchko on 23.04.2018.
 */
@Entity(tableName = "tblCategory")
public class CategoryEntity {

    @PrimaryKey
    private Integer categoryId;
    private String categoryName;

    public CategoryEntity() {
    }

    @Ignore
    public CategoryEntity(Integer categoryId, String categoryName) {
        super();
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}

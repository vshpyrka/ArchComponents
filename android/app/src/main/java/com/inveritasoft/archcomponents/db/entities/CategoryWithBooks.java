package com.inveritasoft.archcomponents.db.entities;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

/**
 * Created by Oleksandr Kryvoruchko on 24.04.2018.
 */
public class CategoryWithBooks {

    @Embedded
    public CategoryEntity categoryEntity;

    @Relation(parentColumn = "categoryId",
            entityColumn = "categoryId",
            entity = BookEntity.class)
    public List<BookEntity> books;


}

package com.inveritasoft.archcomponents.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.inveritasoft.archcomponents.db.entities.CategoryEntity;
import com.inveritasoft.archcomponents.db.entities.CategoryWithBooks;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Oleksandr Kryvoruchko on 23.04.2018.
 */
@Dao
public interface CategoryDao {

    @Transaction
    @Query("SELECT * FROM tblCategory")
    LiveData<List<CategoryWithBooks>> getCategories();

    @Insert(onConflict = REPLACE)
    void replaceCategory(CategoryEntity categoryEntities);

}

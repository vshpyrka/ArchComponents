package com.inveritasoft.archcomponents.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.inveritasoft.archcomponents.db.entities.BookEntity;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Oleksandr Kryvoruchko on 23.04.2018.
 */
@Dao
public interface BookDao {

    @Insert(onConflict = REPLACE)
    void insertBooks(List<BookEntity> bookEntities);

    @Query("SELECT * FROM tblBooks")
    LiveData<List<BookEntity>> getBooks();
}

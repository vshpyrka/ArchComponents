package com.inveritasoft.archcomponents.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.inveritasoft.archcomponents.db.entities.BookEntity;

import java.util.List;

/**
 * Created by Oleksandr Kryvoruchko on 23.04.2018.
 */
@Dao
public interface BookDao {

    @Insert
    void instertBooks(List<BookEntity> bookEntities);

    @Query("SELECT * FROM tblbooks")
    List<BookEntity> getBooks();
}

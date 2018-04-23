package com.inveritasoft.archcomponents.db.dao;

import android.arch.lifecycle.MediatorLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.inveritasoft.archcomponents.db.entities.CategoryEntity;

import java.util.List;

/**
 * Created by Oleksandr Kryvoruchko on 23.04.2018.
 */
@Dao
public interface CategoryDao {

    @Query("SELECT * FROM tblCallLogs ORDER by timestamp DESC")
    MediatorLiveData<List<CategoryEntity>> getCategories();

}

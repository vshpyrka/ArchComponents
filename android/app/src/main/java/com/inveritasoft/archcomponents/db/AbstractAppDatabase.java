package com.inveritasoft.archcomponents.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.inveritasoft.archcomponents.db.dao.BookDao;
import com.inveritasoft.archcomponents.db.dao.CategoryDao;
import com.inveritasoft.archcomponents.db.entities.BookEntity;
import com.inveritasoft.archcomponents.db.entities.CategoryEntity;

/**
 * Created by Oleksandr Kryvoruchko on 23.04.2018.
 */
@Database(entities = {CategoryEntity.class, BookEntity.class}, version = 1, exportSchema = false)
public abstract class AbstractAppDatabase extends RoomDatabase {

    private static AbstractAppDatabase instance;

    private static final String DATABASE_NAME = "bchat-db";

    public abstract CategoryDao categoryDao();

    public abstract BookDao bookDao();

    private final MutableLiveData<Boolean> isDatabaseCreated = new MutableLiveData<>();

    /**
     * Return instance of AbstractAppDatabase.
     *
     * @param context AppContext.
     * @return AbstractAppDatabase.
     */
    public static AbstractAppDatabase getInstance(final Context context) {
        if (instance == null) {
            synchronized (AbstractAppDatabase.class) {
                if (instance == null) {
                    instance = buildDatabase(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    private static AbstractAppDatabase buildDatabase(final Context appContext) {
        return Room.databaseBuilder(appContext, AbstractAppDatabase.class, DATABASE_NAME).build();
    }

    public LiveData<Boolean> getIsDatabaseCreated() {
        return isDatabaseCreated;
    }
}
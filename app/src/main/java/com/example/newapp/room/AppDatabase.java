package com.example.newapp.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ArticlePhoto.class},
                     version = 5
)
public abstract class AppDatabase extends RoomDatabase {

    private static final String FDB_Name = "first_db";
    private static volatile AppDatabase INSTANCE;

    public abstract ArticleDao getArticleDao();

    public static synchronized AppDatabase getInstance(Context context) {

        if (INSTANCE == null) {
            Builder<AppDatabase> appDatabaseBuilder = Room.databaseBuilder(
                    context,
                    AppDatabase.class, FDB_Name);
            INSTANCE = appDatabaseBuilder.
                    fallbackToDestructiveMigration().
                    allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

}



package com.example.varatiamanagement.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.varatiamanagement.dao.OwnerPropertyDao;
import com.example.varatiamanagement.model.OwnerProperty;


@Database(entities = {OwnerProperty.class}, version = 1, exportSchema = false)
public abstract class OwnerPropertyDatabase extends RoomDatabase {

    private static OwnerPropertyDatabase instance;

    public abstract OwnerPropertyDao getOwnerPropertyDao();

    public static synchronized  OwnerPropertyDatabase getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    OwnerPropertyDatabase.class,"owner-database.db")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }


}

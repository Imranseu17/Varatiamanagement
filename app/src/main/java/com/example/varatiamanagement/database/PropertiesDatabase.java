package com.example.varatiamanagement.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


import com.example.varatiamanagement.dao.PropertiesDao;
import com.example.varatiamanagement.model.OwnerProperty;
import com.example.varatiamanagement.model.Properties;

@Database(entities = {Properties.class}, version = 1, exportSchema = false)
public abstract class PropertiesDatabase extends RoomDatabase {

    private static PropertiesDatabase instance;

    public abstract PropertiesDao getPropertiesDao();

    public static synchronized  PropertiesDatabase getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PropertiesDatabase.class,"properties-database.db")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }
}

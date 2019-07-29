package com.example.varatiamanagement.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.example.varatiamanagement.dao.OwnerPropertyDao;
import com.example.varatiamanagement.enumClass.Type;
import com.example.varatiamanagement.model.OwnerProperty;



@Database(entities = {OwnerProperty.class}, version = 1, exportSchema = false)
@TypeConverters({Type.class})
public abstract class OwnerPropertyDatabase extends RoomDatabase {

    public abstract OwnerPropertyDao getOwnerPropertyDao();
}

package com.example.varatiamanagement.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.example.varatiamanagement.dao.OwnerPropertyDao;
import com.example.varatiamanagement.model.OwnerProperty;
import com.example.varatiamanagement.utils.Converters;


@Database(entities = {OwnerProperty.class}, version = 1, exportSchema = false)
public abstract class OwnerPropertyDatabase extends RoomDatabase {

    public abstract OwnerPropertyDao getOwnerPropertyDao();
}

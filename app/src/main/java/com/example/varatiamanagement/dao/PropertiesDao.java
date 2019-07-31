package com.example.varatiamanagement.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import com.example.varatiamanagement.model.Properties;

import java.util.List;
@Dao
public interface PropertiesDao {

    @Query("SELECT * FROM properties_table")
    LiveData<List<Properties>> getAllProperties();

    @Insert
    void insert(Properties properties);

    @Update
    void update(Properties properties);

    @Delete
    void delete(Properties properties);
}

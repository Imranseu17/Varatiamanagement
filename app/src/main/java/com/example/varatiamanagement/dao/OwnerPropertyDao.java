package com.example.varatiamanagement.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import com.example.varatiamanagement.model.OwnerProperty;
import com.example.varatiamanagement.utils.Converters;


@Dao
@TypeConverters({Converters.class})
public interface OwnerPropertyDao {

    @Insert
    void insert(OwnerProperty property);

    @Update
    void update(OwnerProperty property);

    @Delete
    void delete(OwnerProperty property);
}

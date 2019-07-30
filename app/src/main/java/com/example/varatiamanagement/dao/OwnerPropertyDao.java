package com.example.varatiamanagement.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.varatiamanagement.model.OwnerProperty;

import java.util.List;


@Dao
public interface OwnerPropertyDao {

    @Query("SELECT * FROM ownerProperty_table")
    List<OwnerProperty> getAllOwnerProperties();

    @Insert
    void insert(OwnerProperty property);

    @Update
    void update(OwnerProperty property);

    @Delete
    void delete(OwnerProperty property);
}

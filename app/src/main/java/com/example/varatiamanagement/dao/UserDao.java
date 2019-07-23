package com.example.varatiamanagement.dao;




import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.example.varatiamanagement.model.User;


@Dao
public interface UserDao {

    @Query("SELECT * FROM user_table where phoneNumber= :phoneNumber and password= :password")
    User getUser(String phoneNumber, String password);

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);



}

package com.example.elambarithyr.roomdb;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    void insert(EntityDb entityDb);

    @Query("SELECT * from user_name ORDER BY userName ASC")
    LiveData<List<EntityDb>> getAllUserName();

    @Query("DELETE FROM user_name WHERE userName = :userValue")
    void delete(String userValue);

    @Query("DELETE FROM user_name")
    void deleteAll();

}

package com.games.myapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

@Dao
public interface UserDao {
    @Insert
    long insert(User user);  // Ubah ke long untuk menerima ID yang dihasilkan

    @Query("SELECT * FROM user WHERE id_user = :id")
    User getUserById(int id);

    @Query("DELETE FROM user WHERE id_user = :id")
    void deleteUserById(int id);


    @Update
    void update(User user);

}
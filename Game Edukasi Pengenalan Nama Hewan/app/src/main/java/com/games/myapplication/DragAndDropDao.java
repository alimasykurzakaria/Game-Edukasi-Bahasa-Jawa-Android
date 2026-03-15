package com.games.myapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;


@Dao
public interface DragAndDropDao {
    @Insert
    long insertDnd(DragAndDrop draganddrop);

    @Query("SELECT * FROM draganddrop WHERE id_dnd = :id")
    DragAndDrop getDndById(int id);

    @Query("SELECT * FROM draganddrop ORDER BY skor DESC LIMIT 1")
    DragAndDrop getHighestScore();
}


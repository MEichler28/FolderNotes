package com.example.notice_app.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notice_app.data.entity.Drawing;

import java.util.List;

@Dao
public interface DrawingDao {
    @Query("SELECT * FROM drawing ORDER BY folder_id DESC")
    LiveData<List<Drawing>> getAllDrawings();

    @Insert
    void insert(Drawing... drawings);

    @Update
    void update(Drawing drawing);

    @Delete
    void delete(Drawing drawing);
}

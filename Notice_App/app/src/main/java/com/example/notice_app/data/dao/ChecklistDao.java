package com.example.notice_app.data.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notice_app.data.entity.Checklist;

@Dao
public interface ChecklistDao {
    @Query("SELECT * FROM checklist ORDER BY folder_id DESC")
    LiveData<List<Checklist>> getAllChecklists();

    @Insert
    void insert(Checklist... checklist);

    @Update
    void update(Checklist checklist);

    @Delete
    void delete(Checklist checklist);
}

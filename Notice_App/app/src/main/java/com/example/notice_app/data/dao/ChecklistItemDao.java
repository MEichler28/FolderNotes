package com.example.notice_app.data.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notice_app.data.entity.ChecklistItem;

@Dao
public interface ChecklistItemDao {
    @Query("SELECT * FROM checklistitem ORDER BY folder_id DESC")
    LiveData<List<ChecklistItem>> getAllChecklistItems();

    @Insert
    void insert(ChecklistItem... checklistitem);

    @Update
    void update(ChecklistItem checklistItem);

    @Delete
    void delete(ChecklistItem checklistitem);
}


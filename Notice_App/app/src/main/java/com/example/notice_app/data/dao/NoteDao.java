package com.example.notice_app.data.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notice_app.data.entity.Note;

@Dao
public interface NoteDao{
    @Query("SELECT * FROM Note ORDER BY folder_id DESC")
    LiveData<List<Note>> getAllNotes();

    @Insert
    void insert(Note... note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);
}

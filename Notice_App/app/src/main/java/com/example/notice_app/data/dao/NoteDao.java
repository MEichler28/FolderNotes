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

    @Insert
    long insert(Note note);

    @Query("SELECT COUNT(*) FROM note")
    int countNotes();

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("SELECT * FROM note ORDER BY note_id DESC")
    LiveData<List<Note>> getAllNotes();
}

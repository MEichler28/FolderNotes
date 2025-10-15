package com.example.notice_app.data.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notice_app.data.entity.Audio;

@Dao
public interface AudioDao {
    @Query("SELECT * FROM audio ORDER BY folder_id DESC")
    LiveData<List<Audio>> getAllAudios();

    @Insert
    void insert(Audio... audio);

    @Update
    void update(Audio audio);

    @Delete
    void delete(Audio audio);
}


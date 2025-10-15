package com.example.notice_app.data.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notice_app.data.entity.Picture;

@Dao
public interface PictureDao {


    @Query("SELECT * FROM picture ORDER BY folder_id DESC")
    LiveData<List<Picture>> getAllPictures();

    @Insert
    void insert(Picture... picture);

    @Update
    void update(Picture picture);

    @Delete
    void delete(Picture picture);

}

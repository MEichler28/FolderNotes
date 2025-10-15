package com.example.notice_app.data.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.example.notice_app.data.entity.Folder;

@Dao
public interface FolderDao {
    @Query("SELECT * FROM Folder WHERE parent_folder_id = :parentFolderId")
    LiveData<List<Folder>> getSubfolders(Integer parentFolderId);

    @Query("SELECT * FROM Folder WHERE parent_folder_id IS NULL")
    LiveData<List<Folder>> getRootFolders();


    @Insert
    void insert(Folder... folder);

    @Update
    void update(Folder folder);

    @Delete
    void delete(Folder folder);
}


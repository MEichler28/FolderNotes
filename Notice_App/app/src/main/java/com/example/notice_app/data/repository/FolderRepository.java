package com.example.notice_app.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notice_app.data.dao.FolderDao;
import com.example.notice_app.data.database.AppDatabase;
import com.example.notice_app.data.entity.Folder;

import java.util.List;

public class FolderRepository {

    private final FolderDao folderDao;

    public FolderRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        folderDao = db.folderDao();
    }

    public LiveData<List<Folder>> getRootFolders() {
        return folderDao.getRootFolders();
    }

    public LiveData<List<Folder>> getSubfolders(Integer parentFolderId) {
        return folderDao.getSubfolders(parentFolderId);
    }
}

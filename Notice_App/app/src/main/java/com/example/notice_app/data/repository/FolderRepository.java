package com.example.notice_app.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notice_app.data.dao.FolderDao;
import com.example.notice_app.data.database.AppDatabase;
import com.example.notice_app.data.entity.Folder;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FolderRepository {
    private final FolderDao folderDao;
    private final LiveData<List<Folder>> allFolders;

    // eigener IO-Executor → kein AppDatabase.databaseWriteExecutor nötig
    private final ExecutorService io = Executors.newSingleThreadExecutor();

    public FolderRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
       folderDao = db.folderDao();              // muss exakt so in AppDatabase existieren
        allFolders = folderDao.getSubfolders(null);


    }

    public LiveData<List<Folder>> getAllFolders() {
        return allFolders;
    }

    public void insert(Folder folder) {
        io.execute(() -> folderDao.insert(folder));
    }

    public void update(Folder folder) {
        io.execute(() -> folderDao.update(folder));
    }

    public void delete(Folder folder) {
        io.execute(() -> folderDao.delete(folder));
    }

}

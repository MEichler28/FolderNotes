package com.example.notice_app.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notice_app.data.dao.PictureDao;
import com.example.notice_app.data.database.AppDatabase;
import com.example.notice_app.data.entity.Picture;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PictureRepository {


    private final PictureDao pictureDao;
    private final LiveData<List<Picture>> allPictures;

    // eigener IO-Executor → kein AppDatabase.databaseWriteExecutor nötig
    private final ExecutorService io = Executors.newSingleThreadExecutor();

    public PictureRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        pictureDao = db.pictureDao();              // muss exakt so in AppDatabase existieren
        allPictures = pictureDao.getAllPictures();
    }

    public LiveData<List<Picture>> getAllPictures() {
        return allPictures;
    }

    public void insert(Picture picture) {
        io.execute(() -> pictureDao.insert(picture));
    }

    public void update(Picture picture) {
        io.execute(() -> pictureDao.update(picture));
    }

    public void delete(Picture picture) {
        io.execute(() -> pictureDao.delete(picture));
    }
}

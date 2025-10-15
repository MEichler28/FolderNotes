package com.example.notice_app.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notice_app.data.dao.DrawingDao;
import com.example.notice_app.data.database.AppDatabase;
import com.example.notice_app.data.entity.Drawing;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DrawingRepository {


    private final DrawingDao drawingDao;
    private final LiveData<List<Drawing>> allDrawings;

    // eigener IO-Executor → kein AppDatabase.databaseWriteExecutor nötig
    private final ExecutorService io = Executors.newSingleThreadExecutor();

    public DrawingRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        drawingDao = db.drawingDao();              // muss exakt so in AppDatabase existieren
        allDrawings = drawingDao.getAllDrawings();    // muss exakt so im NoteDao existieren
    }

    public LiveData<List<Drawing>> getAllDrawings() {
        return allDrawings;
    }

    public void insert(Drawing drawing) {
        io.execute(() -> drawingDao.insert(drawing));
    }

    public void update(Drawing drawing) {
        io.execute(() -> drawingDao.update(drawing));
    }

    public void delete(Drawing drawing) {
        io.execute(() -> drawingDao.delete(drawing));
    }
}

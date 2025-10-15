package com.example.notice_app.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notice_app.data.dao.ChecklistDao;
import com.example.notice_app.data.database.AppDatabase;
import com.example.notice_app.data.entity.Checklist;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChecklistRepository {


    private final ChecklistDao checklistDao;
    private final LiveData<List<Checklist>> allChecklists;

    // eigener IO-Executor → kein AppDatabase.databaseWriteExecutor nötig
    private final ExecutorService io = Executors.newSingleThreadExecutor();

    public ChecklistRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        checklistDao = db.checklistDao();              // muss exakt so in AppDatabase existieren
        allChecklists = checklistDao.getAllChecklists();    // muss exakt so im NoteDao existieren
    }

    public LiveData<List<Checklist>> getAllChecklists() {
        return allChecklists;
    }

    public void insert(Checklist checklist) {
        io.execute(() -> checklistDao.insert(checklist));
    }

    public void update(Checklist checklist) {
        io.execute(() -> checklistDao.update(checklist));
    }

    public void delete(Checklist checklist) {
        io.execute(() -> checklistDao.delete(checklist));
    }
}

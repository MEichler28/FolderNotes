package com.example.notice_app.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notice_app.data.dao.ChecklistItemDao;
import com.example.notice_app.data.database.AppDatabase;
import com.example.notice_app.data.entity.ChecklistItem;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChecklistItemRepository {


    private final ChecklistItemDao checklistitemDao;
    private final LiveData<List<ChecklistItem>> allChecklistItems;

    // eigener IO-Executor → kein AppDatabase.databaseWriteExecutor nötig
    private final ExecutorService io = Executors.newSingleThreadExecutor();

    public ChecklistItemRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        checklistitemDao = db.checklistitemDao();              // muss exakt so in AppDatabase existieren
        allChecklistItems = checklistitemDao.getAllChecklistItems();    // muss exakt so im NoteDao existieren
    }

    public LiveData<List<ChecklistItem>> getAllChecklistItems() {
        return allChecklistItems;
    }

    public void insert(ChecklistItem checklistitem) {
        io.execute(() -> checklistitemDao.insert(checklistitem));
    }

    public void update(ChecklistItem checklistitem) {
        io.execute(() -> checklistitemDao.update(checklistitem));
    }

    public void delete(ChecklistItem checklistitem) {
        io.execute(() -> checklistitemDao.delete(checklistitem));
    }
}

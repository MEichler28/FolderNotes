package com.example.notice_app.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notice_app.data.dao.NoteDao;
import com.example.notice_app.data.database.AppDatabase;
import com.example.notice_app.data.entity.Note;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteRepository {

    private final NoteDao noteDao;
    private final LiveData<List<Note>> allNotes;

    // eigener IO-Executor → kein AppDatabase.databaseWriteExecutor nötig
    private final ExecutorService io = Executors.newSingleThreadExecutor();

    public NoteRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        noteDao = db.noteDao();              // muss exakt so in AppDatabase existieren
        allNotes = noteDao.getAllNotes();    // muss exakt so im NoteDao existieren
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public void insert(Note note) {
        io.execute(() -> noteDao.insert(note));
    }

    public void update(Note note) {
        io.execute(() -> noteDao.update(note));
    }

    public void delete(Note note) {
        io.execute(() -> noteDao.delete(note));
    }
}

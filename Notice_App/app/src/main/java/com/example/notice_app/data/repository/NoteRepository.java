package com.example.notice_app.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notice_app.data.dao.NoteDao;
import com.example.notice_app.data.database.AppDatabase;
import com.example.notice_app.data.entity.Note;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class NoteRepository {

    private final NoteDao noteDao;
    private final LiveData<List<Note>> allNotes;

    // eigener IO-Executor → kein AppDatabase.databaseWriteExecutor nötig
    private final ExecutorService io = Executors.newSingleThreadExecutor();

    public NoteRepository(Application application) {
        noteDao = AppDatabase.getDatabase(application).noteDao();
        allNotes = noteDao.getAllNotes();    // muss exakt so im NoteDao existieren
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public void insert(Note note, Consumer<Long> onDone) {
        io.execute(() -> noteDao.insert(note));
        long noteId = noteDao.insert(note);
        if (onDone != null) onDone.accept(noteId);
    }

    public void update(Note note) {
        io.execute(() -> noteDao.update(note));
    }

    public void delete(Note note) {
        io.execute(() -> noteDao.delete(note));
    }
}

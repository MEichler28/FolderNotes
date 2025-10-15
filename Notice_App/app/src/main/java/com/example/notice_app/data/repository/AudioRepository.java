package com.example.notice_app.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notice_app.data.dao.AudioDao;
import com.example.notice_app.data.database.AppDatabase;
import com.example.notice_app.data.entity.Audio;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AudioRepository {


    private final AudioDao audioDao;
    private final LiveData<List<Audio>> allAudios;

    // eigener IO-Executor → kein AppDatabase.databaseWriteExecutor nötig
    private final ExecutorService io = Executors.newSingleThreadExecutor();

    public AudioRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        audioDao = db.audioDao();              // muss exakt so in AppDatabase existieren
        allAudios = audioDao.getAllAudios();    // muss exakt so im NoteDao existieren
    }

    public LiveData<List<Audio>> getAllAudios() {
        return allAudios;
    }

    public void insert(Audio audio) {
        io.execute(() -> audioDao.insert(audio));
    }

    public void update(Audio audio) {
        io.execute(() -> audioDao.update(audio));
    }

    public void delete(Audio audio) {
        io.execute(() -> audioDao.delete(audio));
    }
}

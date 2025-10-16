package com.example.notice_app.ui.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.notice_app.data.entity.Note;
import com.example.notice_app.data.repository.NoteRepository;
import java.util.List;
import java.util.function.Consumer;

public class NoteViewModel extends AndroidViewModel {

    private final NoteRepository repo;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repo = new NoteRepository(application);
    }

    public LiveData<List<Note>> getAllNotes() {
        return repo.getAllNotes();
    }

    public void insert(Note note, Consumer<Long> onDone) {
        repo.insert(note, onDone);
    }

    public void delete(Note note) {
        repo.delete(note);
    }

    public void update(Note note) {
        repo.update(note);
    }
}

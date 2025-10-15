package com.example.notice_app.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.notice_app.data.entity.Folder;
import com.example.notice_app.data.repository.FolderRepository;

import java.util.List;

public class FolderViewModel extends AndroidViewModel {

    private final FolderRepository repo;

    public FolderViewModel(@NonNull Application application) {
        super(application);
        repo = new FolderRepository(application);
    }

    public LiveData<List<Folder>> getRootFolders() {
        return repo.getRootFolders();
    }

    public LiveData<List<Folder>> getSubfolders(Integer parentFolderId) {
        return repo.getSubfolders(parentFolderId);
    }
}

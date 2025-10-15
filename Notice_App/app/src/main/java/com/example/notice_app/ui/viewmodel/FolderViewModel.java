package com.example.notice_app.ui.viewmodel;

import android.app.Application;

import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.notice_app.data.dao.FolderDao;
import com.example.notice_app.data.entity.Folder;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ViewModel für Ordner, abgestimmt auf dein FolderDao:
 *
 *  - Beobachten:  getRootFolders(), getSubfolders(parentId) -> beides LiveData aus Room
 *  - Umschalten zwischen Root und einem Ordner per currentParentId (null = Root)
 *  - Erstellen / Umbenennen / Löschen laufen im IO-Thread
 *
 * Hinweise:
 *  - Passe die Felder an deine Folder-Entity an (name, parent_folder_id etc.).
 *  - Room verlangt DB-Operationen im Hintergrund (außer man erlaubt main thread).
 */
public class FolderViewModel extends AndroidViewModel {

    private final FolderDao folderDao;
    private final ExecutorService io = Executors.newSingleThreadExecutor();

    /** null = Root, ansonsten die ID des Eltern-Ordners */
    private final MutableLiveData<@Nullable Integer> currentParentId = new MutableLiveData<>(null);

    /** Umschalten: wenn parentId null -> Root, sonst -> Subfolder-LiveData */
    private final LiveData<List<Folder>> foldersLive =
            Transformations.switchMap(currentParentId, parentId ->
                    parentId == null
                            ? folderDao.getRootFolders()
                            : folderDao.getSubfolders(parentId)
            );

    public FolderViewModel(Application app) {
        super(app);
        // Hol dir deine DB so, wie du es im Projekt machst:
        AppDatabase db = AppDatabase.getInstance(app);
        folderDao = db.folderDao();
    }

    /** UI beobachtet nur dieses LiveData. */
    public LiveData<List<Folder>> getFolders() {
        return foldersLive;
    }

    /** Root anzeigen. */
    public void openRoot() {
        currentParentId.setValue(null);
    }

    /** Unterordner anzeigen (parentId darf nicht null sein). */
    public void openFolder(int parentId) {
        currentParentId.setValue(parentId);
    }

    // ---------------- Schreib-Operationen ----------------

    /** Neuen Ordner anlegen. parentId = null -> Root-Ordner. */
    public void createFolder(String name, @Nullable Integer parentId) {
        io.execute(() -> {
            Folder f = new Folder();
            // !!! Folgende Feldzuweisungen an DEINE Entity anpassen !!!
            // Beispiel: f.name = name; f.parentFolderId = parentId;
            f.name = (name != null && !name.isEmpty()) ? name : "Neuer Ordner";
            f.parentFolderId = parentId;   // Feldname so in deiner Tabelle? (parent_folder_id)

            folderDao.insert(f);

            // Nach dem Insert Liste neu beobachten (Root oder aktueller Ordner)
            // -> currentParentId bestimmt ja bereits, was beobachtet wird.
            // Kein extra reload nötig, Room-Queries sind LiveData.
        });
    }

    /** Ordner umbenennen (du übergibst das Folder-Objekt, z. B. aus der Liste). */
    public void renameFolder(Folder folder, String newName) {
        if (folder == null) return;
        io.execute(() -> {
            folder.name = (newName != null ? newName : "");
            folderDao.update(folder);
        });
    }

    /** Ordner löschen (Objekt übergeben). */
    public void deleteFolder(Folder folder) {
        if (folder == null) return;
        io.execute(() -> folderDao.delete(folder));
    }
}

package com.example.notice_app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notice_app.R;
import com.example.notice_app.data.entity.Folder;
import com.example.notice_app.ui.NoteEditActivity;
import com.example.notice_app.ui.adapter.TileAdapter;
import com.example.notice_app.model.Tile;
import com.example.notice_app.ui.viewmodel.FolderViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private TileAdapter adapter;
    private final List<Tile> uiData = new ArrayList<>();
    private FolderViewModel vm;

    // Nur falls NoteEditActivity wirklich startet:
    private ActivityResultLauncher<Intent> noteEditorLauncher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            // ActivityResult (optional)
            noteEditorLauncher = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> { /* handle result if needed */ }
            );

            // RecyclerView
            rv = findViewById(R.id.rvGrid); // ID in activity_main.xml prüfen
            if (rv == null) throw new IllegalStateException("RecyclerView mit id 'rvGrid' nicht gefunden");
            rv.setLayoutManager(new GridLayoutManager(this, 2));

            adapter = new TileAdapter();
            rv.setAdapter(adapter);

            // Clicks auf Kacheln
            adapter.setOnTileClick(tile -> {
                switch (tile.type) {
                    case FOLDER:
                        Toast.makeText(this, "Ordner öffnen: " + tile.title, Toast.LENGTH_SHORT).show();
                        // vm.openFolder(folderId) – wenn IDs
                        break;
                    case NOTE:
                        noteEditorLauncher.launch(new Intent(this, NoteEditActivity.class));
                        break;
                    case AUDIO:
                        Toast.makeText(this, "Audio: " + tile.title, Toast.LENGTH_SHORT).show();
                        break;
                    case IMAGE:
                        Toast.makeText(this, "Bild: " + tile.title, Toast.LENGTH_SHORT).show();
                        break;
                    case DRAWING:
                        Toast.makeText(this, "Zeichnung: " + tile.title, Toast.LENGTH_SHORT).show();
                        break;
                    case CHECKLIST:
                        Toast.makeText(this, "Liste: " + tile.title, Toast.LENGTH_SHORT).show();
                        break;
                }
            });

            // Beispiel-Startkacheln (nur UI, unabhängig von DB)
            uiData.clear();
            uiData.add(new Tile(Tile.Type.FOLDER, "Ordner", "", android.R.drawable.ic_menu_agenda));
            uiData.add(new Tile(Tile.Type.NOTE, "Notiz", "Vorschau…", android.R.drawable.ic_menu_edit));
            uiData.add(new Tile(Tile.Type.AUDIO, "Audio", "", android.R.drawable.ic_btn_speak_now));
            uiData.add(new Tile(Tile.Type.IMAGE, "Bild", "", android.R.drawable.ic_menu_gallery));
            uiData.add(new Tile(Tile.Type.DRAWING, "Zeichnung", "", android.R.drawable.ic_menu_crop));
            uiData.add(new Tile(Tile.Type.CHECKLIST, "Checkliste", "", android.R.drawable.ic_menu_manage));
            adapter.submit(new ArrayList<>(uiData)); // oder adapter.setData(...), je nach Adapter

            // ViewModel – WICHTIG: das gehört IN onCreate, nicht darunter!
            vm = new ViewModelProvider(this).get(FolderViewModel.class);

            // DB: Root-Ordner beobachten und in Tiles übersetzen (wenn Ordner angezeigt werden sollen)
            vm.getRootFolders().observe(this, folders -> {
                List<Tile> tiles = new ArrayList<>();
                for (Folder f : folders) {
                    tiles.add(new Tile(
                            Tile.Type.FOLDER,
                            f.folderTitle,           // Feldnamen an Entity anpassen
                            "",
                            android.R.drawable.ic_menu_agenda
                    ));
                }
                adapter.submit(tiles);
            });

            // FAB (optional)
            View fab = findViewById(R.id.insertbtn);
            if (fab != null) {
                fab.setOnClickListener(v -> {
                    final String[] options = {"Ordner", "Notiz", "Audio", "Bild", "Zeichnung", "Liste"};
                    new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this)
                            .setTitle("Neu erstellen")
                            .setItems(options, (dialog, which) -> {
                                Tile t = null;
                                switch (which) {
                                    case 0: t = new Tile(Tile.Type.FOLDER, "Neuer Ordner", "", android.R.drawable.ic_menu_agenda); break;
                                    case 1:
                                        t = new Tile(Tile.Type.NOTE, "Neue Notiz", "", android.R.drawable.ic_menu_edit);
                                        noteEditorLauncher.launch(new Intent(MainActivity.this, NoteEditActivity.class));
                                        break;
                                    case 2: t = new Tile(Tile.Type.AUDIO, "Audio", "", android.R.drawable.ic_btn_speak_now); break;
                                    case 3: t = new Tile(Tile.Type.IMAGE, "Bild", "", android.R.drawable.ic_menu_gallery); break;
                                    case 4: t = new Tile(Tile.Type.DRAWING, "Zeichnung", "", android.R.drawable.ic_menu_crop); break;
                                    case 5: t = new Tile(Tile.Type.CHECKLIST, "Neue Liste", "", android.R.drawable.ic_menu_manage); break;
                                }
                                if (t != null) {
                                    uiData.add(t);
                                    adapter.submit(new ArrayList<>(uiData));
                                }
                            })
                            .show();
                });
            }

        } catch (Throwable e) {
            Log.e("MAIN", "Startfehler", e);
            Toast.makeText(this, e.getClass().getSimpleName() + ": " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}

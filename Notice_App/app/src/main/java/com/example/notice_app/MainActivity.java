package com.example.notice_app;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import data.AppDatabase;

public class MainActivity extends AppCompatActivity {

    private TileAdapter adapter;
    private final List<Tile> data = new ArrayList<>(); // Start: leer

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = findViewById(R.id.rvGrid);
        FloatingActionButton fab = findViewById(R.id.insertbtn);

        // 2 Spalten Portrait (später via Ressourcen anpassen)
        rv.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new TileAdapter();
        rv.setAdapter(adapter);

        // Start: leer
        adapter.submit(data);

        // FAB fügt testweise Kacheln hinzu (nur UI-Demo)
        fab.setOnClickListener(v -> {
            // Beispiel: im Wechsel Ordner / Notiz hinzufügen
            if (data.size() % 2 == 0) {
                data.add(new Tile(
                        Tile.Type.FOLDER,
                        "Ordner " + (data.size()+1),
                        null,
                        R.drawable.outline_folder_24
                ));
            } else {
                data.add(new Tile(
                        Tile.Type.NOTE,
                        "Notiz " + (data.size()+1),
                        "Kurze Vorschau der Notiz …",
                        R.drawable.outline_lab_profile_24
                ));
            }
            adapter.submit(data);
        });
    }

    AppDatabase db = Room.databaseBuilder(getApplicationContext(),
            AppDatabase.class, "notebook").build();
}

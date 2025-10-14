package com.example.notice_app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

// WICHTIG: keine QuickSettings-Tile importieren!
public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private TileAdapter adapter;
    private final java.util.List<Tile> uiData = new java.util.ArrayList<>();
    private androidx.activity.result.ActivityResultLauncher<android.content.Intent> noteEditorLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            // RecyclerView anbinden (ID muss in activity_main.xml existieren)
            rv = findViewById(R.id.rvGrid);
            if (rv == null) throw new IllegalStateException("RecyclerView mit id 'rvGrid' nicht gefunden");

            rv.setLayoutManager(new GridLayoutManager(this, 2));
            adapter = new TileAdapter();
            rv.setAdapter(adapter);

            adapter.setOnTileClick(tile -> {
                switch (tile.type) {
                    case FOLDER:
                        Toast.makeText(this, "Ordner öffnen: " + tile.title, Toast.LENGTH_SHORT).show();
                        // hier später: Unterordner / Inhalte laden
                        break;
                    case NOTE:
                        Toast.makeText(this, "Notiz öffnen: " + tile.title, Toast.LENGTH_SHORT).show();
                        // hier später: Detail-Activity starten
                        break;
                    case AUDIO:
                        Toast.makeText(this, "Audio abspielen: " + tile.title, Toast.LENGTH_SHORT).show();
                        break;
                    case IMAGE:
                        Toast.makeText(this, "Bild anzeigen: " + tile.title, Toast.LENGTH_SHORT).show();
                        break;
                    case DRAWING:
                        Toast.makeText(this, "Zeichnung öffnen: " + tile.title, Toast.LENGTH_SHORT).show();
                        break;
                    case CHECKLIST:
                        Toast.makeText(this, "Liste öffnen: " + tile.title, Toast.LENGTH_SHORT).show();
                        break;
                }
            });

            // FAB anklemmen (falls vorhanden), ohne Dialog – nur Smoke-Test
            View fab = findViewById(R.id.insertbtn);
            if (fab != null) {
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        final String[] options = {"Ordner", "Notiz", "Audio", "Bild", "Zeichnung", "Liste"};

                        new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this)
                                .setTitle("Neu erstellen")
                                .setItems(options, new android.content.DialogInterface.OnClickListener() {
                                    @Override public void onClick(android.content.DialogInterface dialog, int which) {
                                        Tile t;
                                        switch (which) {
                                            case 0: // Ordner
                                                t = new Tile(Tile.Type.FOLDER, "Neuer Ordner", "", android.R.drawable.ic_menu_agenda);
                                                break;

                                            case 1: // Notiz
                                                t = new Tile(Tile.Type.NOTE, "Neue Notiz", "", android.R.drawable.ic_menu_edit);
                                                noteEditorLauncher.launch(new android.content.Intent(MainActivity.this, NoteEditActivity.class));
                                                break;
                                            case 2: // Audio
                                                t = new Tile(Tile.Type.AUDIO, "Audio", "", android.R.drawable.ic_btn_speak_now);
                                                break;
                                            case 3: // Bild (Platzhalter-Icon; später durch Picker ersetzen)
                                                t = new Tile(Tile.Type.NOTE, "Bild", "", android.R.drawable.ic_menu_gallery);
                                                break;
                                            case 4: // Zeichnung
                                                t = new Tile(Tile.Type.NOTE, "Zeichnung", "", android.R.drawable.ic_menu_crop);
                                                break;
                                            case 5: // Liste
                                                t = new Tile(Tile.Type.NOTE, "Neue Liste", "", android.R.drawable.ic_menu_manage);
                                                break;
                                            default:
                                                t = null;
                                        }

                                        if (t != null) {
                                            // sofort in der UI zeigen (uiData ist deine List<Tile> in der Activity)
                                            uiData.add(t);
                                            adapter.submit(new java.util.ArrayList<>(uiData));
                                        }
                                    }
                                })
                                .show();
                    }
                });
            }

        } catch (Throwable e) {
            Log.e("MAIN", "Startfehler", e);
            Toast.makeText(this, e.getClass().getSimpleName() + ": " + String.valueOf(e.getMessage()),
                    Toast.LENGTH_LONG).show();
        }
    }
}

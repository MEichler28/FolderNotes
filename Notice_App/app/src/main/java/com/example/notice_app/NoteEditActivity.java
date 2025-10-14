package com.example.notice_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class NoteEditActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activitx_note_edit);

        EditText etTitle = findViewById(R.id.etTitle);
        EditText etContent = findViewById(R.id.etContent);
        Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            Intent data = new Intent();
            data.putExtra("title",   etTitle.getText().toString());
            data.putExtra("content", etContent.getText().toString());
            setResult(RESULT_OK, data);
            finish();
        });
    }
}

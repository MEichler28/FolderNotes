package com.example.notice_app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import data.AppDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    AppDatabase db = Room.databaseBuilder(getApplicationContext(),
            AppDatabase.class, "notebook").build();
}
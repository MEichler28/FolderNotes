package com.example.notice_app.data.database;


import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.notice_app.data.dao.AudioDao;
import com.example.notice_app.data.dao.ChecklistDao;
import com.example.notice_app.data.dao.ChecklistItemDao;
import com.example.notice_app.data.dao.DrawingDao;
import com.example.notice_app.data.dao.FolderDao;
import com.example.notice_app.data.dao.NoteDao;
import com.example.notice_app.data.dao.PictureDao;
import com.example.notice_app.data.entity.Audio;
import com.example.notice_app.data.entity.Checklist;
import com.example.notice_app.data.entity.ChecklistItem;
import com.example.notice_app.data.entity.Drawing;
import com.example.notice_app.data.entity.Folder;
import com.example.notice_app.data.entity.Note;
import com.example.notice_app.data.entity.Picture;

@Database(entities = {Folder.class, Audio.class, Checklist.class, ChecklistItem.class, Drawing.class, Note.class, Picture.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {


    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Application application) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            application.getApplicationContext(),
                            AppDatabase.class,
                            "notice_database"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract FolderDao folderDao();
    public abstract AudioDao audioDao();
    public abstract ChecklistDao checklistDao();
    public abstract ChecklistItemDao checklistitemDao();
    public abstract DrawingDao drawingDao();
    public abstract NoteDao noteDao();
    public abstract PictureDao pictureDao();
}

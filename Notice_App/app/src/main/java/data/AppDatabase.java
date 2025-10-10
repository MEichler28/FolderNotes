package data;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Folder.class, Audio.class, Checklist.class, ChecklistItem.class, Drawing.class, Note.class, Picture.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FolderDao folderDao();
    public abstract AudioDao audioDao();
    public abstract ChecklistDao checklistDao();
    public abstract ChecklistItemDao checklistitemDao();
    public abstract DrawingDao drawingDao();
    public abstract NoteDao noteDao();
    public abstract PictureDao pictureDao();
}


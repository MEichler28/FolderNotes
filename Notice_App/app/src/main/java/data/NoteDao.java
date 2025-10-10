package data;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface NoteDao{
    @Query("SELECT * FROM note WHERE folder_id = :folderId")
    List<Note> getAll(int folderId);

    @Insert
    void insertAll(Note... notes);

    @Delete
    void delete(Note note);
}

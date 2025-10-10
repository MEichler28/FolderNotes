package data;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ChecklistDao {
    @Query("SELECT * FROM checklist WHERE folder_id = :folderId")
    List<Checklist> getAll(int folderId);

    @Insert
    void insertAll(Checklist... checklists);

    @Delete
    void delete(Checklist checklist);
}

package data;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ChecklistItemDao {
    @Query("SELECT * FROM checklistitem WHERE checklist_id = :checklistId")
    List<ChecklistItem> getAll(int checklistId);

    @Insert
    void insertAll(ChecklistItem... checklistitems);

    @Delete
    void delete(ChecklistItem checklistitem);
}


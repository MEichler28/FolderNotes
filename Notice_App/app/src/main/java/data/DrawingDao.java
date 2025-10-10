package data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DrawingDao {
    @Query("SELECT * FROM drawing WHERE folder_id = :folderId")
    List<Drawing> getAll(int folderId);

    @Insert
    void insertAll(Drawing... drawings);

    @Delete
    void delete(Drawing drawing);
}

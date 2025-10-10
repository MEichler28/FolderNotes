package data;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface AudioDao {
    @Query("SELECT * FROM audio WHERE folder_id = :folderId")
    List<Audio> getAll(int folderId);

    @Insert
    void insertAll(Audio... audios);

    @Delete
    void delete(Audio audio);
}

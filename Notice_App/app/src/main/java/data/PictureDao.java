package data;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface PictureDao {
    @Query("SELECT * FROM picture WHERE folder_id = :folderId")
    List<Picture> getAll(int folderId);

    @Insert
    void insertAll(Picture... pictures);

    @Delete
    void delete(Picture picture);
}

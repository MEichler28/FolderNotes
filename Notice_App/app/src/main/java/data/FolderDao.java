package data;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface FolderDao {
    @Query("SELECT * FROM folder WHERE parent_folder_id = :parentFolderId")
    List<Folder> getAll(int parentFolderId);

    @Insert
    void insertAll(Folder... folders);

    @Delete
    void delete(Folder folder);
}


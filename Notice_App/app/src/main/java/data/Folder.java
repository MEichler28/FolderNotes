package data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "folder",
        indices = @Index("folder_Id")
)
public class Folder {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "folder_id")
    public int folderId;

    @ColumnInfo(name = "folder_title")
    public String folderTitle;

    @ColumnInfo(name = "parent_folder_id")
    Integer parentFolderId;

    @ColumnInfo(name = "created_at")
    public long createdAt;

    @ColumnInfo(name = "updated_at")
    public long updatedAt;
}
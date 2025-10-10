package data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "checklist",
        foreignKeys = @ForeignKey(
                entity = Folder.class,
                parentColumns = "folder_id",
                childColumns = "folder_id",
                onDelete = ForeignKey.CASCADE
        ),
        indices = {@Index("folder_id")}
)
public class Checklist {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "checklist_id")
    public int checklistId;

    @ColumnInfo(name = "folder_id")
    public int folderId;

    @ColumnInfo(name = "checklist_title")
    public String checklistTitle;

    @ColumnInfo(name = "created_at")
    public long createdAt;

    @ColumnInfo(name = "updated_at")
    public long updatedAt;
}


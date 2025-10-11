package data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "note",
        foreignKeys = @ForeignKey(
                entity = Folder.class,
                parentColumns = "folder_id",
                childColumns = "folder_id",
                onDelete = ForeignKey.CASCADE
        ),
        indices = {@Index("folder_id")}
)
public class Note {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    public int noteId;

    @ColumnInfo(name = "folder_id")
    public int folderId;

    @ColumnInfo(name = "note_title")
    public String noteTitle;

    @ColumnInfo(name = "note_content")
    public String noteContent;

    @ColumnInfo(name = "created_at")
    public long createdAt;

    @ColumnInfo(name = "updated_at")
    public long updatedAt;
}

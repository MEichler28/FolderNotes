package data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "drawing",
        foreignKeys = @ForeignKey(
                entity = Folder.class,
                parentColumns = "folder_id",
                childColumns = "folder_id",
                onDelete = ForeignKey.CASCADE
        ),
        indices = {@Index("folder_id")}
)
public class Drawing {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "drawing_id")
    public int drawingId;

    @ColumnInfo(name = "folder_id")
    public int folderId;

    @ColumnInfo(name = "drawing_title")
    public String drawingTitle;

    @ColumnInfo(name = "url")
    public String url;

    @ColumnInfo(name = "format")
    public String format;

    @ColumnInfo(name = "drawing_content")
    public String drawingContent;

    @ColumnInfo(name = "created_at")
    public long createdAt;

    @ColumnInfo(name = "updated_at")
    public long updatedAt;
}


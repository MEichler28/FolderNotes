package data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "checklistitem",
        foreignKeys = {
                @ForeignKey(
                        entity = Folder.class,
                        parentColumns = "folder_id",
                        childColumns = "folder_id",
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Checklist.class,
                        parentColumns = "checklist_id",
                        childColumns = "checklist_id",
                        onDelete = ForeignKey.CASCADE
                )
        },
        indices = {@Index("folder_id"), @Index("checklist_id")}
)
public class ChecklistItem {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "checklistitem_id")
    public int checklistitemId;

    @ColumnInfo(name = "folder_id")
    public int folderId;

    @ColumnInfo(name = "checklist_id")
    public int checklistId;

    @ColumnInfo(name = "checklistitem_text")
    public String checklistitemText;

    @ColumnInfo(name = "done")
    public boolean done;

    @ColumnInfo(name = "sortOrder")
    public int sortOrder;

    @ColumnInfo(name = "created_at")
    public long createdAt;

    @ColumnInfo(name = "updated_at")
    public long updatedAt;
}


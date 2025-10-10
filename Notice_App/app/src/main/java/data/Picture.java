package data;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

    @Entity(
            tableName = "picture",
            foreignKeys = @ForeignKey(
                    entity = Folder.class,
                    parentColumns = "folder_id",
                    childColumns = "folder_id",
                    onDelete = ForeignKey.CASCADE
            ),
            indices = {@Index("folder_id")}
    )
    public class Picture {

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "picture_id")
        public int pictureId;

        @ColumnInfo(name = "folder_id")
        public int folderId;

        @ColumnInfo(name = "picture_title")
        public String pictureTitle;

        @ColumnInfo(name = "url")
        public String url;

        @ColumnInfo(name = "mime_type")
        public String mimeType;

        @ColumnInfo(name = "width")
        public Integer width;

        @ColumnInfo(name = "height")
        public Integer height;

        @ColumnInfo(name = "size_bytes")
        public Long sizeBytes;

        @ColumnInfo(name = "picture_content")
        public String pictureContent;

        @ColumnInfo(name = "created_at")
        public long createdAt;

        @ColumnInfo(name = "updated_at")
        public long updatedAt;
    }

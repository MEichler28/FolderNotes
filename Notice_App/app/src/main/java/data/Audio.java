package data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "audio",
        foreignKeys = @ForeignKey(
                entity = Folder.class,
                parentColumns = "folder_id",
                childColumns = "folder_id",
                onDelete = ForeignKey.CASCADE
        ),
        indices = {@Index("folder_id")}
)
public class Audio {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "audio_id")
    public int audioId;

    @ColumnInfo(name = "folder_id")
    public int folderId;

    @ColumnInfo(name = "audio_title")
    public String audioTitle;

    @ColumnInfo(name = "url")
    public String url;

    @ColumnInfo(name = "mime_type")
    public String mimeType;

    @ColumnInfo(name = "duration")
    public Integer duration;

    @ColumnInfo(name = "bitrate")
    public Long bitRate;

    @ColumnInfo(name = "sample_rate")
    public Integer sampleRate;

    @ColumnInfo(name = "channels")
    public Integer channels;

    @ColumnInfo(name = "audio_note")
    public String audioNote;

    @ColumnInfo(name = "created_at")
    public long createdAt;

    @ColumnInfo(name = "updated_at")
    public long updatedAt;
}
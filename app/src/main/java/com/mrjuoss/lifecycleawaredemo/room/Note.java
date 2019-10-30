package com.mrjuoss.lifecycleawaredemo.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {

    @PrimaryKey
    @NonNull
    private String uuid;

    @ColumnInfo(name = "note")
    @NonNull
    private String mNote;

    public Note(@NonNull String uuid, @NonNull String mNote) {
        this.uuid = uuid;
        this.mNote = mNote;
    }

    @NonNull
    public String getUuid() {
        return uuid;
    }

    @NonNull
    public String getNote() {
        return mNote;
    }
}

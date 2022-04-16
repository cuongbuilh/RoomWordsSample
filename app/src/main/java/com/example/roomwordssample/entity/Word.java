package com.example.roomwordssample.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mID;

    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    public Word( @NonNull String word) {
        this.mWord = word;
    }

    @NonNull
    public int getID() {
        return mID;
    }

    public void setID(@NonNull int mID) {
        this.mID = mID;
    }

    @NonNull
    public String getWord() {
        return mWord;
    }

    public void setWord(@NonNull String mWord) {
        this.mWord = mWord;
    }
}
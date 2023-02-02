package com.example.newapp.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "article_photo")
public class ArticlePhoto {

    @PrimaryKey(autoGenerate = true)
    int id;

   @ColumnInfo(name = "imageToUrl")
    String imageUrl;

    public ArticlePhoto(int id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

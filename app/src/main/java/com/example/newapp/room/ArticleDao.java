package com.example.newapp.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ArticleDao {

    @Query("SELECT * FROM article_photo ")
    List<ArticlePhoto> getArticle();
    @Insert
    void insert(ArticlePhoto articles);
    @Insert
    void insertAll(List<ArticlePhoto> articles);
    @Delete
    void delete(ArticlePhoto articles);


}

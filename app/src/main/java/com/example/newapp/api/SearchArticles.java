package com.example.newapp.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SearchArticles {
    @SerializedName("articles")
   public List<Article> articles;

}

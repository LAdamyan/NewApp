package com.example.newapp.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Images {

    @GET("/v2/everything?q=tesla&sortBy=publishedAt&apiKey=16f17b3107794837b22a1d340d35e528")
    Call<SearchArticles> getArticles();


}



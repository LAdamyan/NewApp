package com.example.newapp.api;

import com.example.newapp.R;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Images {

    @GET("/v2/everything?q=tesla&from=2022-12-31&sortBy=publishedAt&apiKey=16f17b3107794837b22a1d340d35e528")
    public Call<SearchArticles> getArticles();






}



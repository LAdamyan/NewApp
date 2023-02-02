package com.example.newapp.api;

import android.database.Observable;

import com.example.newapp.R;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Images {

    @GET("/v2/everything?q=tesla&sortBy=publishedAt&apiKey=16f17b3107794837b22a1d340d35e528")
    Call<SearchArticles> getArticles();



}



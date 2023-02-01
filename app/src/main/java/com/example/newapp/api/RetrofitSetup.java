package com.example.newapp.api;

import androidx.recyclerview.widget.RecyclerView;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSetup {

    private static final String BASE_URL = "https://newsapi.org";

  public   Images initRetrofit() {

        OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();
        OkHttpClient client = okhttpBuilder.build();


        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BASE_URL);
        builder.client(client);
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.build();

        Retrofit retrofit = builder.build();

        return retrofit.create(Images.class);
    }
}

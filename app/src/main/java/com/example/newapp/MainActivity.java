package com.example.newapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp.api.Article;
import com.example.newapp.api.Images;
import com.example.newapp.api.RetrofitSetup;
import com.example.newapp.api.SearchArticles;
import com.example.newapp.dto.Image;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ImageAdapter imageAdapter = new ImageAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecycleView();
        initImagesFromApi();

    }

    private void initRecycleView() {

        RecyclerView recyclerView = findViewById(R.id.recycleView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(imageAdapter);
    }

    private void initImagesFromApi() {

        RetrofitSetup retrofitSetup = new RetrofitSetup();
        Images images = retrofitSetup.initRetrofit();
        Call<SearchArticles> tesla = images.getArticles();

        tesla.enqueue(new Callback<SearchArticles>() {
            @Override
            public void onResponse(Call<SearchArticles> call, Response<SearchArticles> response) {

                if (response.body() != null) {
                    SearchArticles searchArticles = response.body();
                    List<Article> articles = searchArticles.articles;
                    convertApiImagesToDto(articles);
                }
            }

            @Override
            public void onFailure(Call<SearchArticles> call, Throwable t) {
                System.out.println(t.getLocalizedMessage());
            }
        });
    }

    private void convertApiImagesToDto(List<Article> articles) {
        ArrayList<Image> image1 = new ArrayList<>();
        for (Article article : articles) {
            image1.add(new Image(article.urlToImage));
        }
        setDataToAdapter(image1);
    }

    private void setDataToAdapter(ArrayList<Image> image1) {
        imageAdapter.setImages(image1);
    }
}
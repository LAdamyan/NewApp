package com.example.newapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.newapp.api.Article;
import com.example.newapp.api.Images;
import com.example.newapp.api.RetrofitSetup;
import com.example.newapp.api.SearchArticles;
import com.example.newapp.room.AppDatabase;
import com.example.newapp.room.ArticleDao;
import com.example.newapp.dto.Image;
import com.example.newapp.room.ArticlePhoto;

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

        SwipeRefreshLayout swipeRefresh = findViewById(R.id.swipe_refresh);
        swipeRefresh.setOnRefreshListener(this::initImagesFromApi);


        initRecycleView();
        initImagesFromApi();
        fetchImagesFromApiAndSaveToRoom();

    }

    private void initRecycleView() {
        RecyclerView recyclerView = findViewById(R.id.recycleView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(imageAdapter);



    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }
    private void fetchImagesFromApiAndSaveToRoom() {
        if (isNetworkAvailable()) {
            initImagesFromApi();
        } else {
            List<ArticlePhoto> articles = getArticlesFromRoom();
            if (articles != null && !articles.isEmpty()) {
                saveToDb(articles);

            }
        }
    }

    private List<ArticlePhoto> getArticlesFromRoom() {
        AppDatabase db = AppDatabase.getInstance(this);
        ArticleDao articleDao = db.getArticleDao();
        return articleDao.getArticle();

    }


    private void initImagesFromApi() {
        RetrofitSetup retrofitSetup = new RetrofitSetup();
        Images images = retrofitSetup.initRetrofit();
        Call<SearchArticles> tesla = images.getArticles();
        tesla.enqueue(new Callback<SearchArticles>() {
            @Override
            public void onResponse(@NonNull Call<SearchArticles> call, @NonNull Response<SearchArticles> response) {

                    SearchArticles searchArticles = response.body();
                if (response.body() != null) {
                    List<Article> articles = searchArticles.articles;
                    convertApiImagesToDto(articles);


                }
            }
            @Override
            public void onFailure(@NonNull Call<SearchArticles> call, Throwable t) {
                System.out.println(t.getLocalizedMessage());

            }

        });
    }

    private void saveToDb(List<ArticlePhoto> galleries) {
        AppDatabase db = AppDatabase.getInstance(this);
        ArticleDao articleDao = db.getArticleDao();

        List<ArticlePhoto> entity = new ArrayList<>();

        for (ArticlePhoto dto : galleries) {
            entity.add(new ArticlePhoto(0, dto.getImageUrl()));
        }
        articleDao.insertAll(entity);

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
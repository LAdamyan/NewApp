package com.example.newapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp.api.Article;
import com.example.newapp.api.Images;
import com.example.newapp.api.RetrofitSetup;
import com.example.newapp.api.SearchArticles;
import com.example.newapp.dto.Image;
import com.example.newapp.room.AppDatabase;
import com.example.newapp.room.ArticleDao;
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
        initRecycleView();
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
                ArrayList<Image> imageList = convertRoomImagesToDto(articles);
                setDataToAdapter(imageList);
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
                    ArrayList<Image> imageList = convertApiImagesToDto(articles);
                    saveToDb(imageList);
                    setDataToAdapter(imageList);
                }
            }

            @Override
            public void onFailure(@NonNull Call<SearchArticles> call, Throwable t) {
                System.out.println(t.getLocalizedMessage());
            }
        });
    }

    private ArrayList<Image> convertApiImagesToDto(List<Article> articles) {
        ArrayList<Image> imageList = new ArrayList<>();
        for (Article article : articles) {
            imageList.add(new Image(article.urlToImage));
        }
        return imageList;
    }

    private ArrayList<Image> convertRoomImagesToDto(List<ArticlePhoto> articles) {
        ArrayList<Image> imageList = new ArrayList<>();
        for (ArticlePhoto article : articles) {
            imageList.add(new Image(article.getImageUrl()));
        }
        return imageList;
    }

    private void saveToDb(List<Image> imageList) {
        AppDatabase db = AppDatabase.getInstance(this);
        ArticleDao articleDao = db.getArticleDao();
        List<ArticlePhoto> entity = new ArrayList<>();
        for (Image image : imageList) {
            entity.add(new ArticlePhoto(image.getUrl()));
        }
        articleDao.insertAll(entity);
    }

    private void setDataToAdapter(ArrayList<Image> image1) {
        imageAdapter.setImages(image1);
    }
}
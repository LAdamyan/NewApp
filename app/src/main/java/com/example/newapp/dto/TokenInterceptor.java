package com.example.newapp.dto;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

    private final String HEADER_AUTH = "Authorization";

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        builder.header(HEADER_AUTH,"Bearer 563492ad6f917000010000017806ce8e64d041ef93deaf9be7e08a4b");
        builder.header("Content-Type","application/json");

        Request newRequest = builder.build();
        return chain.proceed(newRequest);
    }
}
